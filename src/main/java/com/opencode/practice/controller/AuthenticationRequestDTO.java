package com.opencode.practice.controller;

import lombok.Data;
/**
 * @author Artem
 */
@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}
