package com.turismo.venta.domain.venta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query("SELECT v FROM Venta v WHERE v.venEstReg = 'A'")
    Page<Venta> findAllActive(Pageable paginacion);
}
