package com.itcinfotech.EmployeeTimeSheetBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Autowired
  private SessionRegistry sessionRegistry;
  
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/").permitAll().and().authorizeRequests().antMatchers("/console/**")
        .permitAll().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().and()
        .authorizeRequests().antMatchers("/").permitAll()

        .antMatchers("/signup/*", "/signup", "login", "/registeruser/*", "/registeruser", "/createpassword",
            "/createpassword/*", "/resetpassword", "/resetpassword/*", "/login/*", "/login",
            "login?error")
        .permitAll().and().authorizeRequests().anyRequest().authenticated();

    http.sessionManagement().maximumSessions(1000).sessionRegistry(sessionRegistry()).and().sessionFixation().none();

   http.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID");
    
    http.csrf().disable();
    http.headers().frameOptions().disable();
    http.requestCache().requestCache(new NullRequestCache());
    http.httpBasic();
  }

  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

  @Bean
  public FilterRegistrationBean corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
  }
}
