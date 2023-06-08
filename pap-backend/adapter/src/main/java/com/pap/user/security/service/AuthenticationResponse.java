package com.pap.user.security.service;

import com.pap.user.model.UserDomainObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private UserDomainObject user ;
    private String token ;
}
