package com.spring.jwt.complet.service;

import com.spring.jwt.complet.persistence.entity.RoleEntity;
import com.spring.jwt.complet.persistence.entity.UserEntity;
import com.spring.jwt.complet.service.interface_.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity  = this.userService.findUserEntityByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("The User " + username + "Not Found"));

        String user_name = userEntity.getUsername();
        String password  = userEntity.getPassword();

        Collection<? extends GrantedAuthority> grantedAuthorities = userEntity.getRoleEntities().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getNameRole().name())))
                .collect(Collectors.toList());


        return new User(
                user_name,
                password,
                true,
                true,
                true,
                true,
                grantedAuthorities
        );
    }

}
