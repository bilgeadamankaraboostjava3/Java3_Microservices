package com.muhammet.controller;

import com.muhammet.dto.request.LoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.rabbitmq.producer.MessageProducer;
import com.muhammet.repository.entity.Auth;
import com.muhammet.repository.enums.Activated;
import com.muhammet.repository.enums.Roles;
import com.muhammet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.muhammet.constants.ApiUrls.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MessageProducer  messageProducer;
    @PostMapping(DOLOGIN)
    public ResponseEntity<String> doLogin(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }
    @PostMapping(REGISTER)
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto dto){
        if(authService.save(dto))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Auth>> getList(){
        return ResponseEntity.ok(authService.findAll());
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<Void> sendMessage(String message, Long code){
        messageProducer.sendMessage(message, code);
        return ResponseEntity.ok().build();
    }

}
