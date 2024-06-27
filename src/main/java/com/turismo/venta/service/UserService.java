package com.turismo.venta.service;


import com.turismo.venta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserService{
    public List<User> getAllUser()  ;

    public User findUserProfileByJwt(String jwt);

    public User findUserByEmail(String email) ;

    public User findUserById(String userId) ;

    public List<User> findAllUsers();

    public void saveUser(User user);
    public void deleteUser(String userId);
    public void updateUser(User user);


}