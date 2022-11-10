package com.muhammet.utility;

import com.muhammet.dto.request.LoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.repository.entity.Auth;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AuthUtility {

    public static RegisterRequestDto getRegisterDto(){
        return RegisterRequestDto.builder()
                .email("muhammet@gmail.com")
                .username("muhammet")
                .password("123456")
                .build();
    }

    public static List<RegisterRequestDto> getRegisterDtoList(){
        List<RegisterRequestDto> result = Arrays.asList(
         RegisterRequestDto.builder()
                .email("muhammet@gmail.com")
                .username("muhammet1")
                .password("123456")
                 .admincode("Adm!n")
                .build(),
                RegisterRequestDto.builder()
                        .email("muhammet@gmail.com")
                        .username("muhammet2")
                        .password("123456")
                        .build(),
                RegisterRequestDto.builder()
                        .email("muhammet@gmail.com")
                        .username("muhammet3")
                        .password("123456")
                        .build(),
                RegisterRequestDto.builder()
                        .email("muhammet@gmail.com")
                        .username("muhammet4")
                        .password("123456")
                        .build(),
                RegisterRequestDto.builder()
                        .email("muhammet@gmail.com")
                        .username("muhammet5")
                        .password("123456")
                        .build(),
                RegisterRequestDto.builder()
                        .email("muhammet@gmail.com")
                        .username("muhammet6")
                        .password("123456")
                        .build(),
                RegisterRequestDto.builder()
                        .email("muhammet@gmail.com")
                        .username("muhammet7")
                        .password("123456")
                        .build(),
                RegisterRequestDto.builder()
                        .email("muhammet@gmail.com")
                        .username("muhammet8")
                        .password("123456")
                        .build(),
                RegisterRequestDto.builder()
                        .email("muhammet@gmail.com")
                        .username("muhammet9")
                        .password("123456")
                        .build(),
                RegisterRequestDto.builder()
                        .email("muhammet@gmail.com")
                        .username("muhammet10")
                        .password("123456")
                        .build()
                );
        return result;
    }


    public static LoginRequestDto getLoginDto(){
        return LoginRequestDto.builder()
                .username("muhammet")
                .password("123456")
                .build();
    }
    public static LoginRequestDto getLoginDto1(){
        return LoginRequestDto.builder()
                .username("muhammet1")
                .password("123456")
                .build();
    }

    public static Optional<Auth> getOptionalAuth(){
        return Optional.of(Auth.builder()
                .id(1L)
                .username("muhammet")
                .password("123456")
                .build());
    }

}
