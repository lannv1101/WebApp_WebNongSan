package edu.poly.J6ShopNongsan.repository;

import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.entity.Product;
import edu.poly.J6ShopNongsan.entity.ProductCategoryReport;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {
	
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	Page<Product> findByCategoryId(Long cid,Pageable pageable);
	@Query("SELECT p FROM Product p WHERE p.available=true AND p.discount IS NOT NULL")
	List<Product> findProductDiscount();
	@Query("SELECT p FROM Product p WHERE p.available=true AND p.discount IS NULL")
	Page<Product> findProductDiscountnull(Pageable pageable);
	
	Page<Product> findByNameContaining(String name, Pageable pageable);
	
	Page<Product> findByPriceBetween(Double price1,Double price2,Pageable pageable);
	
	@Query("SELECT new ProductCategoryReport(p.category.name, count(p.category)) FROM Product p GROUP BY p.category order by count(p.category) DESC ")
	List<ProductCategoryReport> countProductByCate();
	
	Optional<Product> findByNameLike(String name);

}
