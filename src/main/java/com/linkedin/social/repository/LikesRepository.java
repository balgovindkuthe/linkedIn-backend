package com.linkedin.social.repository;

import com.linkedin.social.entity.Likes;
import com.linkedin.social.entity.Post;
import com.linkedin.social.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByUserAndPost(Users user, Post post);

    long countByPost(Post post);
}
