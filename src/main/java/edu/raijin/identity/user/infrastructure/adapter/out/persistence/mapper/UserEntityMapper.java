package edu.raijin.identity.user.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.infrastructure.adapter.out.persistence.entity.UsersEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "roleColor", source = "role.color")
    User toDomain(UsersEntity entity);

    UsersEntity toEntity(User domain);
}
