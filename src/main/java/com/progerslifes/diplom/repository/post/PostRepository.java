package com.progerslifes.diplom.repository.post;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE p.user = :user")
    List<Post> findPostByUser(@Param("user") User user);
}
