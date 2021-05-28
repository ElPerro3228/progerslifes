package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.LikeFacade;
import com.progerslifes.diplom.facades.dto.LikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    @Autowired
    private LikeFacade likeFacade;

    @PostMapping("/like")
    public ResponseEntity<LikeDTO> addLike(@RequestParam("postId") int postId) {
        LikeDTO like = likeFacade.saveLike(postId);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @DeleteMapping("/like")
    public ResponseEntity<String> deleteLike(@RequestParam("postId") int postId) {
        likeFacade.deleteLike(postId);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
