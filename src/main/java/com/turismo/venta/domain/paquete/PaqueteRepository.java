package com.turismo.venta.domain.paquete;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> {
    @Query("SELECT p FROM Paquete p WHERE p.paqEstReg = 'A'")
    Page<Paquete> findActivePackages(Pageable pagination);
}
