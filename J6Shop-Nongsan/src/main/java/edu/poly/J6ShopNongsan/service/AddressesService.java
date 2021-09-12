package edu.poly.J6ShopNongsan.service;

import edu.poly.J6ShopNongsan.entity.Addresses;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressesService {


    @Query("SELECT  a from Addresses a WHERE a.address_default=true and a.account.username = ?1")
    Optional<Addresses> findAddressDefault(String username);

    Addresses deleteAddressesByIdAndAccount_Username(Integer id, String account_username);

    List<Addresses> findAddressesByAccount_Username(String username);

    @Query("SELECT  a from Addresses a WHERE a.id =?1 and  a.account.username = ?2")
    Addresses findByIdAndAccount(Integer addressesId, String username);

    @Query("delete from Addresses a where a.id=?1 and a.account.username =?2")
    void deleteByIdAndAccount(Integer addressesId, String username);

    List<Addresses> findAll();

    Addresses getById(Integer integer);

    <S extends Addresses> S save(S entity);

    Optional<Addresses> findById(Integer integer);

    void deleteById(Integer integer);

    void delete(Addresses entity);

	void setDefaultAddress(Integer addressdefaultId, String username);
}
