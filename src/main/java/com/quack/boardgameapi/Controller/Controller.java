package com.quack.boardgameapi.Controller;

import com.quack.boardgameapi.Data.Interfaces.DTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface Controller <T extends DTO>{
    @GetMapping
    List<T> getAll();
}
