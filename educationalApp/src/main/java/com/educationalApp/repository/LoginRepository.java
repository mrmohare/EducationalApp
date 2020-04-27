package com.educationalApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educationalApp.entity.LoginDetEntity;

public interface LoginRepository extends JpaRepository<LoginDetEntity, Long> {

	public LoginDetEntity findByEmail(String username);
}
