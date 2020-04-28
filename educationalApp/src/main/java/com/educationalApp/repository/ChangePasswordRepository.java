package com.educationalApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.educationalApp.entity.LoginDetEntity;

public interface ChangePasswordRepository extends JpaRepository<LoginDetEntity, Long> {

	LoginDetEntity findByEmailAndPassword(String username,String password);

	
	@Modifying
	@Transactional
	@Query("update LoginDetEntity LDE set LDE.password = ?2 where LDE.email= ?1")
	int updatePassword( String username, String newPassword);
}
