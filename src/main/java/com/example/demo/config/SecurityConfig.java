package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder)
	{
		UserDetails admin = User.withUsername("rkd")
				.password(encoder.encode("rkd1")).roles("ADMIN").build();
				
		UserDetails user = User.withUsername("abc")
				.password(encoder.encode("abc1")).roles("USER").build();
		
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	
	
	  @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	  Exception {
	  
		  return http.csrf()
				  .disable().authorizeHttpRequests().requestMatchers("/test/m2").permitAll()
				  .and().authorizeHttpRequests().requestMatchers("/test/**").authenticated()
				  .and().formLogin() 
				  .and().build();
		  
	  }
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
