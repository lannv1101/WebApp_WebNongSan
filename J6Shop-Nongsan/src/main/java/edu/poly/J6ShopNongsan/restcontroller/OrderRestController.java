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

import com.fasterxml.jackson.databind.JsonNode;

import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.entity.Order;
import edu.poly.J6ShopNongsan.entity.SumAmountReport;
import edu.poly.J6ShopNongsan.service.OrderService;
import edu.poly.J6ShopNongsan.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lanmarket/api/orders")
public class OrderRestController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("")
	public Order create(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}

	@GetMapping("sumamountbycreatedate")
	public List<SumAmountReport> sumamountbycreatedate(){
		return orderService.sumAmountByCreatedate();
	}
	
	@GetMapping("")
	public List<Order> get() {
		return orderService.findAll();
	}
	@GetMapping("order0")
	public List<Order> get0() {
		return orderService.findOrderByStatus0();
	}
	@GetMapping("count")
	public Integer count() {
		return orderService.CountOrder0();
	}
	
	@GetMapping("sum")
	public Float sumamount() {
		return orderService.sumamoant();
	}
	@GetMapping("{id}")
	public Order finbyid(@PathVariable("id")Long id) {
		return orderService.findById(id).get();
	}
	
	@PutMapping("{id}")
	public Order update(@PathVariable("id")Long id,@RequestBody Order order) {
		return orderService.save(order);
	}
	@DeleteMapping("{id}")
	public void deleteOrder(@PathVariable("id")Long id) {
		orderService.deleteById(id);
	}
}
