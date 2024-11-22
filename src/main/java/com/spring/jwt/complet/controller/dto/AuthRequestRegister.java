package com.spring.jwt.complet.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthRequestRegister {

    private String name;
    private String surname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    @Size(min = 4 , max = 10)
    private String role;

}
