package com.quack.boardgameapi.Controller;

import com.quack.boardgameapi.Data.Interfaces.UserDAO;
import com.quack.boardgameapi.Data.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController implements Controller<UserDTO> {

    @Autowired
    private UserDAO userDAO;

    @Override
    @GetMapping("/users")
    public List<UserDTO> getAll() {
        return userDAO.getAll();
    }

    @GetMapping("/users/{id}")
    public UserDTO get(@PathVariable int id) {
        try {
            UserDTO user = userDAO.select(id);
            if(user != null){
                return user;
            }else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                );
            }
        }catch (SQLException e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
