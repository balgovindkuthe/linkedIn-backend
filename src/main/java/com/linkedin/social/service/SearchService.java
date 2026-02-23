package com.linkedin.social.service;

import com.linkedin.social.dto.PostSearchResponse;
import com.linkedin.social.dto.UsersSearchResponse;
import com.linkedin.social.entity.Users;

import java.util.List;

public interface SearchService {

    List<UsersSearchResponse> searchUsers(String keyword);

    List<PostSearchResponse> searchPosts(String keyword);
}
