package com.progerslifes.diplom.services.impl;

import com.progerslifes.diplom.entity.Like;
import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.repository.post.PostRepository;
import com.progerslifes.diplom.services.LikeService;
import com.progerslifes.diplom.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgersLifesPostService implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LikeService likeService;

    @Value("${page.explore.size}")
    private int size;

    @Override
    public List<Post> getPosts(User user) {
        return postRepository.findPostByUser(user);
    }

    @Override
    public List<Post> getPosts(List<User> users) {
        return postRepository.findPostByUserIn(users);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> getMostRelevantPosts() {
        PageRequest pageRequest = PageRequest.of(0, size);
        return postRepository.findAllByOrderByLikes(pageRequest).getContent();
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    @Override
    public void deletePost(int postId) {
        Post post = postRepository.findPostById(postId);
        List<Like> likes = new ArrayList<>(post.getLikes());
        for (Like like : likes) {
            likeService.delete(like);
        }
        postRepository.delete(post);
    }
}
