package com.linkedin.social.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostSearchResponse {

    private Long postId;
    private String content;
    private String username;
}
