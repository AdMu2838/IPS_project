package com.turismo.venta.service;

import com.turismo.venta.entity.User;
import com.turismo.venta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        System.out.println(user);

        if(user==null) {
            throw new UsernameNotFoundException("User not found with this email"+username);

        }

        System.out.println("Loaded user: " + user.getEmail());
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    //public List<User> getAllUser(){

    //};

    public User findUserByNombre(String nombre) {
        return userRepository.findByNombre(nombre);
    };

    public User findUserById(String userId){
        return userRepository.findById(userId).get();

    } ;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    };

    public void saveUser(User user){
        userRepository.save(user);
    };
    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    };
    public void updateUser(User user){
        userRepository.save(user);
    };
}
