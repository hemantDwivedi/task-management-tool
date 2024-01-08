package com.dec.taskmanagementsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used for user's registration.
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Register {
    @NotEmpty(message = "Invalid: username cannot be empty")
    private String username;
    @NotEmpty(message = "Invalid: email cannot be empty")
    private String email;
    @NotEmpty(message = "Invalid: password cannot be empty")
    private String password;
}