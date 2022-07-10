/*
package com.opencode.practice.controller;

import com.opencode.practice.model.Role;
import com.opencode.practice.model.Status;
import com.opencode.practice.model.User;
import com.opencode.practice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/regis")
public class RegisContoller {

    @Autowired
    private UserRepo userRepositorySecurity;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @PostMapping
    public void create(@RequestBody User user) {
        user.setPassword(String.valueOf(new BCryptPasswordEncoder(12).encode(user.getPassword())));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userRepositorySecurity.save(user);
    }


}
*/
