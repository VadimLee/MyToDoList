package com.leev2107.myToDoList.Configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfigurerAdapter extends WebSecurityConfigurerAdapter {
	// matches for different roles
	private String[] employeeMatches = new String[] { "/*", "/api/", "/task/changeStatus/**",
			"/api/task/changeStatus/**" };
	private String[] adminMatches = new String[] { "/**", "/api/**" };

	@Autowired
	DataSource securityDS;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDS);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(employeeMatches).hasRole("EMPLOYEE").antMatchers(adminMatches)
				.hasRole("ADMIN").anyRequest().authenticated().and().formLogin().permitAll().and().logout().permitAll()
				.and().httpBasic().and().csrf().disable();
	}

}
