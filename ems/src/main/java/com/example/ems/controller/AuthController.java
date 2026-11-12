package com.example.ems.controller;

import com.example.ems.model.User;
import com.example.ems.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Basic registration and login. Passwords are stored as BCrypt hashes.
 * Note: this does not issue tokens (no JWT/Spring Security filter chain).
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public record RegisterRequest(@NotBlank(message = "Name is required") String name,
                                  @NotBlank(message = "Email is required") @Email(message = "Enter a valid email") String email,
                                  @Size(min = 6, message = "Password must be at least 6 characters") String password) {}

    public record LoginRequest(@NotBlank(message = "Email is required") String email,
                               @NotBlank(message = "Password is required") String password) {}

    private final UserRepository users;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository users) {
        this.users = users;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody RegisterRequest req) {
        if (users.findByEmail(req.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }
        User u = new User();
        u.setName(req.name());
        u.setEmail(req.email());
        u.setPasswordHash(encoder.encode(req.password()));
        return users.save(u);
    }

    @PostMapping("/login")
    public User login(@Valid @RequestBody LoginRequest req) {
        User u = users.findByEmail(req.email())
                .filter(found -> encoder.matches(req.password(), found.getPasswordHash()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
        return u;
    }
}
