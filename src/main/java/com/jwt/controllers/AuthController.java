package com.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.jwt.models.AppUser;
import com.jwt.security.JwtHelper;
import com.jwt.services.UserService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserService userService;

    /**
     * Register new user with encoded password
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String email = body.get("email");
        String username = body.get("username"); // Added username field
        String password = body.get("password");

        AppUser user = userService.registerUser(name, email, username, password);
        return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "user", user.getUsername()
        ));
    }

    /**
     * Authenticate and generate JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username"); // ✅ authenticate using username
        String password = body.get("password");

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid username or password"));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtHelper.generateToken(userDetails);
        
        //Print token in console
        System.out.println("✅ JWT Token generated for user: " + userDetails.getUsername());
        System.out.println("🔐 Token: " + token);
        
        return ResponseEntity.ok(Map.of(
                "jwtToken", token,
                "username", userDetails.getUsername()
        ));
    }
}
