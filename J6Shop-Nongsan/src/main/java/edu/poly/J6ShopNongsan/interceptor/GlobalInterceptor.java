package edu.poly.J6ShopNongsan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.J6ShopNongsan.service.CategoryService;
import edu.poly.J6ShopNongsan.service.ProductService;

@Component
public class GlobalInterceptor implements HandlerInterceptor  {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("cates", categoryService.findAll());
		request.setAttribute("pdiscount", productService.findProductDiscount());
	}
	
	

}
