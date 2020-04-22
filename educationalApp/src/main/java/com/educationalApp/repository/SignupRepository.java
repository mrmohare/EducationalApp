/**
 * 
 */
package com.educationalApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educationalApp.entity.LoginDetEntity;

/**
 * @author Megharaj
 *
 */
public interface SignupRepository extends JpaRepository<LoginDetEntity, Long>{

}
