package com.spring.jwt.complet.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"username","password"}))
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String password;

    @ManyToMany(targetEntity = RoleEntity.class , fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user") ,
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<RoleEntity> roleEntities = new HashSet<>();
}
