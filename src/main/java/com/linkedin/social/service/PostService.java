package com.linkedin.social.service;

import com.linkedin.social.dto.PostRequest;
import com.linkedin.social.entity.Post;

import java.util.List;

public interface PostService {

    Post createPost(PostRequest request);

    List<Post> getMyPosts();

    List<Post> getFeed();
}
