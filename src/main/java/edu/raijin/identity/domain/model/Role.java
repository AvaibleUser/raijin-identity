package edu.raijin.identity.domain.model;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;

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

    private String color;

    private String description;

    private Boolean active;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public boolean checkActive() {
        if (!active) {
            throw new BadRequestException("El rol se encuentra inactivo");
        }

        return true;
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void delete() {
        active = false;
        deletedAt = Instant.now();
    }
}
