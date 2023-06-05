package com.pap.user.ports.spi;

import com.pap.user.model.UserDomainObject;

import java.util.List;

public interface UserJpaPort {
    List<UserDomainObject> addUsers(List<UserDomainObject> users) ;
}
