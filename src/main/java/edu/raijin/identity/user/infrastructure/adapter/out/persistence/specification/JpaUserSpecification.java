package edu.raijin.identity.user.infrastructure.adapter.out.persistence.specification;

import org.springframework.data.jpa.domain.Specification;

import edu.raijin.identity.user.infrastructure.adapter.out.persistence.entity.UsersEntity;

public interface JpaUserSpecification {

    static Specification<UsersEntity> byNameWith(String name) {
        if (name == null) {
            return Specification.unrestricted();
        }
        String nameLike = "%" + name.toLowerCase() + "%";
        return (root, query, builder) -> builder.or(
                builder.like(builder.lower(root.get("firstName")), nameLike),
                builder.like(builder.lower(root.get("lastName")), nameLike));
    }

    static Specification<UsersEntity> byActive() {
        return (root, query, builder) -> builder.equal(root.get("active"), true);
    }
}
