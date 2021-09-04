package edu.poly.J6ShopNongsan.service;

import edu.poly.J6ShopNongsan.entity.Order;
import edu.poly.J6ShopNongsan.entity.SumAmountReport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();

    List<Order> findAll(Sort sort);

    List<Order> findAllById(Iterable<Long> longs);

    Order getById(Long aLong);

    Page<Order> findAll(Pageable pageable);

     Order save(Order entity);

    Optional<Order> findById(Long aLong);

    void deleteById(Long aLong);

    void delete(Order entity);

	Order create(JsonNode orderData);

	List<Order> findByUsername(String username);

	List<Order> findOrderByStatus0();

	List<Order> findOrderByStatus();

	Integer CountOrder0();

	Float sumamoant();



	List<SumAmountReport> sumAmountByCreatedate();





	
}
 