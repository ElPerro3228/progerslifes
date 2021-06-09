package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.LikeFacade;
import com.progerslifes.diplom.facades.dto.LikesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    @Autowired
    private LikeFacade likeFacade;

    @PostMapping(value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikesDTO> addLike(@RequestBody LikesDTO likesDTO) {
        LikesDTO like = likeFacade.saveLike(likesDTO.getPostId());
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @DeleteMapping(value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikesDTO> deleteLike(@RequestBody LikesDTO likesDTO) {
        LikesDTO likes = likeFacade.deleteLike(likesDTO.getPostId());
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }
}
