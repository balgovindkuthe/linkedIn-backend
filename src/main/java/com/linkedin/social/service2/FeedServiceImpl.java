package com.linkedin.social.service2;

import com.linkedin.social.entity.Post;
import com.linkedin.social.entity.Users;
import com.linkedin.social.repository.PostRepository;
import com.linkedin.social.repository.UsersRepository;
import com.linkedin.social.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

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
    public Page<Post> getHomeFeed(int page, int size) {

        Users currentUser = getCurrentUser();

        List<Users> feedUsers = new ArrayList<>();
        feedUsers.add(currentUser);

        feedUsers.addAll(currentUser.getFollowing());

        return postRepository.findByUserInOrderByCreatedAtDesc(
                feedUsers,
                PageRequest.of(page, size)
        );
    }
}
