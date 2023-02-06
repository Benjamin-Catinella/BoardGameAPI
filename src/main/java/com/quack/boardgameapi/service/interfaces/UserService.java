package com.quack.boardgameapi.service.interfaces;

import com.quack.boardgameapi.data.dto.UserDTO;
import com.quack.boardgameapi.entity.UserEntity;
import com.quack.boardgameapi.security.AuthenticationRequest;

import java.util.Collection;

public interface UserService {

    Collection<UserEntity> getAllUsers();

    Collection<UserDTO> getAllUserDTOs();

    UserEntity createUser(AuthenticationRequest authenticationRequest);

    UserEntity createAdmin(AuthenticationRequest authenticationRequest);
}
