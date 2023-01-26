package com.quack.boardgameapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersitenceEngines {
    /**
     * Gets corresponding PersistenceEngine according to the application.properties.persistence.mode property and the type of
     * the PersistenceEngine.
     */
    List<PersistenceEngine> engineList;
    @Value("${application.persistence.mode}")
    String mode;

}
