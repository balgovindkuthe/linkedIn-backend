package com.linkedin.social.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class PostResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
}
