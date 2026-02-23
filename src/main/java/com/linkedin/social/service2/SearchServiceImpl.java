package com.linkedin.social.service2;

import com.linkedin.social.dto.PostSearchResponse;
import com.linkedin.social.dto.UsersSearchResponse;
import com.linkedin.social.repository.PostRepository;
import com.linkedin.social.repository.UsersRepository;
import com.linkedin.social.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final UsersRepository usersRepository;
    private final PostRepository postRepository;

    @Override
    public List<UsersSearchResponse> searchUsers(String keyword) {

        return usersRepository
                .findByUsernameContainingIgnoreCase(keyword)
                .stream()
                .map(user -> UsersSearchResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<PostSearchResponse> searchPosts(String keyword) {

        return postRepository
                .findByContentContainingIgnoreCase(keyword)
                .stream()
                .map(post -> PostSearchResponse.builder()
                        .postId(post.getId())
                        .content(post.getContent())
                        .username(post.getUser().getUsername())
                        .build())
                .collect(Collectors.toList());
    }
}
