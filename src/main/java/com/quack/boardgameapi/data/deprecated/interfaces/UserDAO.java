package com.quack.boardgameapi.data.deprecated.interfaces;


import com.quack.boardgameapi.data.deprecated.dto.UserDTO;
import org.springframework.stereotype.Component;


@Component
public interface UserDAO extends DAO<UserDTO> {
}
