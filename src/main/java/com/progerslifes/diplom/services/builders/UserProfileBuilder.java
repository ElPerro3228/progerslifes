package com.progerslifes.diplom.services.builders;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.entity.UserProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


import java.util.Date;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserProfileBuilder {

    @Value("${user.profile.default.picture}")
    private String defaultProfilePicture;

    private int id;
    private Date birthDate = new Date();
    private String name = "";
    private String surname = "";
    private String description = "";
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserProfile getResult() {
        UserProfile userProfile = new UserProfile();
        userProfile.setProfilePicture(defaultProfilePicture);
        userProfile.setBirthDate(new Date());
        userProfile.setSurname(surname);
        userProfile.setName(name);
        userProfile.setDescription(description);
        return userProfile;
    }
}
