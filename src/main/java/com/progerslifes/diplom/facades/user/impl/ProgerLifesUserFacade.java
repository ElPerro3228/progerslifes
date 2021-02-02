package com.progerslifes.diplom.facades.user.impl;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.entity.UserProfile;
import com.progerslifes.diplom.facades.converters.user.UserDTOConverter;
import com.progerslifes.diplom.facades.converters.user.UserProfileDTOConverter;
import com.progerslifes.diplom.facades.dto.UserDTO;
import com.progerslifes.diplom.facades.dto.UserProfileDTO;
import com.progerslifes.diplom.facades.user.UserFacade;
import com.progerslifes.diplom.services.UploadService;
import com.progerslifes.diplom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ProgerLifesUserFacade implements UserFacade {

    @Autowired
    private UserDTOConverter userDTOConverter;

    @Autowired
    private UserProfileDTOConverter userProfileDTOConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UploadService uploadService;

    @Value("${user.profile.picture.dir}")
    private String imagesDir;

    @Override
    public void registerUser(UserDTO userDTO) {
        User user = userDTOConverter.convert(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setEnabled(true);
        userService.saveUser(user);
    }

    @Override
    public void updateUserProfile(UserProfileDTO userProfileDTO, MultipartFile image) throws IOException {
        UserProfile userProfile = userProfileDTOConverter.convert(userProfileDTO);
        if (image != null) {
            saveProfilePicture(image, userProfile);
        }
        userService.saveUserProfile(userProfile);
    }

    private void saveProfilePicture(MultipartFile image, UserProfile userProfile) throws IOException {
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        uploadService.saveFile(imagesDir, imageName, image);
        userProfile.setProfilePicture(uploadService.getPath(imagesDir, imageName));
        userService.saveUserProfile(userProfile);
    }
}
