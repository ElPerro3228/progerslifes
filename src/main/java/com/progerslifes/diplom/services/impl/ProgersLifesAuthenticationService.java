package com.progerslifes.diplom.services.impl;

import com.progerslifes.diplom.services.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProgersLifesAuthenticationService implements AuthenticationService {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
