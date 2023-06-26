package com.pap.user.jpa;

import com.pap.product.model.PageableContent;
import com.pap.user.jpa.entity.UserEntity;
import com.pap.user.jpa.exception.UserNotFoundException;
import com.pap.user.jpa.mapper.UserMapper;
import com.pap.user.jpa.mapper.UserPageableMapper;
import com.pap.user.jpa.repository.UserRepository;
import com.pap.user.model.UserDomainObject;
import com.pap.user.ports.spi.UserJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserJpaAdapter implements UserJpaPort {
    private final UserRepository userRepository ;

    private final PasswordEncoder passwordEncoder ;
    @Override
    public List<UserDomainObject> addUsers(List<UserDomainObject> usersDomainObject) {
        List<UserEntity> userEntityList = UserMapper.INSTANCE.usersDomainObjectToUserEntity(usersDomainObject);

        for (UserEntity userEntity : userEntityList) {
            if (userRepository.findByUsername(userEntity.getUsername()).isPresent() ||
                    userRepository.findByUsername(userEntity.getEmail()).isPresent()) {
                throw new RuntimeException("User already exists");
            }
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userRepository.save(userEntity);
        }

        return UserMapper.INSTANCE.usersToUserDomainObjectList(userEntityList);
    }
    @Override
    public UserDomainObject updateUser(Long id, UserDomainObject userDomainObject) throws UserNotFoundException {
        Optional<UserEntity> existingUser = userRepository.findById(id);
        if (existingUser.isPresent())
        {
            UserEntity updatedUser = existingUser.get();
            updatedUser.setUsername(userDomainObject.getUsername());
            updatedUser.setEmail(userDomainObject.getEmail());
            updatedUser.setAddress(userDomainObject.getAddress());
            updatedUser.setPhoneNum(userDomainObject.getPhoneNum());
            updatedUser.setRole(userDomainObject.getRole());
            UserEntity savedUserEntity = userRepository.save(updatedUser);
            return UserMapper.INSTANCE.toUserDomain(savedUserEntity);
        }
        else
        {
            throw new UserNotFoundException("User not found with the id : " + id);
        }
    }

    @Override
    public Long getUsersCount() {
        return userRepository.count();
    }

    @Override
    public List<Object[]> getCountUsersPerMonth() {
        return userRepository.getUsersCountPerMonth();
    }

    @Override
    public PageableContent<UserDomainObject> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> userPage = userRepository.findAll(pageable);
        return UserPageableMapper.INSTANCE.toUserDomainPage(userPage);
    }

    @Override
    public PageableContent<UserDomainObject> findAllByFilter(int page, int size, String filter) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> userPage = userRepository.findAllByNameContainingIgnoreCase(filter,pageable);
        return UserPageableMapper.INSTANCE.toUserDomainPage(userPage);
    }

    @Override
    public void deleteUser(Long id) {
        if(getUserById(id)!=null){
            userRepository.deleteById(id);
        }
    }

    @Override
    public UserDomainObject getUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found "));
        return UserMapper.INSTANCE.toUserDomain(user);
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }
}
