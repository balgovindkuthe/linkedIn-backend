package com.linkedin.social.controller;

import com.linkedin.social.dto.CommentRequest;
import com.linkedin.social.entity.Comment;
import com.linkedin.social.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public Comment addComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest request) {
        return commentService.addComment(postId, request);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequest request) {
        return commentService.updateComment(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "Comment deleted successfully";
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }
}
