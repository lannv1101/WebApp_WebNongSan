package edu.poly.J6ShopNongsan.service.impl;

import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.entity.Authority;
import edu.poly.J6ShopNongsan.repository.AuthorityRepository;
import edu.poly.J6ShopNongsan.service.AccountService;
import edu.poly.J6ShopNongsan.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    private AccountService accountService;

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

	@Override
	public <S extends Authority> S save(S entity) {
		return authorityRepository.save(entity);
	}

	@Override
	public Optional<Authority> findById(Integer id) {
		return authorityRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		authorityRepository.deleteById(id);
	}

	@Override
	public Authority getById(Integer s) {
		// TODO Auto-generated method stub
		return authorityRepository.getById(s);
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {

			List<Account> account = accountService.getAdminstrators();
		return authorityRepository.authoriesOf(account);
	}

   
}
