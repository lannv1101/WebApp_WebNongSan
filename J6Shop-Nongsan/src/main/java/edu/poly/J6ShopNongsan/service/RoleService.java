package edu.poly.J6ShopNongsan.service;

import edu.poly.J6ShopNongsan.entity.Role;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();

    List<Role> findAll(Sort sort);

    List<Role> findAllById(Iterable<String> strings);

    Role getById(String s);

    <S extends Role> S save(S entity);

    Optional<Role> findById(String s);

    void deleteById(String s);

    void delete(Role entity);
}
