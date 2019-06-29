package com.itcinfotech.EmployeeTimeSheetBackend.formbeans;

import java.io.Serializable;

public class UserCustomResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private Long userId;
	private boolean isSuccess;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
}
