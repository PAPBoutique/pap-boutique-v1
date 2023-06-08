package com.pap.user.ports.api;

import com.pap.product.model.PageableContent;
import com.pap.user.model.UserDomainObject;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserServicePort {
    List<UserDomainObject> addUsers(List<UserDomainObject> users) ;

    UserDomainObject updateUser(Long id, UserDomainObject userDomainObject);

    PageableContent<UserDomainObject> findAllByPage(int page,int size,String filter);
    void deleteUser(Long id);
}
