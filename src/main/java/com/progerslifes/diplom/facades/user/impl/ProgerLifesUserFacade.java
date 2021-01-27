package com.progerslifes.diplom.facades.user.impl;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.converters.user.UserDTOConverter;
import com.progerslifes.diplom.facades.dto.UserDTO;
import com.progerslifes.diplom.facades.user.UserFacade;
import com.progerslifes.diplom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ProgerLifesUserFacade implements UserFacade {

    @Autowired
    private UserDTOConverter userDTOConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registerUser(UserDTO userDTO) {
        User user = userDTOConverter.convert(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setEnabled(true);
        userService.saveUser(user);
    }
}
