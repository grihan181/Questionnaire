package com.opencode.practice.controller;

import com.opencode.practice.model.TestZoo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/security")
public class TestSecurityController {

    private List<TestZoo> zoos = Stream.of(
            new TestZoo(1, "vova", "8", "Dog"),
            new TestZoo(2, "danil", "10", "Cat")
    ).collect(Collectors.toList());

    @GetMapping
    public List<TestZoo> getAll() {
        return zoos;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public TestZoo getById(@PathVariable long id) {
        return zoos.stream().filter((x -> x.getId() == id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public TestZoo createZoo(TestZoo zoo) {
        zoos.add(zoo);
        return zoo;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteById(Long id) {
        zoos.removeIf(x -> x.getId() == id);


    }
}

