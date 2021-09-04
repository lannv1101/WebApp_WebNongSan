package edu.poly.J6ShopNongsan.service;

import edu.poly.J6ShopNongsan.entity.Authority;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface AuthorityService {
    List<Authority> findAll();

   

	Authority getById(Integer s);



	void deleteById(Integer id);



	Optional<Authority> findById(Integer id);



	<S extends Authority> S save(S entity);



	List<Authority> findAuthoritiesOfAdministrators();





	
}
