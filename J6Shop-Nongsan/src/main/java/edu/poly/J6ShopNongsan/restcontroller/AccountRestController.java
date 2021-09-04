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
import edu.poly.J6ShopNongsan.entity.Category;
import edu.poly.J6ShopNongsan.service.AccountService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("lanmarket/api")
public class AccountRestController {
	@Autowired
	AccountService accountService;
	
//	
//	@GetMapping("accounts")
//	public List<Account> findall() {
//		return accountService.findAll();
//	}
	
	@GetMapping("accounts")
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return accountService.getAdminstrators();
		}
		return accountService.findAll();
	}
	
	
	@GetMapping("accounts/{id}")
	public Account finbyid(@PathVariable("id")String id) {
		return accountService.findById(id).get();
	}
	
	
	
	@PostMapping("accounts")
	public Account postproduct(@RequestBody Account account) {
		return accountService.save(account);
	}
	
	@PutMapping("accounts/{id}")
	public Account update(@PathVariable("id")String id,@RequestBody Account account) {
		return accountService.save(account);
	}
	@DeleteMapping("accounts/{id}")
	public void deleteProduct(@PathVariable("id")String id) {
		accountService.deleteById(id);
	}

}
