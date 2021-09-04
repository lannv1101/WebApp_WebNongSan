package edu.poly.J6ShopNongsan.service.impl;

import edu.poly.J6ShopNongsan.entity.CategoryReport;
import edu.poly.J6ShopNongsan.entity.OrderDetail;
import edu.poly.J6ShopNongsan.entity.OrderProductReport;
import edu.poly.J6ShopNongsan.repository.OrderDetailRepository;
import edu.poly.J6ShopNongsan.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    
   

	@Override
	public List<OrderProductReport> sumProductByOrdetail() {
		return orderDetailRepository.sumProductByOrdetail();
	}

	@Override
	public List<CategoryReport> revenueByCategory() {
		return orderDetailRepository.revenueByCategory();
	}

	@Override
	public Integer countOderDetail() {
		return orderDetailRepository.countOderDetail();
	}

	@Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetail> findAll(Sort sort) {
        return orderDetailRepository.findAll(sort);
    }

    @Override
    public List<OrderDetail> findAllById(Iterable<Long> longs) {
        return orderDetailRepository.findAllById(longs);
    }

    @Override
    public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
        return orderDetailRepository.saveAll(entities);
    }

    @Override
    public OrderDetail getById(Long aLong) {
        return orderDetailRepository.getById(aLong);
    }

    @Override
    public <S extends OrderDetail> S save(S entity) {
        return orderDetailRepository.save(entity);
    }

    @Override
    public Optional<OrderDetail> findById(Long aLong) {
        return orderDetailRepository.findById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        orderDetailRepository.deleteById(aLong);
    }

    @Override
    public void delete(OrderDetail entity) {
        orderDetailRepository.delete(entity);
    }
}
