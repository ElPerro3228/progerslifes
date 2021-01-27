package com.progerslifes.diplom.facades.converters.user;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.converters.GenericConverter;
import com.progerslifes.diplom.facades.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter implements GenericConverter<UserDTO, User> {
    @Override
    public User apply(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
