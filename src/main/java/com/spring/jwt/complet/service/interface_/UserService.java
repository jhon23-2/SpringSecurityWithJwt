package com.spring.jwt.complet.service.interface_;

import com.spring.jwt.complet.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserEntity user);
    void SaveAll(List<UserEntity> userEntities);
    Optional<UserEntity> findUserEntityByUsername(String username);
}
