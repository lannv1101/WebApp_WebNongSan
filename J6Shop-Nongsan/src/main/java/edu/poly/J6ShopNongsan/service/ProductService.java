package edu.poly.J6ShopNongsan.service;

import edu.poly.J6ShopNongsan.entity.Product;
import edu.poly.J6ShopNongsan.entity.ProductCategoryReport;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    List<Product> findAll(Sort sort);

    List<Product> findAllById(Iterable<Long> longs);

    <S extends Product> List<S> saveAll(Iterable<S> entities);

    Product getById(Long aLong);

    <S extends Product> S save(S entity);

    Optional<Product> findById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Product entity);

    <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);



	List<Product> findProductDiscount();

	Page<Product> findByNameContaining(String name, Pageable pageable);

	Page<Product> findAll(Pageable pageable);

	

	Page<Product> findByPriceBetween(Double price1, Double price2, Pageable pageable);

	Page<Product> findByCategoryId(Long cid, Pageable pageable);

	Page<Product> findProductDiscountnull(Pageable pageable);

	List<ProductCategoryReport> countProductByCate();

	Optional<Product> findByNameLike(String name);

	
}
