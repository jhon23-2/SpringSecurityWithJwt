package com.spring.jwt.complet.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = {"nameRole"}))
public class RoleEntity {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role" , unique = true)
    private RoleEnumeration nameRole;
}
