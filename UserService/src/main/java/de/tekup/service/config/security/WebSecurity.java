package de.tekup.service.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
	private Environment env;
	
	// configure web security to accept requests
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//accept all requests for register
		http.authorizeRequests().antMatchers("/api/users/register").permitAll();
		// accept only the gateway request 
		http.authorizeRequests().antMatchers("/**")
								.hasIpAddress(env.getProperty("gateway.ip"))
								.and()
								.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}
	
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, env, authenticationManager());
		authenticationFilter.setFilterProcessesUrl(env.getProperty("login.url.path"));
		return authenticationFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bcrypt);
	}
	
	
}
