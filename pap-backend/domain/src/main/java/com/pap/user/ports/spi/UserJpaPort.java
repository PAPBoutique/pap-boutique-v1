package com.pap.user.ports.spi;

import com.pap.product.model.PageableContent;
import com.pap.user.model.UserDomainObject;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserJpaPort {
    List<UserDomainObject> addUsers(List<UserDomainObject> users) ;
    UserDomainObject updateUser(Long id, UserDomainObject userDomainObject) ;

    Long getUsersCount();

    List<Object[]> getCountUsersPerMonth();

    PageableContent<UserDomainObject> findAllByPage(int page,int size);
    PageableContent<UserDomainObject> findAllByFilter(int page,int size,String filter);
    void deleteUser(Long id);
    UserDomainObject getUserById(Long id);
}
