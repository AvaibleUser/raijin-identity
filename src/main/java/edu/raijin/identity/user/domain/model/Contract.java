package edu.raijin.identity.user.domain.model;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Data
@With
@Builder
@Setter(NONE)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Contract {

    private Long id;

    private UUID userId;

    private String roleName;

    private LocalDate startDate;

    private LocalDate endDate;

    private ContractStatus status;
}
