package com.spring.jwt.complet.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class AuthRequestResponse {
    private String username;
    private String message;
    private String token;
    private boolean status;
}
