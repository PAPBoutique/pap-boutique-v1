package com.pap.user.ports.spi;

import com.pap.product.model.PageableContent;
import com.pap.user.model.UserDomainObject;

import java.util.List;

public interface UserJpaPort {
    List<UserDomainObject> addUsers(List<UserDomainObject> users) ;
    PageableContent<UserDomainObject> findAllByPage(int page,int size);
    PageableContent<UserDomainObject> findAllByFilter(int page,int size,String filter);
    String deleteUser(Long id);
    UserDomainObject getUserById(Long id);
}
