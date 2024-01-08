package com.dec.taskmanagementsystem.controller;

import com.dec.taskmanagementsystem.dto.Login;
import com.dec.taskmanagementsystem.dto.Register;
import com.dec.taskmanagementsystem.entity.User;
import com.dec.taskmanagementsystem.security.AuthResponse;
import com.dec.taskmanagementsystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This class authenticates against user's login or register details.
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    /**
     * handles user's registration
     * @param register contains user's details for registration username, email, and password
     * @return AuthResponse(id, username, role)
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody Register register){
        return ResponseEntity.ok(authService.register(register));
    }

    /**
     * handles user's login
     * @param login contains user's details for login such as username and password
     * @return AuthResponse(id, username, role)
     */
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