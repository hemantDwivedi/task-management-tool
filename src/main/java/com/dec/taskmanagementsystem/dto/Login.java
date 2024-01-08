package com.dec.taskmanagementsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used for login.
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @NotEmpty(message = "Invalid: username cannot be empty.")
    private String usernameOrEmail;
    @NotEmpty(message = "Invalid: password cannot be empty")
    private String password;
}