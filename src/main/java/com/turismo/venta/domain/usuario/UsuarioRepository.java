package com.turismo.venta.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    UserDetails findByUsuEma(String username);

    @Query("SELECT u FROM Usuario u WHERE u.usuEstReg = 'A'")
    Page<Usuario> findAllActive(Pageable paginacion);

    @Query("SELECT u FROM Usuario u WHERE u.usuEstReg = 'I'")
    Page<Usuario> findAllInactive(Pageable paginacion);

    @Query("SELECT u FROM Usuario u WHERE u.usuRol = 'ADMIN'")
    Page<Usuario> findAllAdmins(Pageable paginacion);

    @Query("SELECT u FROM Usuario u WHERE u.usuRol = 'USER'")
    Page<Usuario> findAllUsers(Pageable paginacion);
}
