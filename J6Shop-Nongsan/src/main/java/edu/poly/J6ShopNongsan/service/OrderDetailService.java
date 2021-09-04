package edu.poly.J6ShopNongsan.service;

import edu.poly.J6ShopNongsan.entity.CategoryReport;
import edu.poly.J6ShopNongsan.entity.OrderDetail;
import edu.poly.J6ShopNongsan.entity.OrderProductReport;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    List<OrderDetail> findAll();

    List<OrderDetail> findAll(Sort sort);

    List<OrderDetail> findAllById(Iterable<Long> longs);

    <S extends OrderDetail> List<S> saveAll(Iterable<S> entities);

    OrderDetail getById(Long aLong);

    <S extends OrderDetail> S save(S entity);

    Optional<OrderDetail> findById(Long aLong);

    void deleteById(Long aLong);

    void delete(OrderDetail entity);

	Integer countOderDetail();

	

	List<CategoryReport> revenueByCategory();

	List<OrderProductReport> sumProductByOrdetail();
}
