package edu.poly.J6ShopNongsan.repository;

import edu.poly.J6ShopNongsan.entity.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	@Query("SELECT p FROM Category p WHERE p.name=?1")
	List<Category> findCategoryByName(String name);
}
