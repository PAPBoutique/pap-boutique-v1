package com.pap.user.rest.controller;

import com.pap.user.model.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {
    @GetMapping
    public List<String> getRoles() {
        return Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toList());
    }
}
