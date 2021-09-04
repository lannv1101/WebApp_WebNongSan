package edu.poly.J6ShopNongsan.repository;

import edu.poly.J6ShopNongsan.entity.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account,String> {

	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN('DIRE','STAF','ADMIN')")
	List<Account> getAdminstrators();
	
	@Query("SELECT a FROM Account a WHERE a.email = ?1")
	Optional<Account> findByEmail(String email);
	@Query("SELECT a FROM Account a WHERE a.username = ?1")
	Optional<Account> findByUsername(String username);
	
	@Query("SELECT a FROM Account a WHERE a.verification_code = ?1")
	public Account findByVerificationCode(String code);
}
