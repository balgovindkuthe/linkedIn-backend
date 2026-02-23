package com.linkedin.social.controller;

import com.linkedin.social.dto.PostSearchResponse;
import com.linkedin.social.dto.UsersSearchResponse;
import com.linkedin.social.entity.Users;
import com.linkedin.social.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/users")
    public List<UsersSearchResponse> searchUsers(
            @RequestParam String keyword) {

        return searchService.searchUsers(keyword);
    }

    @GetMapping("/posts")
    public List<PostSearchResponse> searchPosts(
            @RequestParam String keyword) {

        return searchService.searchPosts(keyword);
    }
}
