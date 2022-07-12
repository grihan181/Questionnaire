package com.opencode.practice.model;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

import javax.swing.*;
import java.util.Optional;

/**
 * @author Artem,Grihan
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}