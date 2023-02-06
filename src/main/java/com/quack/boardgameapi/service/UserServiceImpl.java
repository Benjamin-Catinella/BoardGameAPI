package com.quack.boardgameapi.service;

import com.quack.boardgameapi.data.dto.UserDTO;
import com.quack.boardgameapi.entity.AuthorityEntity;
import com.quack.boardgameapi.entity.UserEntity;
import com.quack.boardgameapi.repository.AuthorityRepository;
import com.quack.boardgameapi.repository.UserRepository;
import com.quack.boardgameapi.security.AuthenticationRequest;
import com.quack.boardgameapi.security.Role;
import com.quack.boardgameapi.security.SecurityConfig;
import com.quack.boardgameapi.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository){
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Collection<UserEntity> getAllUsers() {
        return (Collection<UserEntity>) userRepository.findAll();
    }
    @Override
    public Collection<UserDTO> getAllUserDTOs() {
        return UserDTO.from(getAllUsers());
    }

    @Override
    public UserEntity createUser(AuthenticationRequest authenticationRequest) {
        String password = securityConfig.passwordEncoder().encode(authenticationRequest.password());
        List<AuthorityEntity> authorities = new ArrayList<>();
        authorities.add(authorityRepository.getByRole(Role.ROLE_USER));
        UserEntity user = new UserEntity()
                .setUuid(UUID.randomUUID())
                .setUsername(authenticationRequest.username())
                .setPassword(password)
                .setAuthorities(authorities)
                .setEmail("canard@coincoin.egg");
        return userRepository.save(user);
    }

    @Override
    public UserEntity createAdmin(AuthenticationRequest authenticationRequest) {
        String password = securityConfig.passwordEncoder().encode(authenticationRequest.password());
        List<AuthorityEntity> authorities = new ArrayList<>();
        authorities.add(authorityRepository.getByRole(Role.ROLE_ADMIN));
        UserEntity user = new UserEntity()
                .setUuid(UUID.randomUUID())
                .setUsername(authenticationRequest.username())
                .setPassword(password)
                .setAuthorities(authorities)
                .setEmail("canard@coincoin.egg");
        return userRepository.save(user);
    }
}
