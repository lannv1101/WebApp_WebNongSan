package edu.poly.J6ShopNongsan.service;

import edu.poly.J6ShopNongsan.entity.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    List<Category> findAll(Sort sort);

    List<Category> findAllById(Iterable<Long> longs);

    Category getById(Long aLong);

    <S extends Category> S save(S entity);

    Optional<Category> findById(Long aLong);

    void deleteById(Long aLong);

    void delete(Category entity);

    <S extends Category> Optional<S> findOne(Example<S> example);

    <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Category> long count(Example<S> example);

    <S extends Category> boolean exists(Example<S> example);

	List<Category> findCategoryByName(String name);
}
