package com.pap.user.rest.controller;

import com.pap.product.model.PageableContent;
import com.pap.user.model.UserDomainObject;
import com.pap.user.ports.api.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    private final UserServicePort userServicePort ;

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
