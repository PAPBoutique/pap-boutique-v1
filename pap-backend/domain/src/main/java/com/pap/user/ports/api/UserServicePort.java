package com.pap.user.ports.api;

import com.pap.product.model.PageableContent;
import com.pap.user.model.UserDomainObject;

import java.util.List;

public interface UserServicePort {
    List<UserDomainObject> addUsers(List<UserDomainObject> users) ;
    PageableContent<UserDomainObject> findAllByPage(int page,int size,String filter);
}
