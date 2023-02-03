package com.quack.boardgameapi.factory;

import com.quack.boardgameapi.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserEntityFactory {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserEntityFactory.class);

    public static UserEntity from(UUID uuid) {
        //TODO: change this later
        //Using uuid as username for now because hgjfkdhjgklfjlkgfdjkl
        return new UserEntity().setUuid(uuid).setUsername(uuid.toString());
    }

    public static Collection<UserEntity> from(Collection<UUID> uuids) {
        List<UserEntity> users = new ArrayList<>();
        for (UUID uuid: uuids) {
            LOGGER.debug("Adding user :" + uuid);
            users.add(UserEntityFactory.from(uuid));
        }
        return users;
    }

}
