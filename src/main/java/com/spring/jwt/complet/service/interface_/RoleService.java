package com.spring.jwt.complet.service.interface_;

import com.spring.jwt.complet.persistence.entity.RoleEntity;
import com.spring.jwt.complet.persistence.entity.RoleEnumeration;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void saveRole(RoleEntity role);
    Optional<RoleEntity> findRoleByNameRole(RoleEnumeration role);
    void saveAllRole(List<RoleEntity> roleEntities);
}
