package com.progerslifes.diplom.services.impl;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.repository.post.PostRepository;
import com.progerslifes.diplom.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgersLifesPostService implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getPosts(User user) {
        return postRepository.findPostByUser(user);
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreationDateAsc();
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }
}
