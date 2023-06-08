package com.pap.user.security.controller;

import com.pap.user.rest.dto.UserDto;
import com.pap.user.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class AuthenticationController {
    private final AuthenticationService authenticationService ;
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto user){
        return ResponseEntity.ok(authenticationService.login(user));
    }
}
