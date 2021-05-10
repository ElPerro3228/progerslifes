package com.progerslifes.diplom.facades.user.impl;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.entity.UserProfile;
import com.progerslifes.diplom.facades.converters.user.UserConverter;
import com.progerslifes.diplom.facades.converters.user.UserDTOConverter;
import com.progerslifes.diplom.facades.converters.user.UserProfileDTOConverter;
import com.progerslifes.diplom.facades.dto.UserDTO;
import com.progerslifes.diplom.facades.dto.UserProfileDTO;
import com.progerslifes.diplom.facades.user.UserFacade;
import com.progerslifes.diplom.services.AuthenticationService;
import com.progerslifes.diplom.services.UploadService;
import com.progerslifes.diplom.services.UserService;
import com.progerslifes.diplom.services.builders.UserProfileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ProgerLifesUserFacade implements UserFacade {

    @Autowired
    private UserDTOConverter userDTOConverter;

    @Autowired
    private UserProfileDTOConverter userProfileDTOConverter;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UserProfileBuilder userProfileBuilder;

    @Autowired
    private AuthenticationService authenticationService;

    @Value("${user.profile.picture.dir}")
    private String imagesDir;

    @Override
    public void registerUser(UserDTO userDTO) {
        User user = userDTOConverter.convert(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setEnabled(true);
        user.setUserProfile(createUserDefaultProfile(user));
        userService.saveUser(user);
    }

    @Override
    public void updateUserProfile(UserProfileDTO userProfileDTO, MultipartFile image) throws IOException {
        UserProfile userProfile = userProfileDTOConverter.convert(userProfileDTO);
        if ((!image.isEmpty()) && (image.getSize() != 0)) {
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

    private UserProfile createUserDefaultProfile(User user) {
        userProfileBuilder.setName(user.getUsername());
        userProfileBuilder.setUser(user);
        return userProfileBuilder.getResult();
    }

    @Override
    public User follow(String username) {
        return userService.follow(username);
    }

    @Override
    public boolean isFollowing(String username) {
        User followingUser = userService.getUser(username);
        User currentUser = userService.getUser(authenticationService.getAuthentication().getName());
        return currentUser.getSubscriptions().contains(followingUser);
    }

    @Override
    public User dontFollow(String username) {
        return userService.dontFollow(username);
    }

    @Override
    public List<UserDTO> getFollowingUsers(String username) {
        User user = userService.getUser(username);
        return userConverter.convert(user.getSubscriptions());
    }

    @Override
    public List<UserDTO> getFollowers(String username) {
        User user = userService.getUser(username);
        return userConverter.convert(user.getSubscribers());
    }

    @Override
    public User getCurrentUser() {
        return userService.getUser(authenticationService.getAuthentication().getName());
    }

    @Override
    public User saveGitHubLogin(String username, String githubLogin) {
        User user = userService.getUser(username);
        UserProfile userProfile = user.getUserProfile();
        userProfile.setGithub(githubLogin);
        userService.saveUserProfile(userProfile);
        return user;
    }
}
