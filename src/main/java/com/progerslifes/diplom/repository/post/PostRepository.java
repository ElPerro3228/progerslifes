package com.progerslifes.diplom.repository.post;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE p.user = :user ORDER BY p.creationDate DESC")
    List<Post> findPostByUser(@Param("user") User user);

    @Query("SELECT p FROM Post p WHERE p.user IN (:users) ORDER BY p.creationDate DESC")
    List<Post> findPostByUserIn(@Param("users") List<User> users);

    List<Post> findAllByOrderByCreationDateAsc();
}
