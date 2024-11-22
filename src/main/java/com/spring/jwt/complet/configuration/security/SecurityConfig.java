package com.spring.jwt.complet.configuration.security;

import com.spring.jwt.complet.configuration.filter.JwtAuthenticationFilter;
import com.spring.jwt.complet.configuration.filter.JwtAuthorizationFilter;
import com.spring.jwt.complet.configuration.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtUtils jwtUtils;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   AuthenticationManager authenticationManager,
                                                   JwtAuthorizationFilter authorizationFilter,
                                                   AuthenticationEntryPoint authenticationEntryPoint) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils,authenticationManager);

        return httpSecurity
                .csrf(c-> c.disable())
                .sessionManagement(session->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                }).authorizeHttpRequests(request->{
                    request.requestMatchers("/auth/**").permitAll();

                    request.requestMatchers(HttpMethod.GET,"/method/get").authenticated();
                    request.requestMatchers(HttpMethod.POST,"/method/post").hasAnyRole("ADMIN","USER");
                    request.requestMatchers(HttpMethod.DELETE,"/method/delete").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.PUT,"/method/put").hasRole("ADMIN");

                    request.anyRequest().denyAll();
                })
                .exceptionHandling(exception-> exception.authenticationEntryPoint(authenticationEntryPoint))
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {
        return httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    /*
    Second method for set the providers to the AuthenticationManager
    @Bean
    public AuthenticationManager authenticationProvider(AuthenticationProvider authenticationProvider) throws Exception {
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
