package com.opencode.practice.controller;

import com.opencode.practice.exception.ExceptionData;
import com.opencode.practice.exception.NoSuchCountExeption;
import com.opencode.practice.model.Role;
import com.opencode.practice.model.Status;
import com.opencode.practice.model.User;
import com.opencode.practice.repos.UserRepositorySecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/regis")
public class RegisContoller {

    @Autowired
    private UserRepositorySecurity userRepositorySecurity;


    @PostMapping
    public void create(@RequestBody User user) {
        if (userRepositorySecurity.findByEmail(user.getEmail()) == null) {
            user.setPassword(String.valueOf(new BCryptPasswordEncoder(12).encode(user.getPassword())));
            user.setRole(Role.USER);
            user.setStatus(Status.ACTIVE);
            userRepositorySecurity.save(user);
        } else
            throw new NoSuchCountExeption("user with such email already exists\n");
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionData> handleExeption(NoSuchCountExeption exeption) {
        ExceptionData exceptionData = new ExceptionData();
        exceptionData.setInfo(exeption.getMessage());
        return new ResponseEntity<>(exceptionData, HttpStatus.FORBIDDEN);
    }


}
