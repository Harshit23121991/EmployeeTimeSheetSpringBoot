package com.itcinfotech.EmployeeTimeSheetBackend.security;

import static java.lang.String.format;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itcinfotech.EmployeeTimeSheetBackend.model.User;
import com.itcinfotech.EmployeeTimeSheetBackend.repository.UserRepository;

@Service("userDetailsService")
public class AuthenticateUser implements UserDetailsService {

	//static final Logger logger = LogManager.getLogger(AuthenticateUser.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(username);
		if (user != null) {
			//logger.info("User email:" + user.getEmail());
			return new CustomUserDetail(user);
		} else {
			throw new UsernameNotFoundException(format("User %s not found", username));
		}
	}
}
