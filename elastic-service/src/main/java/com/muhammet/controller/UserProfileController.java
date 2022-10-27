package com.muhammet.controller;

import com.muhammet.dto.request.UserProfileRequestDto;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.muhammet.contants.ApiUrls.*;
@RestController
@RequestMapping(ELASTIC)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody UserProfileRequestDto dto){
        userProfileService.save(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(UPDATE)
    public ResponseEntity<Void> update(@RequestBody UserProfile userProfile){
        userProfileService.save(userProfile);
        return ResponseEntity.ok().build();
    }

    @GetMapping(GETALL)
    public ResponseEntity<Iterable<UserProfile>> getAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }

    /**
     * Sorgu için girilebilecek parametreler
     * -> username
     * -> email
     * -> phone
     * -> address, email into @gmail
     * 10 adet girdi isteyebilirim.
     * Geriye dönülecek sonuçlar
     * -> id, username, name, avatar
     * -> id, username
     * -> id, username, phone
     * 20 farklı dönüş tipim olabilir.
     * @return
     */
//    public ResponseEntity<?> findByAll(){
//        return ResponseEntity.ok(userProfileService.findAll());
//    }


}
