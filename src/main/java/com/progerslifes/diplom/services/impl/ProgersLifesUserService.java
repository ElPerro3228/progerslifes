package com.progerslifes.diplom.services.impl;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.repository.user.UserRepository;
import com.progerslifes.diplom.services.UserService;
import com.progerslifes.diplom.services.exception.UserRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgersLifesUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        User dbUser = userRepository.getUserByUsername(user.getUsername());
        if (dbUser != null) {
            throw new UserRegistrationException("user with name " + user.getUsername() + "already exists");
        }
        return userRepository.save(user);
    }
}
