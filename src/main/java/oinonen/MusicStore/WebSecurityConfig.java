package oinonen.MusicStore;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
 @Autowired
 private DataSource dataSource;

 @Autowired
 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
     auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
         .dataSource(dataSource)
         .usersByUsernameQuery("select username, password, enabled from Users where username=?")
         .authoritiesByUsernameQuery("select username, role from Users where username=?");
 };
 
 @Override
 protected void configure(HttpSecurity http) throws Exception {
     http
     	.authorizeRequests()
     	.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
     		.antMatchers("/", 
     			"/products", 
     			"/products/**", 
     			"/styles/**", 
     			"/images", 
     			"/buy/**", 
     			"/remove/", 
     			"/remove/**",
     			"/order",
     			"/order/**", 
     			"/productdata/", 
     			"/productdata/**"
     			).permitAll()
     		.anyRequest().authenticated()
        .and()
      .formLogin()
      	.loginPage("/login")
      	.defaultSuccessUrl("/login-success")
      	.permitAll()
      	.failureUrl("/login-error")
      	.and()
      .logout()
      	.logoutSuccessUrl("/logout-success")
      	.permitAll();

 };
 
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  };
};