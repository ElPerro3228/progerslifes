package com.progerslifes.diplom.facades.converters.user;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.converters.GenericConverter;
import com.progerslifes.diplom.facades.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements GenericConverter<User, UserDTO> {

    @Autowired
    private UserProfileConverter userProfileConverter;

    @Override
    public UserDTO apply(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setUserProfile(userProfileConverter.convert(user.getUserProfile()));
        return userDTO;
    }
}
