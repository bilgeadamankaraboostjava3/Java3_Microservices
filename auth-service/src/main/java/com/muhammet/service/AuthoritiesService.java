package com.muhammet.service;

import com.muhammet.repository.IAuthoritiesRepository;
import com.muhammet.repository.entity.Authorities;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService extends ServiceManager<Authorities,Long> {

    private final IAuthoritiesRepository authoritiesRepository;

    public AuthoritiesService(IAuthoritiesRepository authoritiesRepository) {
        super(authoritiesRepository);
        this.authoritiesRepository = authoritiesRepository;
    }
}
