package edu.poly.J6ShopNongsan.service.impl;

import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.repository.AccountRepository;
import edu.poly.J6ShopNongsan.service.AccountService;
import net.bytebuddy.utility.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
			private JavaMailSender mailSender;

	@Override
	@Query("SELECT a FROM Account a WHERE a.username = ?1")
	public Optional<Account> findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public void sendVerificationEmail(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException {

		String toAddress = account.getEmail();
		String fromAddress = "Your email address";
		String senderName = "Your company name";
		String subject = "Please verify your registration";
		String content = "Dear [[name]],<br>"
				+ "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
				+ "Thank you,<br>"
				+ "Lanmarket | Shop.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("lannvpd03981@fpt.edu.vn", "Lanmarket");
		helper.setTo(account.getEmail());
		helper.setSubject("Verify Email login to Lanmarket");

		content = content.replace("[[name]]", account.getFirst_name() );
		String verifyURL = siteURL + "/lanmarket/login/verify?code=" + account.getVerification_code();

		content = content.replace("[[URL]]", verifyURL);

		helper.setText(content, true);

		mailSender.send(message);
	}
    
    @Override
	public boolean verify(String verificationCode) {
		Account account = accountRepository.findByVerificationCode(verificationCode);

		if (account == null || account.getEnable()) {
			return false;
		} else {
			account.setVerification_code(null);
			account.setEnable(true);
			accountRepository.save(account);

			return true;
		}

	}
	@Override
	public void register(Account account, String siteURL) throws UnsupportedEncodingException, MessagingException {
//		String encodedPass = bCryptPasswordEncoder.encode(account.getPassword());
//		account.setPassword(encodedPass);
		String randomcode = RandomString.make(64);
		account.setVerification_code(randomcode);
		account.setEnable(false);


		accountRepository.save(account);
		sendVerificationEmail(account,siteURL);
		
	}
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    @Override
    public List<Account> findAllById(Iterable<String> strings) {
        return accountRepository.findAllById(strings);
    }

    @Override
    public Account getById(String s) {
        return accountRepository.getById(s);
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public <S extends Account> S save(S entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Optional<Account> findById(String s) {
        return accountRepository.findById(s);
    }

    @Override
    public long count() {
        return accountRepository.count();
    }

    @Override
    public void deleteById(String s) {
        accountRepository.deleteById(s);
    }

   

    @Override
    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

	@Override
	public List<Account> getAdminstrators() {
		// TODO Auto-generated method stub
		return accountRepository.getAdminstrators();
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		// TODO Auto-generated method stub
		return accountRepository.findByEmail(email);
	}
}
