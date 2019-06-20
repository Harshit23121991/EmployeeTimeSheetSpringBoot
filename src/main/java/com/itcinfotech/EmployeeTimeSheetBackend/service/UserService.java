package com.itcinfotech.EmployeeTimeSheetBackend.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.itcinfotech.EmployeeTimeSheetBackend.model.User;


public interface UserService {
	public List<User> findAllUser();

	public User findUserByUserId(Long userId);

	public User activateUser(Long userId);

	//public User saveUser(UserFormBean userFormBean);

	public User updateUser(User user, MultipartFile[] files, MultipartFile profilePic, String removeProfilePic);

	public User findUserByEmail(String email);

	//public User resetPassword(UserFormBean userFormBean);

	public boolean validateEmailToken(String mailToken, String email);

	public List<User> findOrgByEmail(Long orgId);

	public List<User> findOrgByIsSignatory(Long orgId);

	public User findLrgNameAndAddress(String email);

	public String inviteUser(String email);

	public String zipFiles(MultipartFile[] files, Long projectId);

	
	public void isUserActive(Long userId);
}
