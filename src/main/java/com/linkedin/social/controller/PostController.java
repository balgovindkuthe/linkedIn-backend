package com.linkedin.social.controller;

import com.linkedin.social.dto.PostRequest;
import com.linkedin.social.entity.Post;
import com.linkedin.social.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public Post createPost(@RequestBody PostRequest request) {
        return postService.createPost(request);
    }

    @GetMapping("/me")
    public List<Post> myPosts() {
        return postService.getMyPosts();
    }

    @GetMapping("/feed")
    public List<Post> feed() {
        return postService.getFeed();
    }
}
