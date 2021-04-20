package com.progerslifes.diplom.facades.user;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.dto.UserDTO;
import com.progerslifes.diplom.facades.dto.UserProfileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserFacade {
    void registerUser (UserDTO userDTO);
    void updateUserProfile (UserProfileDTO userProfileDTO, MultipartFile image) throws IOException;
    User follow(String username);
    boolean isFollowing(String username);
}
