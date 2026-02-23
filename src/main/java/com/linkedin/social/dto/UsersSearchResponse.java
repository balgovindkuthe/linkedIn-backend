package com.linkedin.social.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersSearchResponse {

    private Long id;
    private String username;
    private String email;
}
