package edu.poly.J6ShopNongsan.restcontroller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.J6ShopNongsan.entity.Product;
import edu.poly.J6ShopNongsan.entity.ProductCategoryReport;
import edu.poly.J6ShopNongsan.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("lanmarket/api")
public class ProductRestController {
	
	@Autowired
	ProductService productService;
	
	
	
	@GetMapping("product")
	public List<Product> findall() {
		return productService.findAll();
	}
	
	@GetMapping("product/{id}")
	public Product finbyid(@PathVariable("id")Long id) {
		return productService.findById(id).get();
	}
	@GetMapping("product/countproductbycate")
	public List<ProductCategoryReport> countproductbycate(){
		return productService.countProductByCate();
	}
	
	@PostMapping("product")
	public Product postproduct(@RequestBody Product product) {
		return productService.save(product);
	}
	
	@PutMapping("product/{id}")
	public Product update(@PathVariable("id")Long id,@RequestBody Product product) {
		return productService.save(product);
	}
	@DeleteMapping("product/{id}")
	public void deleteProduct(@PathVariable("id")Long id) {
		 productService.deleteById(id);
	}
//	@GetMapping("product/{name}")
//	public Product finbynamelike(@PathVariable("name")String name) {
//		
//		return productService.findByNameLike(name).get();
//	}
	
	
	
	
	
	

}
