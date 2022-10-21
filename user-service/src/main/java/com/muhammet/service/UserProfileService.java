package com.muhammet.service;

import com.muhammet.dto.request.UserProfileSaveRequestDto;
import com.muhammet.dto.request.UserProfileUpdateRequestDto;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.UserServiceException;
import com.muhammet.repository.IUserProfileRepository;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.utility.JwtTokenManager;
import com.muhammet.utility.ServiceManager;
import com.muhammet.utility.TokenManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository iUserProfileRepository;
    private final JwtTokenManager tokenManager;
    public UserProfileService(IUserProfileRepository iUserProfileRepository,
                              JwtTokenManager tokenManager) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
        this.tokenManager = tokenManager;
    }

    public Boolean save(UserProfileSaveRequestDto dto){
        save(UserProfile.builder()
                .authid(dto.getAuthid())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build());
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
        return true;
    }
}
