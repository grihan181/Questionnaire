package com.opencode.practice.audit;

import org.springframework.data.domain.AuditorAware;

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