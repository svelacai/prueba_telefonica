package com.mitocode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mitocode.entity.dummyjson.AuthRequest;
import com.mitocode.entity.dummyjson.AuthResponse;
import com.mitocode.entity.dummyjson.UserResponse;
import com.mitocode.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
		try {
			AuthResponse response = authService.autenticarYRegistrar(authRequest.getUsername(),
					authRequest.getPassword());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Autorizacion fallida", e);
		}
	}

	@GetMapping("/me")
	public ResponseEntity<UserResponse> getMe(@RequestHeader("Authorization") String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String accessToken = authorizationHeader.substring(7);
			try {
				UserResponse userDetails = authService.usuarioAutenticado(accessToken);
				return ResponseEntity.ok(userDetails);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalido access token", e);
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autorizacion header.");
	}
}