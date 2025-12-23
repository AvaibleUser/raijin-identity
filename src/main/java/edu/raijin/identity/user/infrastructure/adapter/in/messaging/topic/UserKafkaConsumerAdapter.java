package edu.raijin.identity.user.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.ContractEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.usecase.ChangeUserRoleUseCase;
import edu.raijin.identity.user.domain.usecase.UpdateUserUseCase;
import edu.raijin.identity.user.infrastructure.adapter.in.messaging.mapper.ContractEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserKafkaConsumerAdapter {

    private final ChangeUserRoleUseCase changeRole;
    private final UpdateUserUseCase update;
    private final ContractEventMapper mapper;

    private void consumeContractToUpdateUser(User user, String role) {
        update.update(user.getId(), user);
        changeRole.changeRole(user.getId(), role);
    }

    @KafkaListener(topics = "${kafka.topics.contract-commands.topic}", properties = "${kafka.topics.contract-commands.properties}", groupId = "identity-service")
    public void consumeContractEvent(@Payload ContractEvent event, @Header(RECEIVED_KEY) String key) {
        Boolean banned;
        String role;
        switch (key) {
            case "create", "update" -> {
                banned = switch (event.getStatus()) {
                    case SUSPENDED -> true;
                    default -> null;
                };
                role = switch (event.getStatus()) {
                    case TERMINATED -> "USER";
                    default -> event.getRole();
                };
            }
            default -> {
                banned = false;
                role = "USER";
            }
        }
        User user = mapper.toUser(event, banned);
        consumeContractToUpdateUser(user, role);
    }
}