package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.controllers.forms.GithubForm;
import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.user.UserFacade;
import com.progerslifes.diplom.validation.validators.GitHubAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GithubController {

    @Autowired
    private UserFacade userFacade;
    @Autowired
    private GitHubAccountValidator gitHubAccountValidator;

    @GetMapping("/profile/connectGithub")
    public String getConnectToGithubPage(Model model) {
        GithubForm githubForm = new GithubForm();
        User user = userFacade.getCurrentUser();
        githubForm.setUsername(user.getUsername());
        githubForm.setGithubLogin(user.getUserProfile().getGithub());
        model.addAttribute("githubForm", githubForm);
        return "connectGithub";
    }

    @PostMapping("/profile/connectGithub")
    public String connectGithubAccount(Model model, GithubForm githubForm, Errors errors) {
        gitHubAccountValidator.validate(githubForm.getGithubLogin(), errors);
        if (!errors.hasErrors()) {
            userFacade.saveGitHubLogin(githubForm.getUsername(), githubForm.getGithubLogin());
            return "redirect:/profile";
        }
        model.addAttribute("username", githubForm.getUsername());
        model.addAttribute("githubLogin", githubForm.getGithubLogin());
        return "connectGithub";
    }
}
