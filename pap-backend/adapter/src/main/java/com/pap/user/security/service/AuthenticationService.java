package com.pap.user.security.service;

import com.pap.user.jpa.entity.UserEntity;
import com.pap.user.jpa.mapper.UserMapper;
import com.pap.user.jpa.repository.UserRepository;
import com.pap.user.rest.dto.UserDto;
import com.pap.user.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService ;
    private final AuthenticationManager authenticationManager ;

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
}
