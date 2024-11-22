package com.spring.jwt.complet.service.serviceImpl;

import com.spring.jwt.complet.persistence.entity.UserEntity;
import com.spring.jwt.complet.persistence.repository.UserRepository;
import com.spring.jwt.complet.service.interface_.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public void saveUser(UserEntity user) {
        repository.save(user);
    }

    @Override
    public void SaveAll(List<UserEntity> userEntities) {
        repository.saveAll(userEntities);
    }

    @Override
    public Optional<UserEntity> findUserEntityByUsername(String username) {
        return repository.findUserEntityByUsername(username);
    }
}
