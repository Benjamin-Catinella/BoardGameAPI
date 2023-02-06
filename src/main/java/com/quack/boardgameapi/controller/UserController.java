package com.quack.boardgameapi.controller;

import com.quack.boardgameapi.data.dto.UserDTO;
import com.quack.boardgameapi.entity.UserEntity;
import com.quack.boardgameapi.security.AuthenticationRequest;
import com.quack.boardgameapi.security.Role;
import com.quack.boardgameapi.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/private")
//@RequestMapping("/api/public")
@Secured(Role.ROLE_ADMIN)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public Collection<UserEntity> getAllUsers(){
        return this.userService.getAllUsers();
    }
    @PostMapping("/users")
    public UserEntity createUser(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        return this.userService.createUser(authenticationRequest);
    }

    @PostMapping("/admins")
    public UserEntity createAdmin(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        return this.userService.createAdmin(authenticationRequest);
    }

    @GetMapping("/users/DTO")
    public Collection<UserDTO> getAllUserDTO(){
        return this.userService.getAllUserDTOs();
    }

}
