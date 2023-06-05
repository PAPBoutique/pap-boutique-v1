package com.pap.user.service;

import com.pap.user.model.UserDomainObject;
import com.pap.user.ports.api.UserServicePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServicePort {
    @Override
    public List<UserDomainObject> addUsers(List<UserDomainObject> users) {
        return null;
    }
}
