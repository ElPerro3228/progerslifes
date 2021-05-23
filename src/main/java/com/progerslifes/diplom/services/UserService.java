package com.progerslifes.diplom.services;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.entity.UserProfile;
import org.springframework.data.domain.Page;

public interface UserService {
    User saveUser(User user);
    User getUser(String username);
    UserProfile saveUserProfile(UserProfile userProfile);
    User follow(String username);

    User dontFollow(String username);

    Page<User> getUsers(int page);
}
