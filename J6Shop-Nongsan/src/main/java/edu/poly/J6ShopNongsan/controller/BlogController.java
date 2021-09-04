package edu.poly.J6ShopNongsan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("lanmarket")
public class BlogController {
	@GetMapping("blog")
    public String blog(){
        return "site/blog/blog";
    }
	@GetMapping("blogdetail")
    public String blogdetail(){
        return "site/blog/blog-details";
    }

}
