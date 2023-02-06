package com.quack.boardgameapi.controller;

import com.quack.boardgameapi.data.dto.UserDTO;
import com.quack.boardgameapi.factory.UserEntityFactory;
import com.quack.boardgameapi.security.AuthenticationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import com.quack.boardgameapi.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/public")
public class AuthenticationApi {
    private final AuthenticationManager authenticationManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationApi.class);
    private final Long tokenLifetime = 3600 * 1000L;

    public AuthenticationApi(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid AuthenticationRequest request) {
        try {
            final Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.username(),
                                    request.password()
                            )
                    );
            authenticate.getPrincipal();
            LOGGER.debug(authenticate.toString());

            // UserEntity = objet Entity que vous avez créé et renvoyé par loadByUserName()
            final UserEntity user = UserEntityFactory.from((User) authenticate.getPrincipal());
            LOGGER.debug(user.toString());

            final String token = Jwts.builder().setSubject(authenticate
                            .getName()).claim("authorities", authenticate
                            .getAuthorities().stream().map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())).setIssuedAt(new Date())
                            .setExpiration(new Date(System.currentTimeMillis() + tokenLifetime))
                            .signWith(SignatureAlgorithm.HS512, "canard".getBytes())
                            .compact();
            // TODO : conversion du user en UserDTO
            LOGGER.debug(token);
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            "Bearer " + token
                    )
                    .body(UserDTO.from(user));
        } catch (BadCredentialsException ex) {
            LOGGER.error(Arrays.toString(ex.getStackTrace()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}