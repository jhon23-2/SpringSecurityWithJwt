package com.spring.jwt.complet.configuration.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jwt.complet.configuration.security.jwt.JwtUtils;
import com.spring.jwt.complet.controller.dto.AuthLogin;
import com.spring.jwt.complet.persistence.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, AuthenticationManager authenticationManager){
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        AuthLogin authLogin = null;
        String username = "";
        String password = "";

        try {

            authLogin = new ObjectMapper().readValue(request.getInputStream() , AuthLogin.class);
            username = authLogin.getUsername();
            password = authLogin.getPassword();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);

        return this.authenticationManager.authenticate(authenticationToken); // -> delegates authentication to the authentication manager
        //return getAuthenticationManager().authenticate(authenticationToken); -> do same that last Method

    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {


        String token = jwtUtils.createToken(authResult.getName());

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token",token);
        httpResponse.put("message","User Authenticated Successfully");
        httpResponse.put("user",authResult.getName());
        httpResponse.put("status",true);

        response.addHeader("Authorization", "Bearer "+token);
        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
