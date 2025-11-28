package com.trendbazaar.controller;

import com.trendbazaar.dto.LoginRequest;
import com.trendbazaar.dto.LoginResponse;
import com.trendbazaar.dto.RegisterRequest;
import com.trendbazaar.entity.User;
import com.trendbazaar.reposistory.UserRepository;
import com.trendbazaar.security.JwtUtil;
import com.trendbazaar.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        // Check if user with same email exists
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already in use");
        }

        // Create new user
        User user = new User();

        user.setEmail(registerRequest.getEmail());
        user.setFullName(registerRequest.getFullName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole()); // Role.USER or Role.ADMIN
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user) {
        log.info("user{}",user);
        // Authenticate username(email) and password
        try {
           Authentication authenticationManager1 = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
               user.getEmail(), user.getPassword()
                    )
            );
           log.info("toekn{}",authenticationManager1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        // Generate JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        final String token = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok((new LoginResponse(token,role,user.getEmail())));
    }
}
