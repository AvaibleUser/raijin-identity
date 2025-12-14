package edu.raijin.identity.role.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.domain.model.Permission;
import edu.raijin.identity.role.domain.model.RolePermission;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.permission.AddPermissionDto;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.permission.PermissionDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionDtoMapper {

    RolePermission toDomain(AddPermissionDto permissionDto, Long roleId);

    RolePermission toDomain(Long permissionId, Long roleId);

    List<PermissionDto> toDto(List<Permission> permission);
}
