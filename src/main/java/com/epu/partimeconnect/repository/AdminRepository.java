package com.epu.partimeconnect.repository;

import com.epu.partimeconnect.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsernameAndStatus(String username, String status);
}
