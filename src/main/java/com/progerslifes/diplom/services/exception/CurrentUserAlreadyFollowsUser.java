package com.progerslifes.diplom.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CurrentUserAlreadyFollowsUser extends RuntimeException {

    public CurrentUserAlreadyFollowsUser() {
        super();
    }

    public CurrentUserAlreadyFollowsUser(String message) {
        super(message);
    }
}
