package com.itcinfotech.EmployeeTimeSheetBackend.formbeans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Pavan
 *
 */
public class UserFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String roleName;
	private String password;
	private String mailToken;
	private String userType;
	private Long isSignatory;
	private Long isMailTokenActive;
	private String location;
	private Long cityId;
	private Long countryId;
	private Long organizationId;
	private Long languageId;
	private Long loginFailureCount;
	private Long createdBy;
	private Date createdDate;
	private Long isDelete;
	private Long isActive;
	private Date tokenCreateTime;
	private String salutation;
	private String title;
	private String position;
	private String mobileNumber;
	private String personalWebsite;
	private String description;
	private Long isBlocked;
	private Long isTermsAndConditions;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailToken() {
		return mailToken;
	}

	public void setMailToken(String mailToken) {
		this.mailToken = mailToken;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getIsSignatory() {
		return isSignatory;
	}

	public void setIsSignatory(Long isSignatory) {
		this.isSignatory = isSignatory;
	}

	public Long getIsMailTokenActive() {
		return isMailTokenActive;
	}

	public void setIsMailTokenActive(Long isMailTokenActive) {
		this.isMailTokenActive = isMailTokenActive;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPersonalWebsite() {
		return personalWebsite;
	}

	public void setPersonalWebsite(String personalWebsite) {
		this.personalWebsite = personalWebsite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public Long getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Long loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public Date getTokenCreateTime() {
		return tokenCreateTime;
	}

	public void setTokenCreateTime(Date tokenCreateTime) {
		this.tokenCreateTime = tokenCreateTime;
	}

	public Long getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Long isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Long getIsTermsAndConditions() {
		return isTermsAndConditions;
	}

	public void setIsTermsAndConditions(Long isTermsAndConditions) {
		this.isTermsAndConditions = isTermsAndConditions;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
