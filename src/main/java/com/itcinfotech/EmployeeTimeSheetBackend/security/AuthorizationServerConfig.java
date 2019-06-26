package com.itcinfotech.EmployeeTimeSheetBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  AuthenticationManager authenticationManager;

  public AuthorizationServerConfig(AuthenticationConfiguration authenticationConfiguration) throws Exception {
      this.authenticationManager =
          authenticationConfiguration.getAuthenticationManager();
  }

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;
  
  @Bean
  public TokenStore tokenStore () {
      return new InMemoryTokenStore ();
  }

  @Qualifier("userDetailsService")
  UserDetailsService userDetailsService;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.passwordEncoder(bCryptPasswordEncoder).checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory().withClient("pbb-client").autoApprove(true).authorizedGrantTypes("password")
        .scopes("read", "write").accessTokenValiditySeconds(3600);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
  }
  
  
}
