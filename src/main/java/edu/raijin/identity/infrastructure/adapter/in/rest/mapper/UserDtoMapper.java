package edu.raijin.identity.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.domain.model.User;
import edu.raijin.identity.infrastructure.adapter.in.rest.dto.user.AddUserDto;
import edu.raijin.identity.infrastructure.adapter.in.rest.dto.user.UserWithTokenDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface UserDtoMapper {

    UserWithTokenDto toDto(User user);

    User toDomain(AddUserDto user);
}
