package edu.raijin.identity.user.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.UUID;

import edu.raijin.commons.util.exception.BadRequestException;
import edu.raijin.commons.util.exception.FailedAuthenticateException;
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
public class User {

    private UUID id;

    private String firstName;

    private String lastName;

    private String dpi;

    private String email;

    private String password;

    @Builder.Default
    private Boolean verified = false;

    @Builder.Default
    private Boolean banned = false;

    @Builder.Default
    private Boolean active = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonNull(firstName, () -> new BadRequestException("El nombre es requerido"));
        requireNonNull(lastName, () -> new BadRequestException("El apellido es requerido"));
        requireNonNull(dpi, () -> new BadRequestException("El DPI es requerido"));
        requireNonNull(email, () -> new BadRequestException("El email es requerido"));
        requireNonNull(password, () -> new BadRequestException("La contraseña es requerida"));
    }

    public boolean checkAuthenticated() {
        if (!verified) {
            throw new FailedAuthenticateException("El usuario no ha sido verificado");
        }
        if (banned) {
            throw new FailedAuthenticateException("El usuario se encuentra baneado");
        }
        if (!active) {
            throw new FailedAuthenticateException("El usuario se encuentra inactivo o eliminado");
        }

        return true;
    }

    public void update(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void updateId(UUID id) {
        if (isNull(this.id)) {
            this.id = id;
        }
    }

    public void updatePassword(String password) {
        if (!this.password.equals(password)) {
            this.password = password;
        }
    }

    public void toggleVerified() {
        this.verified = !this.verified;
    }

    public void toggleBanned() {
        this.banned = !this.banned;
    }
}
