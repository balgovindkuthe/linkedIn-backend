package com.linkedin.social.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {

    private String name;
    private String headline;
    private String location;
    private String about;
}
