package com.pap.user.jpa;

import com.pap.product.model.PageableContent;
import com.pap.user.jpa.entity.UserEntity;
import com.pap.user.jpa.exception.UserNotFoundException;
import com.pap.user.jpa.repository.UserRepository;
import com.pap.user.model.UserDomainObject;
import com.pap.user.ports.spi.UserJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserJpaAdapter implements UserJpaPort {
    private final UserRepository userRepository ;
    @Override
    public List<UserDomainObject> addUsers(List<UserDomainObject> users) {
        return null;
    }

    @Override
    public PageableContent<UserDomainObject> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> userPage = userRepository.findAll(pageable);
        //todo add a mapper
        return null;
    }

    @Override
    public PageableContent<UserDomainObject> findAllByFilter(int page, int size, String filter) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> userPage = userRepository.findAllByNameContainingIgnoreCase(filter,pageable);
        //todo add a mapper
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        return null;
    }

    @Override
    public UserDomainObject getUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found "));
        //todo add a mapper
        return null;
    }
}
