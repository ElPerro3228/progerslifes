package com.progerslifes.diplom.services.impl;

import com.progerslifes.diplom.entity.Like;
import com.progerslifes.diplom.repository.post.LikeRepository;
import com.progerslifes.diplom.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public void delete(Like like) {
        likeRepository.deleteById(like.getId());
    }

    @Override
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like getLikeByPostAndUser(int postId, int userId) {
        return likeRepository.findLikeByUserIdAndPostId(userId, postId);
    }

    @Override
    public int getLikesCountByPost(int postId) {
        return likeRepository.getCountByPost(postId);
    }
}
