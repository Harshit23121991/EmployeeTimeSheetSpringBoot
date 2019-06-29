package com.itcinfotech.EmployeeTimeSheetBackend.service;

import java.util.List;

import com.itcinfotech.EmployeeTimeSheetBackend.formbeans.UserFormBean;
import com.itcinfotech.EmployeeTimeSheetBackend.model.User;


public interface UserService {
	List<User> findAllUser();

	User findUserByUserId(Long userId);

	User activateUser(Long userId);

	User saveUser(UserFormBean userFormBean);

	User findUserByEmail(String email);

	//public User resetPassword(UserFormBean userFormBean);

}
