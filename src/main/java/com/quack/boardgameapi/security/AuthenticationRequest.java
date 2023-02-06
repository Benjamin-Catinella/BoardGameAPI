package com.quack.boardgameapi.security;

public record AuthenticationRequest(String username, String password) {
}
