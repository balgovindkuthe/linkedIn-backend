package com.linkedin.social.mapper;

import com.linkedin.social.dto.ProfileResponse;
import com.linkedin.social.entity.Profile;

public class ProfileMapper {

    public static ProfileResponse toDto(Profile profile) {
        return ProfileResponse.builder()
                .userId(profile.getUserId())
                .name(profile.getName())
                .headline(profile.getHeadline())
                .location(profile.getLocation())
                .profileImageUrl(profile.getProfileImageUrl())
                .about(profile.getAbout())
                .build();
    }
}
