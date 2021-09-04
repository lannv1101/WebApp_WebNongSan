package edu.poly.J6ShopNongsan.controller;

import edu.poly.J6ShopNongsan.entity.Product;
import edu.poly.J6ShopNongsan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("lanmarket")
public class ShopController {
    @Autowired
    ProductService productService;
	@GetMapping("shop")
    public String shop(Model model,  @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size){
		///tim kiem theo ten va phan trang 
				int currentPage = page.orElse(1);
				int pageSize = size.orElse(6);
				
				Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("name"));
				Page<Product>resultPage = null;
				
				if (StringUtils.hasText(name)) {
					resultPage = productService.findByNameContaining(name, pageable);
					model.addAttribute("name",name);
				}else {
					resultPage = productService.findProductDiscountnull(pageable);
				}
				
				
				
				int totalPages = resultPage.getTotalPages();
				if (totalPages >0) {
					int start = Math.max(1, currentPage-2);
					int end = Math.min(currentPage+2, totalPages);	
					
					if (totalPages >5) {
						if (end== totalPages) start = end-5;
						else if(start ==1) end =start+5;
							
						
					}
					List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
							.boxed()
							.collect(Collectors.toList());
					
					
					model.addAttribute("pageNumbers", pageNumbers);
				}
				
				model.addAttribute("shop", "co");
				model.addAttribute("name", name);
				model.addAttribute("products", resultPage);
		
			 
				
       
        return "site/shop/shop";
    }
	
    // sort by unitprince
    @GetMapping("shop/sortByUnitPrince")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size
			
			) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by(Direction.DESC, "price"));
		Page<Product>resultPage = null;
		
		if (StringUtils.hasText(name)) {
			resultPage = productService.findByNameContaining(name, pageable);
			model.addAttribute("name",name);
		}else {
			resultPage = productService.findProductDiscountnull(pageable);
		}
		
		
		
		int totalPages = resultPage.getTotalPages();
		if (totalPages >0) {
			int start = Math.max(1, currentPage-2);
			int end = Math.min(currentPage+2, totalPages);	
			
			if (totalPages >5) {
				if (end== totalPages) start = end-5;
				else if(start ==1) end =start+5;
					
				
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("price", "co");
		model.addAttribute("name", name);
		model.addAttribute("products", resultPage);
		
		return "site/shop/shop";
		
		
	}
    // sort by createdate
    @GetMapping("shop/sortbycreatedate")
	public String sortbycreatedate(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size
			
			) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by(Direction.DESC, "createDate"));
		Page<Product>resultPage = null;
		
		if (StringUtils.hasText(name)) {
			resultPage = productService.findByNameContaining(name, pageable);
			model.addAttribute("name",name);
		}else {
			resultPage = productService.findProductDiscountnull(pageable);
		}
		
		
		
		int totalPages = resultPage.getTotalPages();
		if (totalPages >0) {
			int start = Math.max(1, currentPage-2);
			int end = Math.min(currentPage+2, totalPages);	
			
			if (totalPages >5) {
				if (end== totalPages) start = end-5;
				else if(start ==1) end =start+5;
					
				
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			
			
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("date", "co");
		model.addAttribute("name", name);
		model.addAttribute("products", resultPage);
		
		return "site/shop/shop";
		
		
	}
    
    // sort by findbyprince chua dc
    @GetMapping("shop/findbyprice")
	public String searchbyPrice(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,@RequestParam("price1") Double price1,@RequestParam("price2") Double price2
			
			) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by(Direction.DESC, "price"));
		Page<Product>resultPage = null;
		
		if (!StringUtils.hasText(name)) {
			resultPage = productService.findByPriceBetween(price1, price2, pageable);
			model.addAttribute("price1", price1);
			model.addAttribute("price2", price2);
		}else {
			resultPage = productService.findProductDiscountnull(pageable);
		}
		
		
		
		int totalPages = resultPage.getTotalPages();
		if (totalPages >0) {
			int start = Math.max(1, currentPage-2);
			int end = Math.min(currentPage+2, totalPages);	
			
			if (totalPages >5) {
				if (end== totalPages) start = end-5;
				else if(start ==1) end =start+5;
					
				
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("findbyprice", "co");
		model.addAttribute("price1", price1);
		model.addAttribute("price2", price2);
		model.addAttribute("products", resultPage);
		
		return "site/shop/shop";
		
		
	}
	@GetMapping("shop/product/list")
    public String shoplistbycategoryid(Model model,@RequestParam("cid") Optional<Long> cid, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size){
		
		
			
			
			
			
			///tim kiem theo ten va phan trang 
			int currentPage = page.orElse(1);
			int pageSize = size.orElse(6);
			
			Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("name"));
			Page<Product>resultPage = null;
			
			if (StringUtils.hasText(name)) {
				resultPage = productService.findByNameContaining(name, pageable);
				model.addAttribute("name",name);
			}else {
				Page<Product> list = productService.findByCategoryId(cid.get(), pageable);
				resultPage = list;
			}
			
			
			
			int totalPages = resultPage.getTotalPages();
			if (totalPages >0) {
				int start = Math.max(1, currentPage-2);
				int end = Math.min(currentPage+2, totalPages);	
				
				if (totalPages >5) {
					if (end== totalPages) start = end-5;
					else if(start ==1) end =start+5;
						
					
				}
				List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
						.boxed()
						.collect(Collectors.toList());
				
				
				model.addAttribute("pageNumbers", pageNumbers);
			}
			
			model.addAttribute("list", "co");
			
			model.addAttribute("products", resultPage);
			model.addAttribute("cid", cid.get());
	
		 

   
    return "site/shop/shop";
		
       
       
    }
	
	
	

}
