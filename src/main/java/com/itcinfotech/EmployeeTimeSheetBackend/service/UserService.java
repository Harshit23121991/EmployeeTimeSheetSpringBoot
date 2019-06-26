package com.itcinfotech.EmployeeTimeSheetBackend.service;

import java.util.List;

import com.itcinfotech.EmployeeTimeSheetBackend.model.User;


public interface UserService {
	public List<User> findAllUser();

	public User findUserByUserId(Long userId);

	public User activateUser(Long userId);

	//public User saveUser(UserFormBean userFormBean);

	public User findUserByEmail(String email);

	//public User resetPassword(UserFormBean userFormBean);

}
