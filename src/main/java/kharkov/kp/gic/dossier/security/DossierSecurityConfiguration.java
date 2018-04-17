package kharkov.kp.gic.dossier.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration 
public class DossierSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers("/**").permitAll();
		
		//it needs for h2 console - http://javasampleapproach.com/spring-framework/spring-security/configure-spring-security-access-h2-database-console-spring-boot-project
		http.headers().frameOptions().disable();
		// @formatter:on
    }
}
