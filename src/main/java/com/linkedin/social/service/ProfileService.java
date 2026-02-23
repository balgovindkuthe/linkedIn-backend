package com.linkedin.social.service;


import com.linkedin.social.dto.ProfileRequest;
import com.linkedin.social.dto.ProfileResponse;

public interface ProfileService {

    ProfileResponse getMyProfile();

    ProfileResponse updateMyProfile(ProfileRequest request);
}
