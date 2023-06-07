package com.pap.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
@Builder
public class UserDomainObject {
    private Long id ;
    private String username ;
    private String email ;
    private String password ;
    private Role role ;
    private String address ;
    private Long phoneNum ;

}
