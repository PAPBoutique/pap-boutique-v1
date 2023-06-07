package com.pap.user.rest.controller;

import com.pap.user.model.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {
    @GetMapping("/roles")
    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }
}
