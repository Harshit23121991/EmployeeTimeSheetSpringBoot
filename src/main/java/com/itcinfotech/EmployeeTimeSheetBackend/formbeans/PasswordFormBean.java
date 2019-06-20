package com.itcinfotech.EmployeeTimeSheetBackend.formbeans;

import java.io.Serializable;

/**
 * @author Pavan
 *
 */
public class PasswordFormBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String mailToken;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMailToken() {
		return mailToken;
	}
	public void setMailToken(String mailToken) {
		this.mailToken = mailToken;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
