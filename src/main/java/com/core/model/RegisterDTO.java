package com.core.model;

import com.core.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
