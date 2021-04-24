package com.progerslifes.diplom.facades.converters.user;

import com.progerslifes.diplom.entity.UserProfile;
import com.progerslifes.diplom.facades.converters.GenericConverter;
import com.progerslifes.diplom.facades.dto.UserProfileDTO;
import org.springframework.stereotype.Component;

@Component
public class UserProfileConverter implements GenericConverter<UserProfile, UserProfileDTO> {
    @Override
    public UserProfileDTO apply(UserProfile userProfile) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(userProfile.getId());
        userProfileDTO.setName(userProfile.getName());
        userProfileDTO.setSurname(userProfile.getSurname());
        userProfileDTO.setDescription(userProfile.getDescription());
        userProfileDTO.setProfilePicture(userProfile.getProfilePicture());
        userProfileDTO.setBirthDate(userProfile.getBirthDate());
        return userProfileDTO;
    }
}
