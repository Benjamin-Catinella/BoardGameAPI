package com.quack.boardgameapi.factory;

import com.quack.boardgameapi.entity.TokenPositionEntity;
import com.quack.boardgameapi.entity.UserEntity;
import fr.le_campus_numerique.square_games.engine.Token;
import fr.le_campus_numerique.square_games.engine.TokenPosition;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TokenPositionFactory {
    public static TokenPosition<UserEntity> from(TokenPositionEntity tokenEntityPosition){
        return new TokenPosition<>(tokenEntityPosition.getOwner(),tokenEntityPosition.getTokenName(),tokenEntityPosition.getX(), tokenEntityPosition.getY());
    }

    public static Collection<TokenPosition<UserEntity>> from(Collection<TokenPositionEntity> tokenPositionEntityCollection){
        List<TokenPosition<UserEntity>> tokenPositions = new ArrayList<>();
        for (TokenPositionEntity tokenPosition : tokenPositionEntityCollection){
            tokenPositions.add(TokenPositionFactory.from(tokenPosition));
        }
        return tokenPositions;
    }
}
