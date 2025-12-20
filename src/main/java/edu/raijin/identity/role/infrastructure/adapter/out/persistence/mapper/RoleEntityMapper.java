package edu.raijin.identity.role.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.PermissionsEntity;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.RolesEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {

    Role toDomain(RolesEntity entity);

    @Mapping(target = "permissions", ignore = true)
    RolesEntity toEntity(Role domain);

    default Long getId(PermissionsEntity entity) {
        return entity.getId();
    }
}
