package com.spring.blogging.basicSecurity;

import com.spring.blogging.entities.User;
import com.spring.blogging.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch user from database using email
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Return UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),    // Assuming the email is used as the username
                user.getPassword(),
                Collections.emptyList() // Add authorities/roles here if needed
        );
    }
}
