package com.muhammet.service;

import com.muhammet.dto.request.FindByAuthidRequestDto;
import com.muhammet.dto.request.GetMyProfileRequestDto;
import com.muhammet.dto.request.UserProfileSaveRequestDto;
import com.muhammet.dto.request.UserProfileUpdateRequestDto;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.UserServiceException;
import com.muhammet.repository.IUserProfileRepository;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.utility.JwtTokenManager;
import com.muhammet.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {

    private final IUserProfileRepository iUserProfileRepository;
    private final JwtTokenManager tokenManager;
    private final CacheManager cacheManager;
  //  private final ElasticSearchManager elasticSearchManager;

    public Optional<UserProfile> findByAuthid(Long authid){
        return iUserProfileRepository.findOptionalByAuthid(authid);
    }
    public UserProfileService(IUserProfileRepository iUserProfileRepository,
                              CacheManager cacheManager,
                              JwtTokenManager tokenManager) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
        this.tokenManager = tokenManager;
        this.cacheManager = cacheManager;
       // this.elasticSearchManager = elasticSearchManager;
    }

    public UserProfile findByToken(GetMyProfileRequestDto dto){
        Optional<Long> authid = tokenManager.getByIdFromToken(dto.getToken());
        if(authid.isEmpty()) throw new UserServiceException(ErrorType.GECERSIZ_ID);
        Optional<UserProfile> userProfile = iUserProfileRepository.findOptionalByAuthid(authid.get());
        if(userProfile.isEmpty()) throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        return userProfile.get();
        /*
        GlobalException burada oluşacak hatayı yakalayabilir. ama istersek bizde bu hatayı kontrol edebiliriz.
        try{
             Optional<UserProfile> userProfile = iUserProfileRepository.findOptionalByAuthid(authid.get());
        }catch (Exception e){
            throw new UserServiceException(ErrorType.GECERSIZ_ID);
        }
       */
    }

    @Cacheable(value = "uppercase")
    public String getUpperCase(Long authid) {
        /**
         * Bu kısım methodun belli işlem basamaklarını simüle etmek ve
         * belli zaman alacak işlemleri göstermek için yazılmıştır.
         */
        try{
            Thread.sleep(3000);
        }catch (Exception e){

        }
         Optional<UserProfile> user = iUserProfileRepository.findOptionalByAuthid(authid);
        if(user.isEmpty()) return "";
        return user.get().getName().toUpperCase();
    }
    public Boolean save(UserProfileSaveRequestDto dto){
      UserProfile userProfile =   save(UserProfile.builder()
                .authid(dto.getAuthid())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build());
      // elasticSearchManager.save(userProfile);
        return true;
    }

    public Boolean updateNonToken(UserProfileUpdateRequestDto dto){
        Optional<UserProfile> userProfile =
                iUserProfileRepository.findOptionalByAuthid(dto.getAuthid());
        UserProfile profile = userProfile.get();
        profile.setAddress(dto.getAddress());
        profile.setPhone(dto.getPhone());
        profile.setAvatar(dto.getAvatar());
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        save(profile);
       // elasticSearchManager.update(profile);
        return true;
    }

    public Boolean update(UserProfileUpdateRequestDto dto){
        Optional<Long> authid = tokenManager.getByIdFromToken(dto.getToken());
        if(authid.isEmpty()) throw new UserServiceException(ErrorType.GECERSIZ_ID);
        Optional<UserProfile> userProfile =
                iUserProfileRepository.findOptionalByAuthid(authid.get());
        if(userProfile.isEmpty()) throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        UserProfile profile = userProfile.get();
        profile.setAddress(dto.getAddress());
        profile.setPhone(dto.getPhone());
        profile.setAvatar(dto.getAvatar());
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        save(profile);
       // elasticSearchManager.update(profile);
        return true;
    }
    public void updateCacheReset(UserProfile profile){
        save(profile);
        /**
         * Bu işlem ilgili method tarafından tutulan tüm önbeleklenmiş datayı temizler
         * çok istemediğimiz gerekli olduğunda kullanmamız gereken bir yapıdır.
         *  cacheManager.getCache("uppercase")
         */
        cacheManager.getCache("uppercase").evict(profile.getAuthid());
    }

    /**
     *  ÖRN: 500 adet kayıt var.
     *  DİKKAT -> sayfa sayıları 0(sıfır) dan başlar.
     *  - 10 ar adet kayıt göstermek istediğimde 50 adet sayfa oluşur.
     *  - 2. sayfayı sitediğimde 21-30. kayıtlar gösterilir.
     * @param pageSize -> her seferinde kaç kayıt döneceğini belirler
     * @param currentPageNumber -> geçerli sayfanın hangisi olduğunu belirler
     * @param sortParameter -> sıralama işleminin hangi kolon a göre yapılaağını belirler.
     * @param sortDirection -> sıralama yönü, ASC,DESC
     * @return
     */
    public Page<UserProfile> getAllPage(int pageSize, int currentPageNumber, String sortParameter, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortParameter);
        Pageable pageable = PageRequest.of(currentPageNumber,pageSize,sort);
        return iUserProfileRepository.findAll(pageable);
    }

    public Slice<UserProfile> getAllSlice(int pageSize, int currentPageNumber, String sortParameter, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortParameter);
        Pageable pageable = PageRequest.of(currentPageNumber,pageSize,sort);
        return iUserProfileRepository.findAll(pageable);
    }

    public UserProfile findByAuthid(FindByAuthidRequestDto dto){
        Optional<UserProfile> userProfile = iUserProfileRepository.findOptionalByAuthid(dto.getAuthid());
        if(userProfile.isEmpty()) throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        return userProfile.get();
    }

    public List<UserProfile> findAllByIdIn(List<String> ids){
        return iUserProfileRepository.findAllByIdIn(ids);
    }

}
