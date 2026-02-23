package com.linkedin.social.controller;

import com.linkedin.social.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/{postId}")
    public String likeOrUnlike(@PathVariable Long postId) {
        return likesService.likeOrUnlikePost(postId);
    }

    @GetMapping("/{postId}/count")
    public long likeCount(@PathVariable Long postId) {
        return likesService.getLikeCount(postId);
    }
}
