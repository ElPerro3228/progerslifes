package com.progerslifes.diplom.services;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.entity.User;

import java.util.List;

public interface PostService {
    List<Post> getPosts(User user);
    List<Post> getPosts(List<User> users);
    void save(Post post);

    List<Post> getMostRelevantPosts();

    Post getPostById(int id);

    void deletePost(int postId);
}
