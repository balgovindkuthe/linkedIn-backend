package com.linkedin.social.service;

import com.linkedin.social.entity.Post;
import org.springframework.data.domain.Page;

public interface FeedService {

    Page<Post> getHomeFeed(int page, int size);
}
