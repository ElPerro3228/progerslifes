package com.progerslifes.diplom.facades.user;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.dto.UserDTO;
import com.progerslifes.diplom.facades.dto.UserProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserFacade {
    void registerUser (UserDTO userDTO);
    void updateUserProfile (UserProfileDTO userProfileDTO, MultipartFile image) throws IOException;
    User follow(String username);
    boolean isFollowing(String username);

    User dontFollow(String username);

    List<UserDTO> getFollowingUsers(String username);

    List<UserDTO> getFollowers(String username);

    User saveGitHubLogin(String username, String githubLogin);

    User getCurrentUser();
    User getUser(String username);
    Page<User> getUsers(int page);

    List<User> saveUsers(List<User> users);
}
