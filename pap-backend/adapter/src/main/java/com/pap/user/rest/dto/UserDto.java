package com.pap.user.rest.dto;

import com.pap.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String username ;
    private String email ;
    private String address ;
    private Long phoneNum ;
    private String password ;
    private Role role;
}
