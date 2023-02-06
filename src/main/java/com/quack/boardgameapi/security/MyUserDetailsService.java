package com.quack.boardgameapi.security;

import com.quack.boardgameapi.entity.UserEntity;
import com.quack.boardgameapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.getByUsername(username);
        if (user == null){
            return null;
        }
        LOGGER.debug("Found user with username " + username );
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
