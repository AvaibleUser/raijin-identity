package edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import edu.umd.cs.findbugs.annotations.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity(name = "permissions")
@Table(name = "permissions", schema = "public")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class PermissionsEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String key;

    private String description;

    @Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    private Instant deletedAt;

    @ManyToMany(fetch = LAZY, mappedBy = "permissions")
    private List<RolesEntity> roles;
}
