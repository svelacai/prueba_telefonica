package com.mitocode.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mitocode.entity.LoginLog;
import com.mitocode.entity.dummyjson.AuthRequest;
import com.mitocode.entity.dummyjson.AuthResponse;
import com.mitocode.repository.LoginLogRepository;

public class AuthServiceTest {

    @Mock
    private DummyJsonFeignClient dummyJsonFeignClient;

    @Mock
    private LoginLogRepository loginLogRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateAndLogSuccess() {
        String username = "emilys"; 
        String password = "emilyspass"; 
        AuthResponse mockAuthResponse = new AuthResponse();
        mockAuthResponse.setUsername(username); 
        mockAuthResponse.setToken("mockAccessToken"); 
        mockAuthResponse.setRefreshToken("mockRefreshToken"); 
        mockAuthResponse.setEmail("mockEmail@gmail.com"); 


        when(dummyJsonFeignClient.login(any(AuthRequest.class))).thenReturn(mockAuthResponse); 
        when(loginLogRepository.save(any(LoginLog.class))).thenReturn(new LoginLog());  

        AuthResponse result = authService.autenticarYRegistrar(username, password);

        assertNotNull(result);
        verify(dummyJsonFeignClient, times(1)).login(any(AuthRequest.class)); 
        verify(loginLogRepository, times(1)).save(any(LoginLog.class)); 
    }

}