package com.mitocode.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.entity.LoginLog;
import com.mitocode.entity.dummyjson.AuthRequest;
import com.mitocode.entity.dummyjson.AuthResponse;
import com.mitocode.entity.dummyjson.UserResponse;
import com.mitocode.repository.LoginLogRepository;

@Service
public class AuthService {

    private final DummyJsonFeignClient dummyJsonFeignClient;
    private final LoginLogRepository loginLogRepository;

    @Autowired
    public AuthService(DummyJsonFeignClient dummyJsonFeignClient, LoginLogRepository loginLogRepository) {
        this.dummyJsonFeignClient = dummyJsonFeignClient;
        this.loginLogRepository = loginLogRepository;
    }

    public AuthResponse autenticarYRegistrar(String username, String password) {
        AuthRequest authRequest = new AuthRequest(username, password); 
        AuthResponse authResponse = dummyJsonFeignClient.login(authRequest);

        // Registrar el login exitoso 
        LoginLog loginLog = new LoginLog(
            authResponse.getUsername(),
            authResponse.getToken(),
            authResponse.getRefreshToken(),
            authResponse.getEmail()
        ); 
        loginLogRepository.save(loginLog); 

        return authResponse;
    }

    public UserResponse usuarioAutenticado(String accessToken) {
        return dummyJsonFeignClient.getUsuarioAutenticado("Bearer " + accessToken); 
    }
}