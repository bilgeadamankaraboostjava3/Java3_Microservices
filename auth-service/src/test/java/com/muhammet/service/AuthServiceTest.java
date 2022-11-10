package com.muhammet.service;

import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.manager.UserProfileManager;
import com.muhammet.rabbitmq.producer.CreateProfileProducer;
import com.muhammet.repository.IAuthRepository;
import com.muhammet.repository.entity.Auth;
import com.muhammet.repository.enums.Roles;
import com.muhammet.utility.JwtTokenManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.muhammet.utility.AuthUtility.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private AuthService authService;
    private IAuthRepository repository;
    private JwtTokenManager tokenManager;
    private UserProfileManager userProfileManager;
    private CreateProfileProducer createProfileProducer;

    @BeforeEach
    void setUp(){
        repository = mock(IAuthRepository.class);
        tokenManager = mock(JwtTokenManager.class);
        userProfileManager = mock(UserProfileManager.class);
        createProfileProducer = mock(CreateProfileProducer.class);
        authService = new AuthService(repository, userProfileManager, createProfileProducer, tokenManager);
    }

    @Test
    public void saveTestAllow(){
        RegisterRequestDto dto = RegisterRequestDto.builder()
                .email("muhammet@gmail.com")
                .username("muhammet")
                .password("123456")
                .build();
        Auth auth = Auth.builder()
                .id(1L)
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .roles(Roles.ROLE_USER)
                .build();
        /**
         * Burada oluşan hata servis nesnesi oluştururken kullanması için verdiğimiz repository nesnesi Mock
         * nesnesi olduğu için biz müdehale etmedikçe tepki vermeyecektir.
         * bunun için,
         * Eğer herhangi bir istek repository nesnesinin save methodunu çağırır ise ona auth nesnesini dön.
         */
        when(repository.save(any())).thenReturn(auth);

        Boolean issaved = authService.save(dto);
        Assertions.assertTrue(issaved);
    }

    @Test
    public void saveTestError(){
        RegisterRequestDto dto = RegisterRequestDto.builder()
                .email("muhammet@gmail.com")
                .username("muhammet")
                .password("123456")
                .build();
        Auth auth = Auth.builder()
                .id(1L)
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .roles(Roles.ROLE_USER)
                .build();
        /**
         * Burada oluşan hata servis nesnesi oluştururken kullanması için verdiğimiz repository nesnesi Mock
         * nesnesi olduğu için biz müdehale etmedikçe tepki vermeyecektir.
         * bunun için,
         * Eğer herhangi bir istek repository nesnesinin save methodunu çağırır ise ona auth nesnesini dön.
         */
        when(repository.save(any())).thenReturn(auth);
        doThrow(new RuntimeException()).when(createProfileProducer).createProfile(any());
        Boolean issaved = authService.save(dto);
        Assertions.assertTrue(!issaved);
    }

    @Test
    public void saveTestDontSave(){
        RegisterRequestDto dto = RegisterRequestDto.builder()
                .email("muhammet@gmail.com")
                .username("muhammet")
                .password("123456")
                .build();
        Auth auth = Auth.builder()
                .id(null)
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .roles(Roles.ROLE_USER)
                .build();
        /**
         * Burada oluşan hata servis nesnesi oluştururken kullanması için verdiğimiz repository nesnesi Mock
         * nesnesi olduğu için biz müdehale etmedikçe tepki vermeyecektir.
         * bunun için,
         * Eğer herhangi bir istek repository nesnesinin save methodunu çağırır ise ona auth nesnesini dön.
         */
        when(repository.save(any())).thenReturn(auth);
        Boolean issaved = authService.save(dto);
        Assertions.assertTrue(!issaved);
    }

    @Test
    public void doLoginTestError(){
        authService.doLogin(getLoginDto());
    }

    @Test
    public void doLoginTest(){
        when(repository.findOptionalByUsernameAndPassword(any(),any())).thenReturn(getOptionalAuth());
        when(tokenManager.createToken(any())).thenReturn("token");
        authService.doLogin(getLoginDto1());
    }
}
