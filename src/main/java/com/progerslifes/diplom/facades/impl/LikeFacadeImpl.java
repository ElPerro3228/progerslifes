package com.progerslifes.diplom.facades.impl;

import com.progerslifes.diplom.entity.Like;
import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.LikeFacade;
import com.progerslifes.diplom.facades.converters.user.LikeConverter;
import com.progerslifes.diplom.facades.dto.LikesDTO;
import com.progerslifes.diplom.facades.exceptions.LikeHasBeenLeftAlready;
import com.progerslifes.diplom.facades.user.UserFacade;
import com.progerslifes.diplom.services.LikeService;
import com.progerslifes.diplom.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LikeFacadeImpl implements LikeFacade {

    @Autowired
    private LikeService likeService;

    @Autowired
    private PostService postService;
    
    @Autowired
    private UserFacade userFacade;

    @Autowired
    private LikeConverter likeConverter;

    @Override
    public LikesDTO saveLike(int postId) {
        Like like = new Like();
        Post post = postService.getPostById(postId);
        User user = userFacade.getCurrentUser();
        if (likeService.getLikeByPostAndUser(postId, user.getId()) == null) {
            saveLike(like, post, user);
            updatePostLikesCount(postId, post);
            LikesDTO likesDTO = likeConverter.convert(like);
            likesDTO.setPostLikes(post.getLikesCount());
            return likesDTO;
        } else {
            throw new LikeHasBeenLeftAlready();
        }
    }

    @Override
    public LikesDTO deleteLike(int postId) {
        User user = userFacade.getCurrentUser();
        Post post = postService.getPostById(postId);
        Like like = likeService.getLikeByPostAndUser(postId, user.getId());
        LikesDTO likesDTO = likeConverter.convert(like);
        likeService.delete(like);
        updatePostLikesCount(postId, post);
        likesDTO.setPostLikes(post.getLikesCount());
        return likesDTO;
    }

    @Override
    public void markPostsLikedByCurrentUser(List<Post> posts) {
        User user = userFacade.getCurrentUser();
        List<Like> likes = user.getLikes();
        Set<Post> likedPosts = likes.stream()
                .map(Like::getPost)
                .collect(Collectors.toSet());
        posts.stream()
                .filter(post -> likedPosts.contains(post))
                .forEach(post -> post.setLikedByCurrentUser(true));
    }

    private void saveLike(Like like, Post post, User user) {
        like.setPost(post);
        like.setUser(user);
        likeService.save(like);
    }

    private void updatePostLikesCount(int postId, Post post) {
        post.setLikesCount(likeService.getLikesCountByPost(postId));
        postService.save(post);
    }
}
