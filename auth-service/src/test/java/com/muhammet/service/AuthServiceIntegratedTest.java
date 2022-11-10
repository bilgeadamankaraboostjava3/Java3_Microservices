package com.muhammet.service;

import com.muhammet.repository.entity.Auth;
import com.muhammet.utility.AuthUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static com.muhammet.utility.AuthUtility.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootTest
@ActiveProfiles("test")
public class AuthServiceIntegratedTest {

    @Autowired
    AuthService authService;

    @Test
    public void saveTest(){
        AtomicBoolean isSave = new AtomicBoolean(false);
        getRegisterDtoList().forEach(r->{
           isSave.set(authService.save(r));
        });
        Assertions.assertTrue(isSave.get());

    }

    @Test
    public void test(){
        List<Auth> authList = authService.findAll();
        Assertions.assertEquals(10,  authList.size());
    }



}
