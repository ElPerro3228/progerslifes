package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.entity.UserProfile;
import com.progerslifes.diplom.facades.ProfilePageFacade;
import com.progerslifes.diplom.facades.dto.UserProfileDTO;
import com.progerslifes.diplom.facades.user.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class ProfilePageController {

    @Autowired
    private ProfilePageFacade profilePageFacade;
    @Autowired
    private UserFacade userFacade;

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        User user = profilePageFacade.getUser();
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String getProfileEditor(Model model) {
        UserProfile userProfile = profilePageFacade.getUser().getUserProfile();
        model.addAttribute("userProfile", userProfile);
        return "editProfile";
    }

    @PostMapping("/profile/edit")
    public String getProfileEditor(Model model, @Valid UserProfileDTO userProfileDTO, Errors errors,
                                   @RequestParam("image") MultipartFile image) throws IOException {
        if (!errors.hasErrors()) {
            userFacade.updateUserProfile(userProfileDTO, image);
            return "redirect:/profile";
        }
        model.addAttribute("userProfile", userProfileDTO);
        return "editProfile";
    }

    @GetMapping("/profile/{username}/following")
    public String following(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userFacade.getCurrentUser());
        model.addAttribute("users", userFacade.getFollowingUsers(username));
        return "following";
    }

    @GetMapping("/profile/{username}/followers")
    public String followers(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userFacade.getCurrentUser());
        model.addAttribute("users", userFacade.getFollowers(username));
        return "followers";
    }
}
