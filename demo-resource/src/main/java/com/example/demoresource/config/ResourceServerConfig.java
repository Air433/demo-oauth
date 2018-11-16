package com.example.demoresource.config;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

/**
 * Created by ouyanggang on 2018/11/15.
 */
@Configuration
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//  @Autowired(required = false)
//  private RemoteTokenServices remoteTokenServices;
//
//  @Autowired
//  private OAuth2ClientProperties oAuth2ClientProperties;
//
//  @Bean
//  @Qualifier("authorizationHeaderRequestMatcher")
//  public RequestMatcher authorizationHeaderRequestMatcher() {
//    return new RequestHeaderRequestMatcher("Authorization");
//  }


  @Override
  public void configure(HttpSecurity http) throws Exception {
//    super.configure(http);
//    http
//            .authorizeRequests()
//            .antMatchers("/login").permitAll()
//            .antMatchers("/res","/res2/res");
//            .access("#oauth2.hasScope('read') and hasRole('USER')");
    http.
        csrf().disable()
        .authorizeRequests()
        .mvcMatchers("/v2/api-docs").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic();
  }

//  @Primary
//  @Bean
//  public RemoteTokenServices tokenServices() {
//    final RemoteTokenServices tokenService = new RemoteTokenServices();
//    tokenService.setCheckTokenEndpointUrl("http://localhost:9015/oauth/check_token");
//    tokenService.setClientId("demoApp");
//    tokenService.setClientSecret("demoAppSecret");
//    return tokenService;
//  }

//  @Override
//  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//    super.configure(resources);
//    if (StringUtils.isEmpty(oAuth2ClientProperties.getClientId())) {
//      resources.resourceId(oAuth2ClientProperties.getClientId());
//    }
//    if (Objects.nonNull(remoteTokenServices)) {
//      resources.tokenServices(remoteTokenServices);
//    }
//  }

}
