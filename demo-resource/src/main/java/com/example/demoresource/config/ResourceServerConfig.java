package com.example.demoresource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by ouyanggang on 2018/11/15.
 */
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    super.configure(http);
    http
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/res","/res2/res");
//            .access("#oauth2.hasScope('read') and hasRole('USER')");
  }

}
