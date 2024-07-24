package com.turismo.venta.domain.venta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query("SELECT v FROM Venta v WHERE v.venEstReg = 'A'")
    Page<Venta> findAllActive(Pageable paginacion);

    @Query("SELECT v FROM Venta v WHERE v.venFec = :fecha")
    Page<Venta> findByFecha(@Param("fecha") LocalDate fecha, Pageable pageable);

    @Query("SELECT v FROM Venta v WHERE FUNCTION('MONTH', v.venFec) = :mes AND FUNCTION('YEAR', v.venFec) = :anio")
    Page<Venta> findByMesAndAnio(@Param("mes") int mes, @Param("anio") int anio, Pageable pageable);

    @Query("SELECT v FROM Venta v WHERE FUNCTION('YEAR', v.venFec) = :anio")
    Page<Venta> findByAnio(@Param("anio") int anio, Pageable pageable);
}
