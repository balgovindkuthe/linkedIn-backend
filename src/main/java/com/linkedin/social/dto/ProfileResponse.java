package com.linkedin.social.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {

    private Long userId;
    private String name;
    private String headline;
    private String location;
    private String profileImageUrl;
    private String about;
}
