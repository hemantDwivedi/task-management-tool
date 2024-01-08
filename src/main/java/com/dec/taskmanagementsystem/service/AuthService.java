package com.dec.taskmanagementsystem.service;

import com.dec.taskmanagementsystem.dto.Login;
import com.dec.taskmanagementsystem.dto.Register;
import com.dec.taskmanagementsystem.entity.User;
import com.dec.taskmanagementsystem.security.AuthResponse;

import java.util.Optional;

/**
 * Interface contains methods for user authentication.
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

public interface AuthService {
    /**
     * An abstract class for user's registration
     * @param register Register object
     * @return AuthResponse - id, username, and role
     */
    AuthResponse register(Register register);

    /**
     * An abstract class for user's login
     * @param login Login object
     * @return the User or null
     */
    Optional<User> login(Login login);
}