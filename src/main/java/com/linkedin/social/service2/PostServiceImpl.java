package com.linkedin.social.service2;

import com.linkedin.social.dto.PostRequest;
import com.linkedin.social.entity.Post;
import com.linkedin.social.entity.Users;
import com.linkedin.social.repository.PostRepository;
import com.linkedin.social.repository.UsersRepository;
import com.linkedin.social.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UsersRepository usersRepository;

    private Users getCurrentUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Post createPost(PostRequest request) {

        Users user = getCurrentUser();

        Post post = Post.builder()
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        return postRepository.save(post);
    }

    @Override
    public List<Post> getMyPosts() {
        Users user = getCurrentUser();
        return postRepository.findByUserOrderByCreatedAtDesc(user);
    }

    @Override
    public List<Post> getFeed() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}
