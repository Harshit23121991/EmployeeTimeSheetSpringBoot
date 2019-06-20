package com.itcinfotech.EmployeeTimeSheetBackend.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.itcinfotech.EmployeeTimeSheetBackend.model.User;


public class CustomUserDetail implements UserDetails {

  private static final long serialVersionUID = 1L;

  private Long userId;
  private String email;
  private String password;
  private String role;
  private Collection<? extends GrantedAuthority> authorities;
  private User endUser;

  public CustomUserDetail(User authenticatedUser) {
    this.email = authenticatedUser.getEmail();
    this.password = authenticatedUser.getPassword();
    this.userId = authenticatedUser.getUserId();
    this.role = authenticatedUser.getRole().getRoleName().toUpperCase();
    this.endUser = authenticatedUser;

    List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
    auths.add(new SimpleGrantedAuthority(authenticatedUser.getRole().getRoleName().toUpperCase()));
    this.authorities = auths;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public User getEndUser() {
    return endUser;
  }

  public void setEndUser(User endUser) {
    this.endUser = endUser;
  }

}
