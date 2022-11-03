package com.muhammet.controller;

import com.muhammet.dto.request.LoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.dto.response.LoginResponseDto;
import com.muhammet.dto.response.RegisterResponseDto;
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
    public ResponseEntity<LoginResponseDto> doLogin(@RequestBody @Valid LoginRequestDto dto){
        String token = authService.doLogin(dto);
        return ResponseEntity.ok(LoginResponseDto.builder()
                        .token(token)
                        .code(1201)
                        .message("Giriş başarılı")
                .build());
    }
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        if(authService.save(dto))
            return ResponseEntity.ok(RegisterResponseDto.builder()
                            .code(1200)
                            .message("Kayıt Başarılı")
                    .build());
        return ResponseEntity.badRequest().body(RegisterResponseDto.builder()
                .message("Kayıt Başarısız")
                .code(1400)
                .build());
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
