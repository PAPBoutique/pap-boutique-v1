package com.pap.user.rest.controller;

import com.pap.product.model.PageableContent;
import com.pap.user.model.UserDomainObject;
import com.pap.user.ports.api.UserServicePort;
import com.pap.user.rest.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.pap.user.rest.mapper.UserRestMapper;
import javax.validation.constraints.Min;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    private final UserServicePort userServicePort ;

    @PostMapping
    public List<UserDomainObject> addUsers(@RequestBody List<UserDto> userDTOS)
    {
        List<UserDomainObject> userDomainObjects = UserRestMapper.INSTANCE.convertToDomainObject(userDTOS);
        return userServicePort.addUsers(userDomainObjects);
    }

    @PutMapping("/update/{id}")
    public UserDomainObject updateProduct(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
            var domainObject = UserRestMapper.INSTANCE.converUserDtoToDomainObject(userDto);
            UserDomainObject updatedUser = userServicePort.updateUser(id, domainObject);
            updatedUser.setPassword(null);
            return updatedUser;
    }
    @GetMapping
    public PageableContent<UserDomainObject> getUsersByPage(
            @RequestParam @Min(value = 0 , message = "Page number must be greater than or equal 0") int page,
            @RequestParam int size,
            @RequestParam String filterValue){
        return userServicePort.findAllByPage(page,size,filterValue);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(
            @PathVariable Long id){
        userServicePort.deleteUser(id);
    }
}
