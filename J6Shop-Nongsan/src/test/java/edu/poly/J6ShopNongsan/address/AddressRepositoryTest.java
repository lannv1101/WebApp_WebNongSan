package edu.poly.J6ShopNongsan.address;

import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.entity.Addresses;
import edu.poly.J6ShopNongsan.repository.AddressesRepository;
import edu.poly.J6ShopNongsan.service.AddressesService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryTest {
    @Autowired
    private AddressesRepository addressesRepository;

    @Test
    @Rollback(value = false)
    public void TestAddressNew(){
        String username = "lanadmin";
        Account account = new Account();
        account.setUsername(username);
        Addresses addresses = new Addresses();
        addresses.setAccount(account);
        addresses.setFirst_name("Lan Vu");
        addresses.setLast_name("Nguyen");
        addresses.setAddress_line1("08 Hà Văn Tính");
        addresses.setAddress_line2("08 Hà Văn Tính3");
        addresses.setCity("Đà Nẵng");
        addresses.setState("Hòa khánh");
        addresses.setPostal_code("05555");
        addresses.setPhone_number("032144455");
        addresses.setAddress_default(false);

        Addresses saveAddress = addressesRepository.save(addresses);

        Assertions.assertThat(saveAddress).isNotNull();
        Assertions.assertThat(saveAddress.getId()).isGreaterThan(0);

    }
    @Test
    @Rollback(value = false)
    public void TestUpdateAddress(){
        Integer addressId=12;
        Addresses addresses = addressesRepository.findById(addressId).get();
        addresses.setAddress_default(false);

        Addresses addressesTestUpdate = addressesRepository.save(addresses);
    }

//    @Test
//    @Rollback(value = false)
//    public void TestDeleteAddress(){
//        Integer id=7;
//        String username ="lanadmin";
//
//        Addresses addressesTestDelete = addressesRepository.deleteById(id);
//    }
}
