package edu.poly.J6ShopNongsan.repository;

import edu.poly.J6ShopNongsan.entity.CategoryReport;
import edu.poly.J6ShopNongsan.entity.Order;

import edu.poly.J6ShopNongsan.entity.OrderDetail;
import edu.poly.J6ShopNongsan.entity.OrderProductReport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
	
	@Query("SELECT count(*) FROM OrderDetail o")
	Integer countOderDetail();
	@Query("SELECT NEW CategoryReport(od.product.category, sum(od.quantity),sum(od.price)) FROM OrderDetail od GROUP BY od.product.category")
	List<CategoryReport> revenueByCategory();
	
	@Query("SELECT new OrderProductReport (od.product.name,sum(od.quantity)) From OrderDetail od GROUP BY od.product Order by sum(od.quantity) DESC ")
	List<OrderProductReport> sumProductByOrdetail();
}
