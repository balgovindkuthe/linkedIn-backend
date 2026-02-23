package com.linkedin.social.service;

import com.linkedin.social.entity.Users;

import java.util.List;

public interface AdminService {

    void blockUser(Long userId);

    void unblockUser(Long userId);

    void deletePost(Long postId);

    List<Users> getAllUsers();

    void makeAdmin(Long userId);
}
