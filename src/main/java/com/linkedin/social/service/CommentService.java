package com.linkedin.social.service;

import com.linkedin.social.dto.CommentRequest;
import com.linkedin.social.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment addComment(Long postId, CommentRequest request);

    Comment updateComment(Long commentId, CommentRequest request);

    void deleteComment(Long commentId);

    List<Comment> getCommentsByPost(Long postId);
}
