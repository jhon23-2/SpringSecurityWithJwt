package com.spring.jwt.complet;

import com.spring.jwt.complet.persistence.entity.RoleEntity;
import com.spring.jwt.complet.persistence.entity.RoleEnumeration;
import com.spring.jwt.complet.persistence.entity.UserEntity;
import com.spring.jwt.complet.service.interface_.RoleService;
import com.spring.jwt.complet.service.interface_.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static java.util.Set.of;

@Slf4j
@SpringBootApplication
public class SpringSecurityJwtCompletApplication {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtCompletApplication.class, args);
	}

	@Bean
	CommandLineRunner innit(PasswordEncoder passwordEncoder){
		return args -> {

			//WE CREATE ROLES

			RoleEntity roleAdmin = RoleEntity.builder()
					.id(1L)
					.nameRole(RoleEnumeration.ADMIN)
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.id(2L)
					.nameRole(RoleEnumeration.USER)
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.id(3L)
					.nameRole(RoleEnumeration.INVITE)
					.build();

			roleService.saveAllRole(List.of(roleInvited,roleAdmin,roleUser));
			log.info("RoleEntities saved successfully");

			//WE CREATE USERS

			UserEntity jhonatan  = UserEntity.builder()
					.name("Jhonatan")
					.lastName("Almanza")
					.username("jhonatan")
					.password(passwordEncoder.encode("12345"))
					.roleEntities(of(RoleEntity.builder().id(1L).build()))
					.build();

			UserEntity leiner = UserEntity.builder()
					.name("Leiner")
					.lastName("Almanza")
					.username("leiner")
					.password(passwordEncoder.encode("1234"))
					.roleEntities(of(RoleEntity.builder().id(2L).build()))
					.build();

			UserEntity sultan = UserEntity.builder()
					.name("Sultan")
					.lastName("Almanza")
					.username("sultan")
					.password(passwordEncoder.encode("123"))
					.roleEntities(of(RoleEntity.builder().id(3L).build()))
					.build();

			userService.SaveAll(List.of(jhonatan,leiner,sultan));
			log.info("All users have saved successfully");

		};
	}

}
