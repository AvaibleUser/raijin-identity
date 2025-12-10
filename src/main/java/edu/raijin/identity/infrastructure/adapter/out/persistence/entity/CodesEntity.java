package edu.raijin.identity.infrastructure.adapter.out.persistence.entity;

import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity(name = "user_codes")
@Table(name = "user_codes", schema = "public")
@DynamicInsert
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "code")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class CodesEntity {

    @Id
    @NonNull
    @Column(columnDefinition = "BPCHAR(6)", length = 6)
    private String code;

    @NonNull
    @Column(nullable = false)
    private String email;

    private Instant expiration;
}
