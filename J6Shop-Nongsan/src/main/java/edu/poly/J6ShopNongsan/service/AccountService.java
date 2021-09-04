package edu.poly.J6ShopNongsan.service;

import edu.poly.J6ShopNongsan.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

public interface AccountService {
    List<Account> findAll();

    List<Account> findAll(Sort sort);

    List<Account> findAllById(Iterable<String> strings);

    Account getById(String s);

    Page<Account> findAll(Pageable pageable);

    <S extends Account> S save(S entity);

    Optional<Account> findById(String s);

    long count();

    void deleteById(String s);

    void delete(Account entity);

	List<Account> getAdminstrators();

	Optional<Account> findByEmail(String email);

	void register(Account account, String siteURL) throws UnsupportedEncodingException, MessagingException;

	boolean verify(String verificationCode);

    @Query("SELECT a FROM Account a WHERE a.username = ?1")
    Optional<Account> findByUsername(String username);

    void sendVerificationEmail(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException;
}
