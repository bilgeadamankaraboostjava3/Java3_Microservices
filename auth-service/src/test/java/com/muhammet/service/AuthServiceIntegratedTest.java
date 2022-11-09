package com.muhammet.service;

import com.muhammet.repository.entity.Auth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AuthServiceIntegratedTest {

    @Autowired
    AuthService authService;

    @Test
    public void test(){
        List<Auth> authList = authService.findAll();
        Assertions.assertEquals(10,  authList.size());
    }
}
