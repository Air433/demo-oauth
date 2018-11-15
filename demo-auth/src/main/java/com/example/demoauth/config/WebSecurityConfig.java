package com.example.demoauth.config;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author: 林塬
 * @date: 2018/1/19
 * @description: 权限配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationManagerBuilder authenticationManagerBuilder;
  /**
   * 1\这里记得设置requestMatchers,不拦截需要token验证的url
   * 不然会优先被这个filter拦截,走用户端的认证而不是token认证
   * 2\这里记得对oauth的url进行保护,正常是需要登录态才可以
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http
        .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
        .and()
        .authorizeRequests()
        .antMatchers("/oauth/**").authenticated()
        .and()
        .formLogin().permitAll();
  }


  @Bean
  @Override
  protected UserDetailsService userDetailsService(){
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("demoUser1").password("123456").authorities("USER").build());
    manager.createUser(User.withUsername("demoUser2").password("123456").authorities("USER").build());
    return manager;
  }

  /**
   * support password grant type
   * @return
   * @throws Exception
   */
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  @Qualifier("UserDetailsService-a")
  private UserDetailsService userDetailsService;

  @PostConstruct
  public void init() {
    try {
      authenticationManagerBuilder
          .userDetailsService(userDetailsService)
          .passwordEncoder(passwordEncoder());
    } catch (Exception e) {
      throw new BeanInitializationException("Security configuration failed", e);
    }
  }
  @Override
  protected void configure(AuthenticationManagerBuilder builder) throws Exception{
    builder.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
  }


}
