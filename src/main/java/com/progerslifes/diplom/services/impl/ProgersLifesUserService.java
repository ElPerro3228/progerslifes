package com.progerslifes.diplom.services.impl;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.entity.UserProfile;
import com.progerslifes.diplom.repository.user.UserProfileRepository;
import com.progerslifes.diplom.repository.user.UserRepository;
import com.progerslifes.diplom.services.AuthenticationService;
import com.progerslifes.diplom.services.UserService;
import com.progerslifes.diplom.services.exception.CurrentUserAlreadyFollowsUser;
import com.progerslifes.diplom.services.exception.UserRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProgersLifesUserService implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public User saveUser(User user) {
        User dbUser = userRepository.getUserByUsername(user.getUsername());
        if (dbUser != null) {
            throw new UserRegistrationException("user with name " + user.getUsername() + " already exists");
        }
        user.setLastModified(new Date());
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        User dbUser = userRepository.getUserByUsername(username);
        if (dbUser == null) {
            throw new UsernameNotFoundException("user with name " + username + " doesn't exist");
        }
        return dbUser;
    }

    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) {
        userProfile.setLastModified(new Date());
        return userProfileRepository.save(userProfile);
    }

    @Override
    public User follow(String username) {
        User followingUser = userRepository.getUserByUsername(username);
        User currentUser = userRepository.getUserByUsername(authenticationService.getAuthentication().getName());
        if (currentUser.getSubscriptions().contains(followingUser)) {
            throw new CurrentUserAlreadyFollowsUser("Current user already follow user with username: " + username);
        }
        currentUser.addSubscription(followingUser);
        return userRepository.save(currentUser);
    }

    @Override
    public User dontFollow(String username) {
        User followingUser = userRepository.getUserByUsername(username);
        User currentUser = userRepository.getUserByUsername(authenticationService.getAuthentication().getName());
        if (currentUser.getSubscriptions().contains(followingUser)) {
            currentUser.deleteSubscription(followingUser);
            currentUser = userRepository.save(currentUser);
        }
        return currentUser;
    }
}
