package edu.raijin.identity.user.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user.AddUserDto;
import edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user.UpdateUserDto;
import edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user.UserWithRoleDto;
import edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user.UserWithTokenDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {

    UserWithTokenDto toDto(User user, String token, String role, String color, List<String> permissions);

    UserWithRoleDto toDto(User user);

    User toDomain(AddUserDto user);

    User toDomain(UpdateUserDto user);
}
