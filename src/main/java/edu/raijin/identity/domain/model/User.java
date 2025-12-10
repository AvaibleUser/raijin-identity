package edu.raijin.identity.domain.model;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.UUID;

import org.springframework.security.authentication.InsufficientAuthenticationException;

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

    private String email;

    private String token;

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

    public boolean checkAuthenticated() {
        if (!verified) {
            throw new InsufficientAuthenticationException("El usuario no ha sido verificado");
        }
        if (banned) {
            throw new InsufficientAuthenticationException("El usuario se encuentra baneado");
        }
        if (!active) {
            throw new InsufficientAuthenticationException("El usuario se encuentra inactivo o eliminado");
        }

        return true;
    }

    public void update(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void toggleVerified() {
        this.verified = !this.verified;
    }

    public void toggleBanned() {
        this.banned = !this.banned;
    }
}
