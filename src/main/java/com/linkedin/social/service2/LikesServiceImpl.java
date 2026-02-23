package com.linkedin.social.service2;

import com.linkedin.social.entity.Likes;
import com.linkedin.social.entity.Post;
import com.linkedin.social.entity.Users;
import com.linkedin.social.repository.LikesRepository;
import com.linkedin.social.repository.PostRepository;
import com.linkedin.social.repository.UsersRepository;
import com.linkedin.social.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likeRepository;
    private final UsersRepository usersRepository;
    private final PostRepository postRepository;

    private Users getCurrentUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public String likeOrUnlikePost(Long postId) {

        Users user = getCurrentUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return likeRepository.findByUserAndPost(user, post)
                .map(existingLike -> {
                    likeRepository.delete(existingLike);
                    return "Post unliked";
                })
                .orElseGet(() -> {
                    Likes like = Likes.builder()
                            .user(user)
                            .post(post)
                            .build();
                    likeRepository.save(like);
                    return "Post liked";
                });
    }

    @Override
    public long getLikeCount(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return likeRepository.countByPost(post);
    }
}
