package com.practice.UserService.security;

import com.practice.UserService.domain.User;

import java.util.Map;

public interface JWTSecurityTokenGenerator {
    Map<String, String>generateToken(User user);
}
