package edu.poly.J6ShopNongsan.controller;


import edu.poly.J6ShopNongsan.entity.Product;
import edu.poly.J6ShopNongsan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("lanmarket")
public class DetailController {

    @Autowired
    ProductService productService;
	@GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id){
       Optional<Product> detailitem= productService.findById(id);
       model.addAttribute("detailitem",detailitem.get());
        return "site/detail/details";
    }

}
