package com.dec.taskmanagementsystem.controller;

import com.dec.taskmanagementsystem.dto.Login;
import com.dec.taskmanagementsystem.dto.Register;
import com.dec.taskmanagementsystem.entity.User;
import com.dec.taskmanagementsystem.security.AuthResponse;
import com.dec.taskmanagementsystem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody Register register){
        return ResponseEntity.ok(authService.register(register));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody Login login){
        Optional<User> user = authService.login(login);
        if (user.isPresent()){
            User presentUser = user.get();
            return ResponseEntity.ok(new AuthResponse(presentUser.getId(), presentUser.getUsername(), presentUser.getRole()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}