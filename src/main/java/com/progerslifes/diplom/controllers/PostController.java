package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.PostFacade;
import com.progerslifes.diplom.facades.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class PostController {

    @Autowired
    private PostFacade postFacade;

    @PostMapping("/post")
    public String savePost(Model model, @Valid PostDTO postDTO,
                           @RequestParam("image") MultipartFile image, Errors errors) throws IOException {
        if (!errors.hasErrors()) {
            postFacade.savePost(postDTO, image);
            return "redirect:home";
        }
        model.addAttribute("postTemplate", postDTO);
        return "home";
    }
}
