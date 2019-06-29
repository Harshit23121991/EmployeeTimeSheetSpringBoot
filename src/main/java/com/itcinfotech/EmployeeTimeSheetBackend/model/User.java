package com.itcinfotech.EmployeeTimeSheetBackend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itc_user")
public class User implements Serializable {
	private static final long serialVersionUID = 115782561L;

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date tokenCreateTime;
	private Date createdDate;
	private Long isActive;
	private String mobileNumber;

	public User() {
		// this is default User Constructor
	}

	public User(User user) {
		this.userId = user.userId;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.password = user.password;
		this.tokenCreateTime = user.tokenCreateTime;
		this.createdDate = user.createdDate;
		this.isActive = user.isActive;
		this.mobileNumber = user.mobileNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "is_active")
	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	@Column(name = "token_create_time")
	public Date getTokenCreateTime() {
		return tokenCreateTime;
	}

	public void setTokenCreateTime(Date tokenCreateTime) {
		this.tokenCreateTime = tokenCreateTime;
	}

	@Column(name = "mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
