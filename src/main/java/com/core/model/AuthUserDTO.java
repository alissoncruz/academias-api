package com.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthUserDTO {
    private String token;
    private int level;
    private String expiresAt;
    private String user;
}
