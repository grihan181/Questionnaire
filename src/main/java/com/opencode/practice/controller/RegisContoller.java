package com.opencode.practice.controller;

import com.opencode.practice.model.User;
import com.opencode.practice.repos.UserRepositorySecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisContoller {

    @Autowired
    private UserRepositorySecurity userRepositorySecurity;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @PostMapping
    @PreAuthorize("hasAuthority('developers:read')")
    public void create(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepositorySecurity.save(user);
    }


}
