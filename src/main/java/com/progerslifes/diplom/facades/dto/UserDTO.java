package com.progerslifes.diplom.facades.dto;

import com.progerslifes.diplom.validation.user.PasswordMatches;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class UserDTO {
    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @Nullable
    private UserProfileDTO userProfile;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @Nullable
    public UserProfileDTO getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(@Nullable UserProfileDTO userProfile) {
        this.userProfile = userProfile;
    }
}
