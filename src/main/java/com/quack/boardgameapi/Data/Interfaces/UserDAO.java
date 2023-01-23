package com.quack.boardgameapi.Data.Interfaces;


import com.quack.boardgameapi.Data.DTO.UserDTO;
import org.springframework.stereotype.Component;


@Component
public interface UserDAO extends DAO<UserDTO> {
}
