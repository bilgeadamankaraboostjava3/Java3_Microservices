package com.muhammet.controller;

import com.muhammet.dto.request.UserProfileSaveRequestDto;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.muhammet.constants.ApiUrls.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileContoller {

    private final UserProfileService userProfileService;

    /**
     * Kullanıcı kaydı, auth service te yapılıyor ve burada olan bilgiler user-service e gönderiliyor.
     * Auth-Service ten gelecek olan parametreler:
     * 1- username
     * 2- email
     * 3- authid
     * @return
     */
    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto){
        return ResponseEntity.ok(userProfileService.save(dto));
    }
    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> update(){
        return null;
    }
    @PostMapping(FIND_BY_ID)
    public ResponseEntity<UserProfile> findById(){
        return null;
    }
    @PostMapping(USER_LIST)
    public ResponseEntity<List<UserProfile>> userList(){
        return null;
    }
}
