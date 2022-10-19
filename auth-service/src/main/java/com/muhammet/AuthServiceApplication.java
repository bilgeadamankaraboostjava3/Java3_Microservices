package com.muhammet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
/**
 * OpenFeign kullanımı için bunu eklemeniz gerekiyor. diğer türlü işlemler null pointer exception hatası veriyor.
 */
@EnableFeignClients
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}