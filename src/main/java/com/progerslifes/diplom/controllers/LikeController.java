package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.LikeFacade;
import com.progerslifes.diplom.facades.dto.LikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    @Autowired
    private LikeFacade likeFacade;

    @PostMapping(value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikeDTO> addLike(@RequestBody LikeDTO likeDTO) {
        LikeDTO like = likeFacade.saveLike(likeDTO.getPostId());
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @DeleteMapping(value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteLike(@RequestBody LikeDTO likeDTO) {
        likeFacade.deleteLike(likeDTO.getPostId());
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
