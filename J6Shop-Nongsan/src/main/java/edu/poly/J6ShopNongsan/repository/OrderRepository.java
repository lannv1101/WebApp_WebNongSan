package edu.poly.J6ShopNongsan.repository;

import edu.poly.J6ShopNongsan.entity.Order;
import edu.poly.J6ShopNongsan.entity.SumAmountReport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order,Long> {

	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);
	@Query("SELECT o FROM Order o WHERE o.status =1 or o.status=2 or o.status=3")
	List<Order> findOrderByStatus();
	@Query("SELECT o FROM Order o WHERE o.status =0")
	List<Order> findOrderByStatus0();
	@Query("SELECT count(*) FROM Order o WHERE o.status =0")
	Integer CountOrder0();
	@Query("SELECT SUM(o.amount) FROM Order o")
	Float sumamoant();
	

	
	@Query("SELECT new SumAmountReport(o.createDate, sum(o.amount)) from Order o group by o.createDate")
	List<SumAmountReport> sumAmountByCreatedate();
	
}
