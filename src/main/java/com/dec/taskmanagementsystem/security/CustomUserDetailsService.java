package com.dec.taskmanagementsystem.security;

import com.dec.taskmanagementsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * An Implementation service class of UserDetailsService
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    /**
     * @param usernameOrEmail contains user's username or email
     * @return User of spring security
     * @throws UsernameNotFoundException If user not exists in database.
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        var user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found by username or email")
                );


        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));

        return new User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}