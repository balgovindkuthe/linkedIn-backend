package com.linkedin.social.controller;

import com.linkedin.social.entity.Users;
import com.linkedin.social.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @PutMapping("/block/{userId}")
    public String blockUser(@PathVariable Long userId) {
        adminService.blockUser(userId);
        return "User blocked successfully";
    }

    @PutMapping("/unblock/{userId}")
    public String unblockUser(@PathVariable Long userId) {
        adminService.unblockUser(userId);
        return "User unblocked successfully";
    }

    @DeleteMapping("/post/{postId}")
    public String deletePost(@PathVariable Long postId) {
        adminService.deletePost(postId);
        return "Post deleted by admin";
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return adminService.getAllUsers();
    }

    @PutMapping("/make-admin/{userId}")
    public String makeAdmin(@PathVariable Long userId) {
        adminService.makeAdmin(userId);
        return "User promoted to ADMIN";
    }
}
