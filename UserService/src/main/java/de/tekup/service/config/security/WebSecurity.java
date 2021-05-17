package de.tekup.service.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.tekup.service.data.services.UserService;
import lombok.AllArgsConstructor;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	private UserService userService;
	private BCryptPasswordEncoder bcrypt;
	
	// configure web security to accept requests
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//accept all requests
		http.authorizeRequests().antMatchers("/**").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bcrypt);
	}
	
	
}
