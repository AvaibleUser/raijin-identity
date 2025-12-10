package edu.raijin.identity.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.domain.model.User;
import edu.raijin.identity.infrastructure.adapter.out.persistence.entity.UsersEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface UserEntityMapper {

    User toDomain(UsersEntity entity);

    UsersEntity toEntity(User domain);
}
