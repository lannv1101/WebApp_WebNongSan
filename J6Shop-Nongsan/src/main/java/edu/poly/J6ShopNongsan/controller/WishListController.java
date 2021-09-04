package edu.poly.J6ShopNongsan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WishListController {
	
	@RequestMapping("/lanmarket/wishlist/view")
    public String cart(){
        return "site/wishlist/wishlist";
    }


}
