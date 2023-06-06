package com.pap.user.service;

import com.pap.product.model.PageableContent;
import com.pap.user.model.UserDomainObject;
import com.pap.user.ports.api.UserServicePort;
import com.pap.user.ports.spi.UserJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {
    private final UserJpaPort userJpaPort ;
    @Override
    public List<UserDomainObject> addUsers(List<UserDomainObject> users) {
        return userJpaPort.addUsers(users);
    }

    @Override
    public PageableContent<UserDomainObject> findAllByPage(int page, int size, String filter) {
        if(filter.isBlank())  return userJpaPort.findAllByPage(page,size);
        PageableContent<UserDomainObject> users = userJpaPort.findAllByFilter(page, size, filter);
        if (users.getContent()==null)  users.setContent(new ArrayList<>());
        return users;
    }

    @Override
    public void deleteUser(Long id) {
        userJpaPort.deleteUser(id);
    }
}
