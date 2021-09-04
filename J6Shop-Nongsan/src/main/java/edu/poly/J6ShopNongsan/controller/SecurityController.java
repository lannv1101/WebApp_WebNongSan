package edu.poly.J6ShopNongsan.controller;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import edu.poly.J6ShopNongsan.entity.Authority;
import edu.poly.J6ShopNongsan.entity.Role;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.model.AccountDTO;
import edu.poly.J6ShopNongsan.service.AccountService;
import edu.poly.J6ShopNongsan.service.AuthorityService;



@Controller
@RequestMapping("lanmarket")
public class SecurityController {
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	AccountService accountService;
	@Autowired
	AuthorityService authorityService;
	@Autowired
	HttpSession session;

	@GetMapping("security/login/form")
	public String loginForm(Model model) {
//		model.addAttribute("message", "Vui lòng đăng nhập");
		return "site/login/formlogin";
	}
	@GetMapping("security/signup/form")
	public String signupForm(Model model) {
//		model.addAttribute("message", "Vui lòng đăng nhập");
		model.addAttribute("account", new AccountDTO());
		return "site/login/signup";
	}
	@PostMapping("security/signup/form")
	public ModelAndView postsignupForm(ModelMap model,@Valid @ModelAttribute("account") AccountDTO dto,BindingResult result,HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
//		model.addAttribute("message", "Vui lòng đăng nhập");
		Optional<Account> opt = accountService.findByEmail(dto.getEmail());
		if (result.hasErrors()) {
		   model.addAttribute("message", "Thông tin người dùng không hợp lệ");
		}
		else if (opt.isPresent()) {
			System.out.println("present");
			model.addAttribute("message", "Email đã tồn tại");
			return new ModelAndView("site/login/signup",model);
			
		}else {
			Authority au = new Authority();
			Role rl =  new Role();
			
			Account account = new Account();
			
			BeanUtils.copyProperties(dto, account);
			account.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
//			account.setPassword(dto.getPassword());
			account.setEnable(false);
			account.setPhoto("");
			account.setVerification_code("");
			au.setAccount(account);
			rl.setId("CUST");
			au.setRole(rl);
			
			accountService.register(account, getSiteURL(request));
			authorityService.save(au);
			model.addAttribute("message", "Vui lòng verify trước khi đăng nhập");
			return new ModelAndView("site/login/signup");
		}
		return new ModelAndView("site/login/signup",model);
		
	}
	@GetMapping("/login/verify")
	public String verifyUser(Model model,@Param("code") String code) {
		if (accountService.verify(code)) {
			model.addAttribute("message", "Verify successfully");
			return "site/login/verifi_success";
		} else {
			model.addAttribute("message", "Verify failed");
			return "site/login/verify_fail";
		}
	}
	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
	
	@GetMapping("security/forgotpassword/form")
	public String forgotpassForm(Model model) {
//		model.addAttribute("message", "Vui lòng đăng nhập");
		return "site/login/forgotpassword";
	}
	
	@GetMapping("security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		return "forward:/lanmarket/home";
	}
	
	@GetMapping("security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "site/login/formlogin";
	}
	@GetMapping("security/login/unauthoried")
	public String unauthoried(Model model) {
		
		return "site/401.html";
	}
	@GetMapping("security/logoff/success")
	public String logoffSuccess(Model model) {
		session.removeAttribute("username");
		model.addAttribute("message", "Bạn đã đăng xuất");
		return "site/login/formlogin";
	}
//	@GetMapping("security/logoff")
//	public String logoffSucces(Model model) {
////		model.addAttribute("message", "Bạn đã đăng xuất");
//		return "site/login/formlogin";
//	}
}
