package com.mitocode.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mitocode.entity.dummyjson.AuthRequest;
import com.mitocode.entity.dummyjson.AuthResponse;
import com.mitocode.entity.dummyjson.UserResponse;

@FeignClient(name = "dummyJson", url = "https://dummyjson.com")
public interface DummyJsonFeignClient {

	@PostMapping("/auth/login")
	AuthResponse login(@RequestBody AuthRequest authRequest);

	@GetMapping("/auth/me")
	UserResponse getUsuarioAutenticado(@RequestHeader("Authorization") String authorizationHeader);


}