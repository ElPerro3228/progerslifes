package com.progerslifes.diplom.repository.post;

import com.progerslifes.diplom.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    @Query(value = "SELECT * FROM likes WHERE user_id = :user_id AND post_id = :post_id", nativeQuery = true)
    Like findLikeByUserIdAndPostId(@Param("user_id") Integer userId, @Param("post_id") Integer postId);
}
