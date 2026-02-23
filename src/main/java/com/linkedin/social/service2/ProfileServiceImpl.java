package com.linkedin.social.service2;

import com.linkedin.social.dto.ProfileRequest;
import com.linkedin.social.dto.ProfileResponse;
import com.linkedin.social.entity.Profile;
import com.linkedin.social.entity.Users;
import com.linkedin.social.mapper.ProfileMapper;
import com.linkedin.social.repository.ProfileRepository;
import com.linkedin.social.repository.UsersRepository;
import com.linkedin.social.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UsersRepository usersRepository;

    private Users getCurrentUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public ProfileResponse getMyProfile() {
        Users user = getCurrentUser();

        Profile profile = profileRepository
                .findByUserId(user.getId())
                .orElseGet(() -> createDefaultProfile(user));

        return ProfileMapper.toDto(profile);
    }

    @Override
    public ProfileResponse updateMyProfile(ProfileRequest request) {
        Users user = getCurrentUser();

        Profile profile = profileRepository
                .findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setName(request.getName());
        profile.setHeadline(request.getHeadline());
        profile.setLocation(request.getLocation());
        profile.setAbout(request.getAbout());

        profileRepository.save(profile);

        return ProfileMapper.toDto(profile);
    }

    private Profile createDefaultProfile(Users user) {
        Profile profile = Profile.builder()
                .userId(user.getId())
                .name(user.getUsername())
                .build();

        return profileRepository.save(profile);
    }
}
