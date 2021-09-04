package edu.poly.J6ShopNongsan.restcontroller;

import java.util.List;

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

import edu.poly.J6ShopNongsan.entity.Category;
import edu.poly.J6ShopNongsan.entity.Product;
import edu.poly.J6ShopNongsan.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("lanmarket/api")
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("categories")
	public List<Category> findall() {
		return categoryService.findAll();
	}
	
	@GetMapping("categories/{id}")
	public Category finbyid(@PathVariable("id")Long id) {
		return categoryService.findById(id).get();
	}
	
	@GetMapping("categories/{name}")
	public List<Category> finbyname(@PathVariable("name")String name) {
		return categoryService.findCategoryByName(name);
	}
	
	@PostMapping("categories")
	public Category postproduct(@RequestBody Category category) {
		return categoryService.save(category);
	}
	
	@PutMapping("categories/{id}")
	public Category update(@PathVariable("id")Long id,@RequestBody Category category) {
		return categoryService.save(category);
	}
	@DeleteMapping("categories/{id}")
	public void deleteProduct(@PathVariable("id")Long id) {
		categoryService.deleteById(id);
	}

}
