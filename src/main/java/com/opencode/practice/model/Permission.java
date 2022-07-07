package com.opencode.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

public enum Permission {
    DEVELOPERS_WRITE("developers:write"),
    DEVELOPERS_READ("developers:read");

    @Getter
    private final String permission;

}
