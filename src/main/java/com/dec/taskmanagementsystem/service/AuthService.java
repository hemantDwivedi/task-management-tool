package com.dec.taskmanagementsystem.service;

import com.dec.taskmanagementsystem.dto.Login;
import com.dec.taskmanagementsystem.dto.Register;
import com.dec.taskmanagementsystem.entity.User;
import com.dec.taskmanagementsystem.security.AuthResponse;

import java.util.Optional;

public interface AuthService {
    AuthResponse register(Register register);
    Optional<User> login(Login login);
}