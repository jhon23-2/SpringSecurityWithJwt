package com.spring.jwt.complet.service.interface_;

import com.spring.jwt.complet.controller.dto.AuthRequestRegister;
import com.spring.jwt.complet.controller.dto.AuthRequestResponse;

public interface AuthService {
    AuthRequestResponse register(AuthRequestRegister authRequestRegister);
}
