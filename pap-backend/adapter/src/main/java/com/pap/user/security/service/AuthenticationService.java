package com.pap.user.security.service;

import com.pap.user.jpa.entity.UserEntity;
import com.pap.user.jpa.mapper.UserMapper;
import com.pap.user.jpa.repository.UserRepository;
import com.pap.user.model.Role;
import com.pap.user.model.UserDomainObject;
import com.pap.user.rest.dto.UserDto;
import com.pap.user.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService ;
    private final AuthenticationManager authenticationManager ;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse login(UserDto request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        UserEntity user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(UserMapper.INSTANCE.toUserDomain(user))
                .build();
    }

    public AuthenticationResponse signup(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent() || userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Username already exists.");
        }
        UserEntity newUser = UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .address(userDto.getAddress())
                .phoneNum(userDto.getPhoneNum())
                .role(Role.CLIENT)
                .build();
        userRepository.save(newUser);
        UserDomainObject userDomain = UserMapper.INSTANCE.toUserDomain(newUser);
        String jwtToken = jwtService.generateToken(newUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(userDomain)
                .build();
    }




}
