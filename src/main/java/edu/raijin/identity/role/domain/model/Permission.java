package edu.raijin.identity.role.domain.model;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;

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
public class Permission {

    private Long id;

    private String key;

    private String description;

    private Boolean active;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;
}
