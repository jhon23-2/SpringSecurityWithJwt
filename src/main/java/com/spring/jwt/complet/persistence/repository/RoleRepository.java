package com.spring.jwt.complet.persistence.repository;

import com.spring.jwt.complet.persistence.entity.RoleEntity;
import com.spring.jwt.complet.persistence.entity.RoleEnumeration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findRoleEntityByNameRole(RoleEnumeration nameRole);
}
