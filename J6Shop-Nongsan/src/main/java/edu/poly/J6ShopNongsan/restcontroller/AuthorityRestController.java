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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.entity.Authority;
import edu.poly.J6ShopNongsan.service.AuthorityService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("lanmarket/api")
public class AuthorityRestController {

	
	@Autowired
	AuthorityService authorityService;
	
	@GetMapping("authorities/findall")
	public List<Authority> findall() {
		return authorityService.findAll();
	}
	@GetMapping("authorities")
	public List<Authority> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return authorityService.findAuthoritiesOfAdministrators();
		}
		return authorityService.findAll();
	}
	
	
	@PostMapping("authorities")
	public Authority postproduct(@RequestBody Authority authority) {
		return authorityService.save(authority);
	}
	
	@DeleteMapping("authorities/{id}")
	public void deleteProduct(@PathVariable("id")Integer id) {
		authorityService.deleteById(id);
	}
}
