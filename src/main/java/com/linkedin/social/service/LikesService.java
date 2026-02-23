package com.linkedin.social.service;

public interface LikesService {

    String likeOrUnlikePost(Long postId);

    long getLikeCount(Long postId);
}
