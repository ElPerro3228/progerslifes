package com.progerslifes.diplom.controllers.advice;

import com.progerslifes.diplom.facades.exceptions.LikeHasBeenLeftAlready;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LikeControllerAdvice {

    @ExceptionHandler(LikeHasBeenLeftAlready.class)
    public ResponseEntity<String> handleLikeHasBeenLeftAlready() {
        return new ResponseEntity<>("like has been left already", HttpStatus.CONFLICT);
    }

}
