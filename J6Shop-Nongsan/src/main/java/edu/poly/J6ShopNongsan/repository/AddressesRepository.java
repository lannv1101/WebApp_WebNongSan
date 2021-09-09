package edu.poly.J6ShopNongsan.repository;

import edu.poly.J6ShopNongsan.entity.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressesRepository  extends JpaRepository<Addresses,Integer> {

    List<Addresses> findAddressesByAccount_Username(String username);

    @Query("SELECT  a from Addresses a WHERE a.id =?1 and  a.account.username = ?2")
    public Addresses findByIdAndAccount (Integer addressesId, String username);

    @Query("Delete from Addresses a where a.id=?1 and a.account.username =?2")
    public void  deleteByIdAndAccount(Integer addressesId,String username);

    Addresses  deleteAddressesByIdAndAccount_Username(Integer id, String account_username);

    @Query("SELECT  a from Addresses a WHERE a.address_default=true and a.account.username = ?1")
    public Optional<Addresses>  findAddressDefault(String username);

}
