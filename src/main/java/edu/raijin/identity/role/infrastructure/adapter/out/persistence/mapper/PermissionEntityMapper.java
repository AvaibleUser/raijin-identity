package edu.raijin.identity.role.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.List;

import org.mapstruct.Mapper;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.domain.model.Permission;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.PermissionsEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface PermissionEntityMapper {

    List<Permission> toDomain(List<PermissionsEntity> entity);
}
