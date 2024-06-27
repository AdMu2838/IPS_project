package com.turismo.venta.repository;

import com.turismo.venta.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

    User findByEmail(String email);

    List<User> findAll();
    User findByNombre(String nombre);
}