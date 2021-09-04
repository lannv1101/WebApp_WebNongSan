package edu.poly.J6ShopNongsan.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.entity.Authority;
import edu.poly.J6ShopNongsan.entity.Role;

@Service
public class UserService implements UserDetails {

	@Autowired
	AccountService accountService;
	@Autowired
	AuthorityService authorityService;
	@Autowired
	BCryptPasswordEncoder pe;

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		Optional<Account> opt = accountService.findByEmail(email);
		if (opt.isPresent()) {
			UserDetails user = User.withUsername(email).password(pe.encode(password)).roles("CUST").build();

			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			
			
		}else {
			Authority au = new Authority();
			Role rl =  new Role();
			
			Account account = new Account();
			
		
			account.setUsername(email);
			account.setPassword(password);
			account.setEmail(email);
			account.setEnable(true);
			account.setPhoto("");
			account.setVerification_code("");
			au.setAccount(account);
			rl.setId("CUST");
			au.setRole(rl);
			
			accountService.save(account);
			authorityService.save(au);
			UserDetails user = User.withUsername(email).password(pe.encode(password)).roles("CUST").build();

			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);

		

	}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
