package com.linkedin.social.repository;

import com.linkedin.social.entity.Comment;
import com.linkedin.social.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
}
