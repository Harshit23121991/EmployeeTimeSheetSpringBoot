package com.itcinfotech.EmployeeTimeSheetBackend.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
/*import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.EmployeeTimeSheetBackend.exceptions.UserAlreadyExistException;
import com.itcinfotech.EmployeeTimeSheetBackend.exceptions.UserNotFoundException;
import com.itcinfotech.EmployeeTimeSheetBackend.formbeans.UserFormBean;
import com.itcinfotech.EmployeeTimeSheetBackend.model.User;
import com.itcinfotech.EmployeeTimeSheetBackend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  //@Autowired
 // private BCryptPasswordEncoder bCryptPasswordEncoder;

 // static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());

  @Override
  public List<User> findAllUser() {
    return userRepository.findAll();
  }

  @Override
  public User findUserByUserId(Long userid) {
    Optional<User> existedUser = userRepository.findById(userid);
    if (existedUser == null) {
      throw new UserNotFoundException();
    }
    return existedUser.get();
  }

  @Override
  public User activateUser(Long userId) {
    Optional<User> existedUser = userRepository.findById(userId);
    User User =  existedUser.get();
    if (User == null) {
      throw new UserNotFoundException();
    }
    userRepository.save(User);
    return User;
  }

  @Transactional
  @Override
  public User saveUser(UserFormBean userFormBean) {
    User isExist = userRepository.findUserByEmail(userFormBean.getEmail());
    if (isExist != null) {
      throw new UserAlreadyExistException();
    }
    return userRepository.save(prepareUser(userFormBean));
  }

 /*  @Transactional(
      rollbackOn = Exception.class)
  @Override
  public User updateUser(User user, MultipartFile[] files, MultipartFile profilePic, String removeProfilePic) {
    User existedUser = userRepository.findOne(user.getUserId());
    if (existedUser == null) {
      throw new UserNotFoundException();
    }
    if (user.getLanguageId() != null) {
      existedUser.setLanguageId(user.getLanguageId());
    }
    if (user.getOrganization() != null && user.getOrganization().getOrganizationId() != null) {
      existedUser.setOrganization(user.getOrganization());
    }
    if (user.getUserType() != null) {
      existedUser.setUserType(user.getUserType());
    }
    existedUser.setIsActive(user.getIsActive());
    existedUser.setPhone(user.getPhone());
    existedUser.setLocation(user.getLocation());
    existedUser.setIsSignatory(user.getIsSignatory());
    existedUser.setEmail(user.getEmail());
    existedUser.setLastName(user.getLastName());
    existedUser.setFirstName(user.getFirstName());
    existedUser.setCountryId(user.getCountryId());
    existedUser.setCityId(user.getCityId());
    existedUser.setSignatoryLimit(user.getSignatoryLimit());
    existedUser.setFormatLanguageId(user.getFormatLanguageId());
    existedUser.setSalutation(user.getSalutation());
    existedUser.setTitle(user.getTitle());
    existedUser.setPosition(user.getPosition());
    existedUser.setMobileNumber(user.getMobileNumber());
    existedUser.setPersonalWebsite(user.getPersonalWebsite());
    existedUser.setDescription(user.getDescription());
    unLockUser(user, existedUser);
    existedUser.setIsBlocked(user.getIsBlocked());
    existedUser.setIsDelete(user.getIsDelete() == null ? existedUser.getIsDelete() : user.getIsDelete());
    existedUser.setIsTermsAndConditionsAccepted(user.getIsTermsAndConditionsAccepted());
    // User userDb = userRepository.save(existedUser);
    String profilePicName = null;
    if (existedUser.getUserId() != null) {
      // uploading profile pic
      if (profilePic != null) {
        profilePicName = fileUploadUtility.uploadProfilePic(profilePic, existedUser.getUserId(), profilePicFolder);
        if (profilePicName != null && !"".equals(profilePicName)) {
          existedUser.setUserProfileImageName(profilePicName);
        }
      } else {
        if (removeProfilePic != null && removeProfilePic.equals("noimage")) {
          existedUser.setUserProfileImageName(null);
        }
      }
      // uploadProfilePic(profilepic, userDb.getUserId());
      Map<String, List<String>> filePathToFileNameAndSizeListMap = uploadFile(files, existedUser.getUserId());
      if (filePathToFileNameAndSizeListMap != null) {
        for (String filePath : filePathToFileNameAndSizeListMap.keySet()) {
          try {
            UserDocument userDoc = new UserDocument();
            userDoc.setUserId(existedUser.getUserId());
            userDoc.setFileName(filePathToFileNameAndSizeListMap.get(filePath).get(0));
            try {
              userDoc.setFileSize(Long.parseLong(filePathToFileNameAndSizeListMap.get(filePath).get(1)));
            } catch (Exception e) {
              logger.error("Could not convert size string to long", e);
            }
            userDoc.setFilePath(filePath);
            userDocumentService.saveUserDocument(userDoc);
            logger.info("userDocument Saved Succesfully...");
          } catch (Exception e) {
            File deleteFile = new File(filePath);
            if (deleteFile != null) {
              if (deleteFile.delete()) {
                logger.info("File Deleted Successfully");
              }
            }
          }
        }
      } else {
        logger.info("no file to be uploaded");
      }
    } else {
      logger.info("Project Save Failed...");
    }
    return userRepository.save(existedUser);
  }*/

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

 /* @Transactional
  @Override
  public User resetPassword(UserFormBean userFormBean) {
    User user;
    User existedUser = userRepository.findUserByEmail(userFormBean.getEmail());
    if (existedUser == null) {
      throw new UserNotFoundException();
    } else {
      Date date = new Date();
      existedUser.setIsMailTokenActive(1L);
      existedUser.setMailToken(UUID.randomUUID().toString());
      existedUser.setTokenCreateTime(date);
      existedUser.setLoginFailureCount(0L);
      existedUser.setIsBlocked(0L);
      user = userRepository.save(existedUser);
      // Long moduleId = 2L;
      String moduleName = MailModuleEnum.CREATE_FORGET_MODULE.name();
      mailUtility.sendMailToUser(existedUser.getEmail(),
          PBB_BASE_URL + "/#/reset" + "?token=" + existedUser.getMailToken() + "&email=" + existedUser.getEmail(),
          moduleName, null, null, null);
    }
    return user;
  }*/

  private User prepareUser(UserFormBean userFormBean) {
    Date date = new Date();
    User user = new User();
    if (userFormBean.getUserId() != null) {
      user.setUserId(userFormBean.getUserId());
    }
    if (userFormBean.getEmail() != null && !("".equals(userFormBean.getEmail()))) {
      user.setEmail(userFormBean.getEmail().toLowerCase());
    }
    user.setPassword(userFormBean.getPassword());
    user.setFirstName(userFormBean.getFirstName());
    user.setLastName(userFormBean.getLastName());
    user.setCreatedDate(date);
    user.setIsActive(0L);
    user.setTokenCreateTime(date);
    user.setMobileNumber(userFormBean.getMobileNumber());
    return user;
  }

}
