package com.progerslifes.diplom.facades.converters.user;

import com.progerslifes.diplom.entity.UserProfile;
import com.progerslifes.diplom.facades.converters.GenericConverter;
import com.progerslifes.diplom.facades.dto.UserProfileDTO;
import org.springframework.stereotype.Component;

@Component
public class UserProfileDTOConverter implements GenericConverter<UserProfileDTO, UserProfile> {
    @Override
    public UserProfile apply(UserProfileDTO userProfileDTO) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userProfileDTO.getId());
        userProfile.setName(userProfileDTO.getName());
        userProfile.setSurname(userProfileDTO.getSurname());
        userProfile.setDescription(userProfileDTO.getDescription());
        userProfile.setProfilePicture(userProfileDTO.getProfilePicture());
        userProfile.setBirthDate(userProfileDTO.getBirthDate());
        userProfile.setGithub(userProfileDTO.getGithub());
        return userProfile;
    }
}
