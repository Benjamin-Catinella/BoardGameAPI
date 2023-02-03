package com.quack.boardgameapi.factory;

import com.quack.boardgameapi.entity.TokenPositionEntity;
import fr.le_campus_numerique.square_games.engine.Token;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

public class TokenPositionEntityFactory {
    /**
     * Converts a Token to a TokenPositionEntity using its position owner and name
     *
     * @param token
     * @return The TokenPositionEntity from the provided Token
     */
    public static TokenPositionEntity from(@NotNull Token token) {
        TokenPositionEntity newTokenPosition = new TokenPositionEntity();
        newTokenPosition.setOwner(token.getOwnerId().isPresent() ? UserEntityFactory.from(token.getOwnerId().get()) : null );
        newTokenPosition.setTokenName(token.getName());
        //Set X position, can throw nullpointer, if so does nothing
        try {
            newTokenPosition.setX(token.getPosition().x());
            newTokenPosition.setY(token.getPosition().y());
        }catch (NullPointerException nullPointerException){}
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
        if(!tokens.isEmpty()){
            for (Token token: tokens) {
                t.add(TokenPositionEntityFactory.from(token));
            }
        }
        return t;
    }
}
