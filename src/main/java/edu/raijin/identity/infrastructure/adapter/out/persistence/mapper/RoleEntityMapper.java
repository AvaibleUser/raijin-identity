package edu.raijin.identity.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.domain.model.Role;
import edu.raijin.identity.infrastructure.adapter.out.persistence.entity.RolesEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface RoleEntityMapper {

    Role toDomain(RolesEntity entity);

    RolesEntity toEntity(Role domain);
}
