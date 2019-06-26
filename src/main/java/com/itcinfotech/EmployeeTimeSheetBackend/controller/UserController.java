package com.itcinfotech.EmployeeTimeSheetBackend.controller;

import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.EmployeeTimeSheetBackend.exceptions.UserAlreadyExistException;
import com.itcinfotech.EmployeeTimeSheetBackend.exceptions.UserNotFoundException;
import com.itcinfotech.EmployeeTimeSheetBackend.formbeans.PasswordFormBean;
import com.itcinfotech.EmployeeTimeSheetBackend.formbeans.UserCustomResponse;
import com.itcinfotech.EmployeeTimeSheetBackend.formbeans.UserFormBean;
import com.itcinfotech.EmployeeTimeSheetBackend.model.User;
import com.itcinfotech.EmployeeTimeSheetBackend.repository.UserRepository;
import com.itcinfotech.EmployeeTimeSheetBackend.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
  
  @Autowired
  UserService userService;
  
  @Autowired
  UserRepository userRepository;
  
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  static final Logger logger = LogManager.getLogger(UserController.class.getName());

  /**
   * @return
   */
  @RequestMapping(
      value = "/getalluser",
      method = RequestMethod.GET)
  public List<User> findAllUser() {
    return userService.findAllUser();
  }

  @RequestMapping(
      value = "/getuserbyid",
      params = { "userid" },
      method = RequestMethod.GET)
  public User findUserByUserId(@RequestParam("userid") Long userid) {
    return userService.findUserByUserId(userid);
  }

  @RequestMapping(
      value = "/activateuser",
      params = { "userid" },
      method = RequestMethod.POST)
  public ResponseEntity<UserCustomResponse> activateUser(@RequestParam("userid") Long userId) {
    UserCustomResponse userCustomResponse = null;
    try {
      userService.activateUser(userId);
      userCustomResponse = new UserCustomResponse();
      userCustomResponse.setMessage("");
      userCustomResponse.setUserId(userId);
      userCustomResponse.setIsSuccess(true);
    } catch (UserNotFoundException e) {
      userCustomResponse = new UserCustomResponse();
      userCustomResponse.setMessage("");
      userCustomResponse.setUserId(userId);
      userCustomResponse.setIsSuccess(false);
    }
    return new ResponseEntity<>(userCustomResponse, HttpStatus.OK);
  }


  /**
   * @param userFormBean
   * @return
   * @throws UserAlreadyExistException1
   * @throws PbbMessageException
   */
  @RequestMapping(
      value = "/registeruser",
      method = RequestMethod.POST)
  public ResponseEntity<UserCustomResponse> saveUser(@RequestBody UserFormBean userFormBean) {
    UserCustomResponse regResponse = new UserCustomResponse();
    User userDomain;
    try {
      userDomain = null;// userService.saveUser(userFormBean);
      logger.info("User Created Succesfully...");
      regResponse.setIsSuccess(true);
      regResponse.setUserId(userDomain.getUserId());
      regResponse.setUserType(userDomain.getUserType());
     // regResponse.setMessage(UserEnum.USER_REG.getValue() + OtherEnum.SUCCESS_MSG.getValue());
    } catch (UserAlreadyExistException e) {
      regResponse.setIsSuccess(false);
     // regResponse.setMessage(UserEnum.USER_REG.getValue() + OtherEnum.USER_ALREADY_EXIST.getValue());
      logger.error("User Save Failed..." + e.getMessage());
    } catch (Exception e) {
      regResponse.setIsSuccess(false);
      //regResponse.setMessage(UserEnum.USER_REG.getValue() + OtherEnum.FAILURE_MSG.getValue());
      logger.error("User Save Failed..." + e.getMessage());
    }
    return new ResponseEntity<>(regResponse, HttpStatus.OK);
  }

  
  @RequestMapping(
      value = "/createpassword",
      method = RequestMethod.POST)
  public @ResponseBody UserCustomResponse createPassword(@RequestBody PasswordFormBean passwordFormBean) {
    UserCustomResponse createPswrdResponse = new UserCustomResponse();
    User user = userService.findUserByEmail(passwordFormBean.getEmail());
    Date currentDate = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(user.getTokenCreateTime());
    //cal.add(Calendar.MINUTE, mailTokenTimeInterval);
    Date tokenExpireTime = cal.getTime();
    logger.info("tokenCreateTime : " + user.getTokenCreateTime());
    logger.info("tokenExpireTime : " + tokenExpireTime);
    logger.info("currentDate : " + currentDate);
    /*if (user.getMailToken().equals(passwordFormBean.getMailToken()) && user.getIsMailTokenActive() == 1
        && currentDate.compareTo(tokenExpireTime) < 0) {

      user.setPassword(bCryptPasswordEncoder.encode(passwordFormBean.getPassword()));
      user.setIsMailTokenActive(0L);
      userService.updateUser(user, null, null, null);
      createPswrdResponse.setUserId(user.getUserId());
      createPswrdResponse.setIsSuccess(true);
      createPswrdResponse.setMessage(UserEnum.USER_PWD.getValue() + OtherEnum.SUCCESS_MSG.getValue());
    } else {
      createPswrdResponse.setIsSuccess(false);
      createPswrdResponse.setMessage(OtherEnum.TOKEN_INVALID.getValue());
    }*/
    return createPswrdResponse;
  }

  /**
   * @param userFormBean
   * @return
   * @throws UserNotFoundException1
   * @throws MessagingException
   * @throws MalformedURLException
   * @throws PbbMessageException
   */
  @RequestMapping(
      value = "/resetpassword",
      method = RequestMethod.POST)
  public ResponseEntity<UserCustomResponse> restPassword(@RequestBody UserFormBean userFormBean)
      throws UserNotFoundException {
    UserCustomResponse resetPwdResponse = new UserCustomResponse();
    User userDomain = null;//userService.resetPassword(userFormBean);
    if (userDomain.getUserId() != null) {
      logger.info("User password reset Link Send Succesfully...");
      resetPwdResponse.setUserId(userDomain.getUserId());
      resetPwdResponse.setIsSuccess(true);
      //resetPwdResponse.setMessage(UserEnum.USER_FORGOTPASS.getValue() + OtherEnum.SUCCESS_MSG.getValue());
    } else {
      logger.info("User password Reset Failed...");
      resetPwdResponse.setIsSuccess(false);
     // resetPwdResponse.setMessage(UserEnum.USER_FORGOTPASS.getValue() + OtherEnum.FAILURE_MSG.getValue());
    }
    return new ResponseEntity<>(resetPwdResponse, HttpStatus.OK);
  }

  @RequestMapping(
      value = "/getvalidatemailtoken",
      params = { "emailtoken", "email" },
      method = RequestMethod.GET)
  public ResponseEntity<UserCustomResponse> validateMailToken(@RequestParam("emailtoken") String emailtoken,
      @RequestParam("email") String email) {
    UserCustomResponse mailTokenResponse = new UserCustomResponse();
    boolean validateToken = userService.validateEmailToken(emailtoken, email);
    if (validateToken) {
      mailTokenResponse.setIsSuccess(true);
      //mailTokenResponse.setMessage(OtherEnum.TOKEN_VALID.getValue());
      return new ResponseEntity<>(mailTokenResponse, HttpStatus.OK);
    } else {
      mailTokenResponse.setIsSuccess(false);
      //mailTokenResponse.setMessage(OtherEnum.TOKEN_INVALID.getValue());
      return new ResponseEntity<>(mailTokenResponse, HttpStatus.OK);
    }
  }
}
