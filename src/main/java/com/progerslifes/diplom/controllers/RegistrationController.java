package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.dto.UserDTO;
import com.progerslifes.diplom.facades.user.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(Model model, @Valid UserDTO userDTO, Errors errors) {
        if (!errors.hasErrors()) {
            userFacade.registerUser(userDTO);
            return "redirect:/login";
        }
        model.addAttribute("user", userDTO);
        return "registration";
    }
}
