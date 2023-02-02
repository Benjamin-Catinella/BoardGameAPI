package com.quack.boardgameapi.factory;

import com.quack.boardgameapi.entity.TokenPositionEntity;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.ArrayList;
import java.util.Collection;

public class TokenPositionEntityFactory {
    /**
     * Converts a Token to a TokenPositionEntity using its position owner and name
     *
     * @param token
     * @return The TokenPositionEntity from the provided Token
     */
    public static TokenPositionEntity from(Token token) {
        TokenPositionEntity newTokenPosition = new TokenPositionEntity();
        // CAN BREAK TODO: CHANGE THIS CAN BREAK
        newTokenPosition.setOwner(UserEntityFactory.from(token.getOwnerId().get()));
        newTokenPosition.setTokenName(token.getName());
        newTokenPosition.setX(token.getPosition().x());
        newTokenPosition.setY(token.getPosition().y());
        return newTokenPosition;
    }

    /**
     * Converts a Collection of {Token} to a Collection of TokenPositionEntity for saving
     *
     * @param tokens
     * @return The newly converted TokenPositionEntity collection from the provided tokens
     */
    public static Collection<TokenPositionEntity> from(Collection<Token> tokens) {
        Collection<TokenPositionEntity> t = new ArrayList<>();
        for (Token token: tokens) {
            t.add(TokenPositionEntityFactory.from(token));
        }
        return t;
    }
}
