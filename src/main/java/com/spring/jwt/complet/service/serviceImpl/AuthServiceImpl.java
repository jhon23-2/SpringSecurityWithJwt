package com.spring.jwt.complet.service.serviceImpl;

import com.spring.jwt.complet.controller.dto.AuthRequestRegister;
import com.spring.jwt.complet.controller.dto.AuthRequestResponse;
import com.spring.jwt.complet.persistence.entity.RoleEntity;
import com.spring.jwt.complet.persistence.entity.RoleEnumeration;
import com.spring.jwt.complet.persistence.entity.UserEntity;
import com.spring.jwt.complet.service.interface_.AuthService;
import com.spring.jwt.complet.service.interface_.RoleService;
import com.spring.jwt.complet.service.interface_.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public AuthRequestResponse register(AuthRequestRegister authRequestRegister) {

        String roleRegister = authRequestRegister.getRole().toUpperCase();
        RoleEnumeration roleEnum = null;

        Optional<String> roleString = roleEnumerations.stream()
                .filter(role -> {
                    return role.equalsIgnoreCase(roleRegister);
                }).findAny();


        if(roleString.isPresent()){

            String roleStringGet = roleString.get();
            roleEnum = RoleEnumeration.valueOf(roleStringGet);

            RoleEntity roleEntity = roleService.findRoleByNameRole(roleEnum)
                    .orElseThrow(()->new RuntimeException("role no found"));

            Long idRole = roleEntity.getId();

            UserEntity userEntity = UserEntity.builder()
                    .name(authRequestRegister.getName())
                    .lastName(authRequestRegister.getSurname())
                    .username(authRequestRegister.getUsername())
                    .password(passwordEncoder.encode(authRequestRegister.getPassword()))
                    .roleEntities(Set.of(RoleEntity.builder()
                            .id(idRole)
                            .build()))
                    .build();

            userService.saveUser(userEntity);
            log.info("User register successfully", "user register true");


            return AuthRequestResponse.builder()
                    .username(userEntity.getUsername())
                    .message("User register successfully")
                    .status(true)
                    .token("")
                    .build();
        }

        return AuthRequestResponse.builder()
                .username(null)
                .message("User Not Register in database")
                .status(false)
                .token(null)
                .build();

    }


    private final Set<String> roleEnumerations = Set.of(
            "INVITE",
            "ADMIN",
            "USER"
    );
}
