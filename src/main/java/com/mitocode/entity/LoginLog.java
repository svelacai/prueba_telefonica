package com.mitocode.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "login_log")
public class LoginLog {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String username;

	@Column(name = "login_time", nullable = false)
	private LocalDateTime loginTime;

	@Column(name = "access_token", columnDefinition = "TEXT")
	private String accessToken;

	@Column(name = "email")
	private String email;

	@Column(name = "refresh_token", columnDefinition = "TEXT")
	private String refreshToken;

	public LoginLog() {
	}

	public LoginLog(String username, String accessToken, String refreshToken, String email) {
		this.username = username;
		this.loginTime = LocalDateTime.now();
		this.accessToken = accessToken;
		this.email = email;
		this.refreshToken = refreshToken;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}