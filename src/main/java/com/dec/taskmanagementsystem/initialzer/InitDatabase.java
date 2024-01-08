package com.dec.taskmanagementsystem.initialzer;


import com.dec.taskmanagementsystem.entity.User;
import com.dec.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * The InitDatabase class add values into user table
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class InitDatabase implements CommandLineRunner {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    /**
     * A CommandLineRunner method
     * @param args Command line arguments
     */
    @Override
    public void run(String... args) {
        List<User> USERS = Arrays.asList(
                new User(1L, "admin", "admin@gmail.com", passwordEncoder.encode("admin"), "ADMIN", null),
                new User(2L, "user", "user@gmail.com", passwordEncoder.encode("user"), "USER", null)
        );
        if (!repository.findAll().isEmpty()) {
            return;
        }
        repository.saveAll(USERS);
        log.info("Database initialized");
    }

}