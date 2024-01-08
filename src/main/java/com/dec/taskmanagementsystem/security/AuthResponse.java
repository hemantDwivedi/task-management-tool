package com.dec.taskmanagementsystem.security;

/**
 * Custom response for successful authentication
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

public record AuthResponse(Long id, String username, String role){}