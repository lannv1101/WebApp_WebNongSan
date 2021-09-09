package edu.poly.J6ShopNongsan.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.poly.J6ShopNongsan.entity.Addresses;
import edu.poly.J6ShopNongsan.service.AccountService;
import edu.poly.J6ShopNongsan.service.AddressesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.entity.Order;
import edu.poly.J6ShopNongsan.service.OrderService;
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("lanmarket")
public class CheckoutController {

	@Autowired
	AccountService accountService;
	@Autowired
	AddressesService addressesService;
	@Autowired
	OrderService orderService;

	@Autowired
	HttpSession session;
	@GetMapping("order/checkout")
    public String checkout(Model model){
		String username = (String) session.getAttribute("username");

		Optional<Account> account =accountService.findByUsername(username);
		Optional<Addresses> addresses = addressesService.findAddressDefault(username);

		if (addresses.isEmpty()){
			model.addAttribute("address",account.get());
//			System.out.println("account"+account.get());

		}else {
			model.addAttribute("address",addresses.get());
//			System.out.println("addresses"+addresses.get());
		}


		model.addAttribute("account",account.get());//lay email cho paypal
		return "site/checkout/checkout";
    }


    //test paypal page rieng
//	@GetMapping("order/checkout/test")
//	public String checkoutpaypal(){
//		return "site/checkout/checkout-paypal";
//	}

	@GetMapping("order/list")
    public String orderlists(Model model, HttpServletRequest request){
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
        return "site/order/list";
    }

	@GetMapping("order/detail/{id}")
    public String orderdetailid(@PathVariable("id") Long id, Model model){
		model.addAttribute("orders", orderService.findById(id).get());
        return "site/order/detail";
    }
	@PostMapping("order/detail/update/{id}")
    public String postorderdetail(@PathVariable("id") Long id, Model model){
		Optional<Order> opt = orderService.findById(id);
	
	 if (opt.isPresent()) {
		Order entity = opt.get();
		 Order od = new Order();
		 BeanUtils.copyProperties(opt, od);
			
			od.setStatus(4);
			orderService.save(od);
			  return "site/order/detail";
	}
	
        return "site/order/detail";
    }

}
