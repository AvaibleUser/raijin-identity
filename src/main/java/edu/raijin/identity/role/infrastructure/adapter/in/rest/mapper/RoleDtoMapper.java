package edu.raijin.identity.role.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.role.AddRoleDto;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.role.RoleDto;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.role.UpdateRoleDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleDtoMapper {

    Role toDomain(AddRoleDto role);

    Role toDomain(UpdateRoleDto role);

    RoleDto toDto(Role role);
}
