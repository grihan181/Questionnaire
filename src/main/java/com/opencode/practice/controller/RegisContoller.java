package com.opencode.practice.controller;

import com.opencode.practice.model.Developer;
import com.opencode.practice.model.User;
import com.opencode.practice.repos.UserRepositorySecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisContoller {

    @Autowired
    private   UserRepositorySecurity userRepositorySecurity;
    @PostMapping
    @PreAuthorize("hasAuthority('developers:read')")
    public void create(@RequestBody User user) {
        userRepositorySecurity.save(user);
    }
}
