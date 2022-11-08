package com.muhammet.manager;

import com.muhammet.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.muhammet.constants.ApiUrls.SAVE;

/**
 * DİKKAT!!
 * name -> benzersiz bir isim olmalıdır. diğer türlü hata alacaksınız.
 */
@FeignClient(name = "user-profile-service",
        url= "${myapplication.user-service.feign-client}/user",
        decode404 = true)
public interface UserProfileManager {

    @PostMapping(SAVE)
    ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto);

}
