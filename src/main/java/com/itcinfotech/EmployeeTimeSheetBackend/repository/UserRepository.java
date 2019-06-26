package com.itcinfotech.EmployeeTimeSheetBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itcinfotech.EmployeeTimeSheetBackend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByEmail(String email);

	User findByEmail(String email);
	
}
