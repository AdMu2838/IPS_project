package com.turismo.venta.domain.servicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{
    @Query("SELECT s FROM Servicio s WHERE s.serEstReg = 'A'")
    Page<Servicio> findAllActive(Pageable paginacion);

    @Query("SELECT s FROM Servicio s WHERE s.serEstReg = 'A' and s.serDestino = :destino")
    Page<Servicio> findServicioDestino(String destino, Pageable paginacion);

    @Query("SELECT s FROM Servicio s WHERE s.serFec BETWEEN :startDate AND :endDate")
    Page<Servicio> findServiciosByFechaBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                                Pageable paginacion);
    @Query("SELECT s FROM Servicio s WHERE s.serEstReg = 'A' and s.serTipo = :tipo")
    Page<Servicio> findByServicioTipo(String tipo, Pageable paginacion);

    @Query("SELECT s FROM Servicio s JOIN s.paquetes p WHERE p.id = :paqCod")
    Page<Servicio> findByPaquetes_PaqCod(Long paqCod, Pageable pageable);

    Page<Servicio> findBySerNomContainingIgnoreCase(String nombre, Pageable paginacion);
}
