package com.pap.user.security.impl;

import com.pap.user.jpa.entity.UserEntity;
import com.pap.user.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("No user found with this username"));
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        Set<GrantedAuthority> authoritySet = new HashSet<>();
        authoritySet.add(authority);
        return new User(
                user.getUsername(),
                user.getPassword(),
                true,true,true,true,
                authoritySet
        );
    }
}
