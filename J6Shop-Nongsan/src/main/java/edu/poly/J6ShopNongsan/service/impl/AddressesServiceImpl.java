package edu.poly.J6ShopNongsan.service.impl;

import edu.poly.J6ShopNongsan.entity.Addresses;
import edu.poly.J6ShopNongsan.repository.AccountRepository;
import edu.poly.J6ShopNongsan.repository.AddressesRepository;
import edu.poly.J6ShopNongsan.service.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressesServiceImpl implements AddressesService {

    @Autowired
    private AddressesRepository addressesRepository;

    @Override
    public Addresses deleteAddressesByIdAndAccount_Username(Integer id, String account_username) {
        return addressesRepository.deleteAddressesByIdAndAccount_Username(id, account_username);
    }

    @Override
    public List<Addresses> findAddressesByAccount_Username(String username) {
        return addressesRepository.findAddressesByAccount_Username(username);
    }

    @Override
    @Query("SELECT  a from Addresses a WHERE a.id =?1 and  a.account.username = ?2")
    public Addresses findByIdAndAccount(Integer addressesId, String username) {
        return addressesRepository.findByIdAndAccount(addressesId, username);
    }

    @Override
    @Query("delete from Addresses a where a.id=?1 and a.account.username =?2")
    public void deleteByIdAndAccount(Integer addressesId, String username) {
        addressesRepository.deleteByIdAndAccount(addressesId, username);
    }

    @Override
    public List<Addresses> findAll() {
        return addressesRepository.findAll();
    }

    @Override
    public Addresses getById(Integer integer) {
        return addressesRepository.getById(integer);
    }

    @Override
    public <S extends Addresses> S save(S entity) {
        return addressesRepository.save(entity);
    }

    @Override
    public Optional<Addresses> findById(Integer integer) {
        return addressesRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        addressesRepository.deleteById(integer);
    }

    @Override
    public void delete(Addresses entity) {
        addressesRepository.delete(entity);
    }
}
