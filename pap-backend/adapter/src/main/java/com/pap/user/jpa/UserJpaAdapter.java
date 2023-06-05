package com.pap.user.jpa;

import com.pap.user.model.UserDomainObject;
import com.pap.user.ports.spi.UserJpaPort;

import java.util.List;

public class UserJpaAdapter implements UserJpaPort {
    @Override
    public List<UserDomainObject> addUsers(List<UserDomainObject> users) {
        return null;
    }
}
