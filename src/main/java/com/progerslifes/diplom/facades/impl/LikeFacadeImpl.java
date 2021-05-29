package com.progerslifes.diplom.facades.impl;

import com.progerslifes.diplom.entity.Like;
import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.LikeFacade;
import com.progerslifes.diplom.facades.converters.user.LikeConverter;
import com.progerslifes.diplom.facades.dto.LikeDTO;
import com.progerslifes.diplom.facades.user.UserFacade;
import com.progerslifes.diplom.services.LikeService;
import com.progerslifes.diplom.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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
    public LikeDTO saveLike(int postId) {
        Like like = new Like();
        Post post = postService.getPostById(postId);
        User user = userFacade.getCurrentUser();
        like.setPost(post);
        like.setUser(user);
        return likeConverter.convert(likeService.save(like));
    }

    @Override
    public void deleteLike(int postId) {
        User user = userFacade.getCurrentUser();
        Like like = likeService.getLikeByPostAndUser(postId, user.getId());
        likeService.delete(like);
    }

    @Override
    public void markPostsLikedByCurrentUser(List<Post> posts) {
        User user = userFacade.getCurrentUser();
        List<Like> likes = user.getLikes();
        Set<Post> likedPosts = likes.stream()
                .map(Like::getPost)
                .collect(Collectors.toSet());
        Set<Post> postSet = Set.copyOf(posts);
        posts.stream()
                .filter(post -> likedPosts.contains(post))
                .forEach(post -> post.setLikedByCurrentUser(true));
    }
}
