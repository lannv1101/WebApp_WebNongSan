package edu.poly.J6ShopNongsan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.J6ShopNongsan.service.UserService;

@Controller
@RequestMapping("lanmarket/oauth2")
public class oauth2LoginController {
	@Autowired
    UserService userService;
	 @GetMapping("login/success")
	    public String success(OAuth2AuthenticationToken oauth2){
	        userService.loginFromOAuth2(oauth2);

	        return "forward:/lanmarket/security/login/success";
	    }

	 @GetMapping("login/error")
	    public String error(Model model){
			model.addAttribute("message", "Sai thông tin đăng nhập");
			return "site/login/formlogin";
	    }
}
