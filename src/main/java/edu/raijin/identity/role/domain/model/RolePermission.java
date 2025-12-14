package edu.raijin.identity.role.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

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
public class RolePermission {

    private Long roleId;

    private Long permissionId;

    public void checkValidRegistration() {
        requireNonNull(roleId, () -> new BadRequestException("El rol es requerido"));
        requireNonNull(permissionId, () -> new BadRequestException("El permiso es requerido"));
    }
}
