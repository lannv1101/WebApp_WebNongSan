package edu.poly.J6ShopNongsan.config;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.service.AccountService;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder pe;
	@Autowired
	HttpSession session;
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//Cung cấp nguồn dữ liệu đăng nhập
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username ->{
			try {
				Account user = accountService.findById(username).get();
				if (user.getEnable()==false) {
					return null;
				}
				session.setAttribute("username",user.getUsername());
				System.out.println("username: "+ session.getAttribute("username"));
//				String password = pe.encode(user.getPassword());
				String password = user.getPassword();
				if (pe.matches(pe.encode(password),user.getPassword())){
					user.setPassword("");
				}
				String[] roles = user.getAuthorities().stream()
						.map(er ->er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).build();
				
			} catch (NoSuchElementException e) {
				// TODO: handle exception
				throw new UsernameNotFoundException(username + "not found");
			}
			
		});
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //phan quyen su dung
    	http.csrf().disable();
    	http.cors().disable();
        http.authorizeRequests()
                .antMatchers("/lanmarket/order/**").authenticated()
                .antMatchers("/lanmarket/admin/**").hasAnyRole("STAF","DIRE","ADMIN")
                .antMatchers("lanmarket/api/authorities").hasAnyRole("DIRE","ADMIN")
        		.anyRequest().permitAll();
        
        
        http.formLogin()
        	.loginPage("/lanmarket/security/login/form")
        	.loginProcessingUrl("/lanmarket/security/login")
        	.defaultSuccessUrl("/lanmarket/security/login/success",false)
        	.failureUrl("/lanmarket/security/login/error");
        http.rememberMe()
        	.tokenValiditySeconds(86400);
        	
                
        http.exceptionHandling()
        	.accessDeniedPage("/lanmarket/security/login/unauthoried");
        
        http.logout()
        	.logoutUrl("/lanmarket/security/logoff")
        	.logoutSuccessUrl("/lanmarket/security/logoff/success");
        
        
        http.oauth2Login()
        .loginPage("/lanmarket/security/login/form")
        .defaultSuccessUrl("/lanmarket/oauth2/login/success",false)
        .failureUrl("/lanmarket/auth2/login/error")
        .authorizationEndpoint()
        	.baseUri("/oauth2/authorization");
      
    }
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
	}
    
}
