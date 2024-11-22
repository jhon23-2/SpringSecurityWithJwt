package com.spring.jwt.complet.service.serviceImpl;

import com.spring.jwt.complet.persistence.entity.RoleEntity;
import com.spring.jwt.complet.persistence.entity.RoleEnumeration;
import com.spring.jwt.complet.persistence.repository.RoleRepository;
import com.spring.jwt.complet.service.interface_.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void saveRole(RoleEntity role) {
        roleRepository.save(role);
    }

    @Override
    public Optional<RoleEntity> findRoleByNameRole(RoleEnumeration role) {
        return roleRepository.findRoleEntityByNameRole(role);
    }

    @Override
    public void saveAllRole(List<RoleEntity> roleEntities) {
        roleRepository.saveAll(roleEntities);
    }
}
