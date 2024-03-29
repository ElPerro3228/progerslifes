package com.progerslifes.diplom.controllers;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UsernameNotFoundException.class)
    public String userNotFound() {
        return "forward:/home";
    }
}
