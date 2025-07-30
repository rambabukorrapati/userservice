package com.kamala.userservice.controller;


import com.kamala.userservice.model.User;
import com.kamala.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody @Valid User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}
