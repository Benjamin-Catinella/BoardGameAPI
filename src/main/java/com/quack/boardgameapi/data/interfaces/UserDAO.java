package com.quack.boardgameapi.data.interfaces;


import com.quack.boardgameapi.data.dto.UserDTO;
import org.springframework.stereotype.Component;


@Component
public interface UserDAO extends DAO<UserDTO> {
}
