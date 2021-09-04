package edu.poly.J6ShopNongsan.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.J6ShopNongsan.entity.Authority;
import edu.poly.J6ShopNongsan.entity.Role;
import edu.poly.J6ShopNongsan.service.RoleService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("lanmarket/api")
public class RoleRestController {

	
	@Autowired
	RoleService roleService;
	
	
	@GetMapping("roles")
	public List<Role> findall() {
		return roleService.findAll();
	}
}
