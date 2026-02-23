package com.linkedin.social.service2;

import com.linkedin.social.entity.Roles;
import com.linkedin.social.entity.Users;
import com.linkedin.social.repository.PostRepository;
import com.linkedin.social.repository.RolesRepository;
import com.linkedin.social.repository.UsersRepository;
import com.linkedin.social.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UsersRepository usersRepository;
    private final PostRepository postRepository;
    private final RolesRepository rolesRepository;

    @Override
    public void blockUser(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBlocked(true);
        usersRepository.save(user);
    }

    @Override
    public void unblockUser(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBlocked(false);
        usersRepository.save(user);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void makeAdmin(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Roles adminRole = rolesRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

        user.getRoles().add(adminRole);
        usersRepository.save(user);
    }
}
