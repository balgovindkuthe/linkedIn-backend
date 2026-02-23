package com.linkedin.social.repository;

import com.linkedin.social.entity.Post;
import com.linkedin.social.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserOrderByCreatedAtDesc(Users user);
    List<Post> findAllByOrderByCreatedAtDesc();
    Page<Post> findByUserInOrderByCreatedAtDesc(
            List<Users> users,
            Pageable pageable
    );
    List<Post> findByContentContainingIgnoreCase(String content);
}


