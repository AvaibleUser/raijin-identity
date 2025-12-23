package edu.raijin.identity.user.infrastructure.adapter.in.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.ContractEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.user.domain.model.Contract;
import edu.raijin.identity.user.domain.model.User;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractEventMapper {

    @Mapping(source = "employeeId", target = "userId")
    @Mapping(source = "role", target = "roleName")
    Contract toDomain(ContractEvent event);

    @Mapping(source = "contract.employeeId", target = "id")
    @Mapping(source = "banned", target = "banned")
    User toUser(ContractEvent contract, Boolean banned);
}
