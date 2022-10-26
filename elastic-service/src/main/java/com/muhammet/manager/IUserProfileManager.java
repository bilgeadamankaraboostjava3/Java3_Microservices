package com.muhammet.manager;

import com.muhammet.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.muhammet.contants.ApiUrls.*;

@FeignClient(name = "user-service",
        //          http://localhost:9092/api/v1         /user
        url = "${myapplication.user-service.feign-client}/user"
,decode404 = true)
public interface IUserProfileManager {

    @GetMapping(USER_LIST)
    ResponseEntity<List<UserProfile>> userList();
}
