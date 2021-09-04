package edu.poly.J6ShopNongsan.service.impl;

import edu.poly.J6ShopNongsan.entity.Product;
import edu.poly.J6ShopNongsan.entity.ProductCategoryReport;
import edu.poly.J6ShopNongsan.repository.ProductRepository;
import edu.poly.J6ShopNongsan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    
  
	

	@Override
	public Optional<Product> findByNameLike(String name) {
		return productRepository.findByNameLike(name);
	}

	@Override
	public List<ProductCategoryReport> countProductByCate() {
		return productRepository.countProductByCate();
	}

	@Override
	public Page<Product> findProductDiscountnull(Pageable pageable) {
		return productRepository.findProductDiscountnull(pageable);
	}

	@Override
	public Page<Product> findByPriceBetween(Double price1, Double price2, Pageable pageable) {
		return productRepository.findByPriceBetween(price1, price2, pageable);
	}

	@Override
	public Page<Product> findByNameContaining(String name, Pageable pageable) {
		return productRepository.findByNameContaining(name, pageable);
	}

	

	@Override
	public Page<Product> findByCategoryId(Long cid, Pageable pageable) {
		return productRepository.findByCategoryId(cid, pageable);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public List<Product> findAllById(Iterable<Long> longs) {
        return productRepository.findAllById(longs);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return productRepository.saveAll(entities);
    }

    @Override
    public Product getById(Long aLong) {
        return productRepository.getById(aLong);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        productRepository.deleteById(aLong);
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        return productRepository.findAll(example, pageable);
    }

	@Override
	public List<Product> findProductDiscount() {
		return productRepository.findProductDiscount();
	}

    
}
