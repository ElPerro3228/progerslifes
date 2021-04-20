package com.progerslifes.diplom.services;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.entity.UserProfile;

public interface UserService {
    User saveUser(User user);
    User getUser(String username);
    UserProfile saveUserProfile(UserProfile userProfile);
    User follow(String username);
}
