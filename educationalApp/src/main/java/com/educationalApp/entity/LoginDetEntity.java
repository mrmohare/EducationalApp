package com.educationalApp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.UniqueElements;

@Entity // This tells Hibernate to make a table out of this class
public class LoginDetEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String fullName;

  @Column(name="email", unique=true)
  private String email;
  
  private String mobile;
  
  private String password;
  
  private String role;

  private Timestamp createdOn;
  
  private Timestamp updatedOn;
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return fullName;
  }

  public void setName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Timestamp getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(Timestamp createdOn) {
	this.createdOn = createdOn;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public Timestamp getUpdatedOn() {
	return updatedOn;
}

public void setUpdatedOn(Timestamp updatedOn) {
	this.updatedOn = updatedOn;
}
}