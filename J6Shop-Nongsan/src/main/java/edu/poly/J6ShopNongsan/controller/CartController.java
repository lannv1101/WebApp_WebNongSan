package edu.poly.J6ShopNongsan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("lanmarket")
public class CartController {
	@GetMapping("cart/view")
    public String cart(){
        return "site/cart/cart";
    }

}
