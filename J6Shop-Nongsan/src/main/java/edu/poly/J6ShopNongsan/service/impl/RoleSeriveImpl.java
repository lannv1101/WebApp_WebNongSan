package edu.poly.J6ShopNongsan.service.impl;

import edu.poly.J6ShopNongsan.entity.Role;
import edu.poly.J6ShopNongsan.repository.RoleRepository;
import edu.poly.J6ShopNongsan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleSeriveImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAll(Sort sort) {
        return roleRepository.findAll(sort);
    }

    @Override
    public List<Role> findAllById(Iterable<String> strings) {
        return roleRepository.findAllById(strings);
    }

    @Override
    public Role getById(String s) {
        return roleRepository.getById(s);
    }

    @Override
    public <S extends Role> S save(S entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Optional<Role> findById(String s) {
        return roleRepository.findById(s);
    }

    @Override
    public void deleteById(String s) {
        roleRepository.deleteById(s);
    }

    @Override
    public void delete(Role entity) {
        roleRepository.delete(entity);
    }
}
