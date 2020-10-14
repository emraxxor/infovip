package com.github.infovip.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.infovip.spring.components.AuthSuccessHandler;
import com.github.infovip.spring.components.DefaultLogoutHandler;
import com.github.infovip.spring.services.ApplicationUserService;

/**
 * 
 * @author Attila Barna
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter  {

	
	private final PasswordEncoder pw;
	
	private final ApplicationUserService userService;
	
	@Autowired
	private DefaultLogoutHandler logoutHandler;
	
	@Autowired
	private AuthSuccessHandler successHandler;
	
	@Autowired
	public ApplicationSecurityConfiguration(PasswordEncoder pw, ApplicationUserService au) {
		this.pw = pw;
		this.userService = au;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			 csrf().disable()
		 	.authorizeRequests()
		 	.antMatchers("/api/user/**","/activity","/activity/**","/user","/user/**").hasRole(ApplicationPermission.ROLE_USER.get())
			.antMatchers("/**").permitAll()
		 	.and()
			.formLogin()
			 	.loginPage("/login")
			 	.defaultSuccessUrl("/activity", true)
			 	.failureUrl("/login?err=invalid")
			 	.passwordParameter("password")
			 	.usernameParameter("email")
			    .successHandler(successHandler)
			.and()
			    .rememberMe()
		 	.and()
		 	.logout()
		 		.logoutUrl("/logout")
		 		.addLogoutHandler(logoutHandler)
		 		.clearAuthentication(true)
		 		.invalidateHttpSession(true)
		 		.deleteCookies("JSESSIONID")
		 		.logoutSuccessUrl("/login");
		
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoProvider() {
		DaoAuthenticationProvider dp = new DaoAuthenticationProvider();
		dp.setPasswordEncoder(pw);
		dp.setUserDetailsService(userService);
		return dp;
	}

}
