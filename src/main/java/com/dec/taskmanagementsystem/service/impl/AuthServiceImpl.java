package com.dec.taskmanagementsystem.service.impl;

import com.dec.taskmanagementsystem.dto.Login;
import com.dec.taskmanagementsystem.dto.Register;
import com.dec.taskmanagementsystem.entity.User;
import com.dec.taskmanagementsystem.exception.ApiException;
import com.dec.taskmanagementsystem.repository.UserRepository;
import com.dec.taskmanagementsystem.security.AuthResponse;
import com.dec.taskmanagementsystem.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse register(Register register) {
       if (userRepository.existsByEmail(register.getEmail())) throw new ApiException(HttpStatus.BAD_REQUEST, "Email has been already used.");
       if (userRepository.existsByUsername(register.getUsername())) throw new ApiException(HttpStatus.BAD_REQUEST, "Username already taken.");

       User user = buildUser(register);
       User savedUser = userRepository.save(user);

       return new AuthResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getRole());
    }

    @Override
    public Optional<User> login(Login login) {
        return userRepository.findByUsernameOrEmail(login.getUsername(), login.getUsername())
                .filter(user -> passwordEncoder.matches(login.getPassword(), user.getPassword()));
    }

    private User buildUser(Register register){
        User user = new User();
        user.setEmail(register.getEmail());
        user.setUsername(register.getUsername());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setRole("USER");

        return user;
    }
}