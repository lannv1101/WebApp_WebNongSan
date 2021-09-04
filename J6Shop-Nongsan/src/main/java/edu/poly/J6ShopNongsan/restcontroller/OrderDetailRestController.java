package edu.poly.J6ShopNongsan.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.J6ShopNongsan.entity.CategoryReport;
import edu.poly.J6ShopNongsan.entity.OrderProductReport;
import edu.poly.J6ShopNongsan.service.OrderDetailService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lanmarket/api/orderdetails")
public class OrderDetailRestController {
	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping("count")
	public Integer count() {
		return orderDetailService.countOderDetail();
	}
	@GetMapping("sumproductbyordetail")
	public List<OrderProductReport> sumProductByOrdetail() {
		return orderDetailService.sumProductByOrdetail();
	}
	@GetMapping(value="revenuebycategory")
	public ResponseEntity<Iterable<CategoryReport>> revenueByCategory(){
		try {
			return new ResponseEntity<Iterable<CategoryReport>>(orderDetailService.revenueByCategory(),HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Iterable<CategoryReport>>(HttpStatus.BAD_REQUEST);
		}
	}

}
