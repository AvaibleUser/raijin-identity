package edu.raijin.identity.role.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.time.Instant;
import java.util.List;

import edu.raijin.commons.util.exception.BadRequestException;
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
public class Role {

    private Long id;

    private String name;

    private String description;

    private String color;

    @Builder.Default
    private Boolean active = true;

    private List<Long> permissions;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonNull(name, () -> new BadRequestException("El nombre es requerido"));
    }

    public boolean checkActive() {
        if (!active) {
            throw new BadRequestException("El rol se encuentra inactivo");
        }
        return true;
    }

    public void update(Role update) {
        this.description = firstNonNull(description, update.description);
        this.permissions = firstNonNull(permissions, update.permissions);
        this.color = firstNonNull(color, update.color);
    }

    public void delete() {
        active = false;
        deletedAt = Instant.now();
    }
}
