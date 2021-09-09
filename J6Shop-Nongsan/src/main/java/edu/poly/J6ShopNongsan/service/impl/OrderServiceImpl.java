package edu.poly.J6ShopNongsan.service.impl;

import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.entity.Order;
import edu.poly.J6ShopNongsan.entity.OrderDetail;
import edu.poly.J6ShopNongsan.entity.Product;
import edu.poly.J6ShopNongsan.entity.SumAmountReport;
import edu.poly.J6ShopNongsan.repository.OrderDetailRepository;
import edu.poly.J6ShopNongsan.repository.OrderRepository;
import edu.poly.J6ShopNongsan.service.AccountService;
import edu.poly.J6ShopNongsan.service.OrderDetailService;
import edu.poly.J6ShopNongsan.service.OrderService;
import edu.poly.J6ShopNongsan.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderdetailrepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderdetailService;
    @Autowired
    ProductService productService;
    @Autowired
    AccountService accountService;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }



	@Override
	public List<SumAmountReport> sumAmountByCreatedate() {
		return orderRepository.sumAmountByCreatedate();
	}




	@Override
	public Float sumamoant() {
		return orderRepository.sumamoant();
	}







	@Override
	public Integer CountOrder0() {
		return orderRepository.CountOrder0();
	}







	@Override
	public List<Order> findOrderByStatus() {
		return orderRepository.findOrderByStatus();
	}



	@Override
	public List<Order> findOrderByStatus0() {
		return orderRepository.findOrderByStatus0();
	}



	@Override
    public List<Order> findAll(Sort sort) {
        return orderRepository.findAll(sort);
    }

    @Override
    public List<Order> findAllById(Iterable<Long> longs) {
        return orderRepository.findAllById(longs);
    }

    @Override
    public Order getById(Long aLong) {
        return orderRepository.getById(aLong);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public  Order save(Order entity) {
        return orderRepository.save(entity);
    }

    @Override
    public Optional<Order> findById(Long aLong) {
        return orderRepository.findById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepository.deleteById(aLong);
    }

    @Override
    public void delete(Order entity) {
        orderRepository.delete(entity);
    }

    @Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();// chuyen doi json
		Order order = mapper.convertValue(orderData, Order.class);
		
	
//	    order.setAccount(order.getAccount());
		
	
		
		order.setStatus(0);
		orderService.save(order); //sai cho save order

		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};
		// lấy dữ liệu từ orderdetail
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)

				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
                for (OrderDetail orderDetail : details) { // duyet tu orderdetail tren angularjs
                    Product pr= productService.getById(orderDetail.getProduct().getId()); //lay id cua san pham dua tren productid cua angularjs
                    pr.setQuantity(pr.getQuantity()-orderDetail.getQuantity());// lay so luong ban dau - so luong mua
                    productService.save(pr);///luu san pham sau khi tru
                    System.out.println("id"+orderDetail.getProduct().getId());
                    System.out.println("quantity"+orderDetail.getQuantity());
                }
		orderdetailService.saveAll(details);// luu nhieu orderdetail cùng 1 lúc
//		System.out.println("username"+order.getAccount().getUsername());
		return order;
		
	}



	@Override
	public List<Order> findByUsername(String username) {
		// TODO Auto-generated method stub
		return orderRepository.findByUsername(username);
	}
}
