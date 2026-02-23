package com.linkedin.social.controller;

import com.linkedin.social.dto.ProfileRequest;
import com.linkedin.social.dto.ProfileResponse;
import com.linkedin.social.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/me")
    public ProfileResponse getMyProfile() {
        return profileService.getMyProfile();
    }

    @PutMapping("/me")
    public ProfileResponse updateMyProfile(
            @RequestBody ProfileRequest request
    ) {
        return profileService.updateMyProfile(request);
    }
}
