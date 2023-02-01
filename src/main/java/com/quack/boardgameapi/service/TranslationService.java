package com.quack.boardgameapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.util.Locale;


/**
 * Used to get the translation from the messages_lang.properties
 */
@Service
public class TranslationService {

    private MessageSource messageSource;
    @Autowired
    public TranslationService(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    /**
     * Takes the properties key and the locale to get the translation for said key
     * (uses resources/translations/messages_{Locale}.properties
     * @param key ex : Game.TicTacToe.name
     * @param locale ex : Locale.FRENCH
     * @return Translated message as a String
     */
    public String translate(String key, Locale locale){
        try {
            return messageSource.getMessage(key, null, locale);
        }
        catch (NoSuchMessageException noSuchMessageException){
            System.err.println(noSuchMessageException);
            return messageSource.getMessage(key, null, Locale.US);
        }
    }

}
