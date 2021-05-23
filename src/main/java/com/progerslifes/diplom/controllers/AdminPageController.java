package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.controllers.forms.UpdateUsersForm;
import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.user.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AdminPageController {

    @Autowired
    private UserFacade userFacade;

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String getAdminPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Page<User> usersPage = userFacade.getUsers(page);
        UpdateUsersForm updateUsersForm = new UpdateUsersForm(usersPage.getContent(), page, usersPage.getTotalPages());
        model.addAttribute("updateUsersForm", updateUsersForm);
        return "adminUsersControlPanel";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/admin/users/update")
    public String saveUsers(Model model, @Valid @ModelAttribute("updateUsersForm") UpdateUsersForm updateUsersForm,
                            Errors errors) {
        if (!errors.hasErrors()) {
            userFacade.saveUsers(updateUsersForm.getUsers());
            return "redirect:/admin?page=" + updateUsersForm.getPage();
        }
        return "adminUsersControlPanel";
    }
}
