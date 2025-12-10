package edu.raijin.identity.infrastructure.adapter.out.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.raijin.commons.infrastructure.adapter.messaging.event.RegisteredUserEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.domain.model.User;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface UserEventMapper {

    @Mapping(target = "code", source = "code")
    RegisteredUserEvent toEvent(User user, String code);
}
