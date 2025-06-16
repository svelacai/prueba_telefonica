package com.mitocode.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.entity.LoginLog;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, UUID> {
}