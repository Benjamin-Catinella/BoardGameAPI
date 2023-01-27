package com.quack.boardgameapi;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class TranslationService {

    private MessageSource messageSource;
    public TranslationService(MessageSource messageSource){
        this.messageSource = messageSource;
    }
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
