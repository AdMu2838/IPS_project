package com.turismo.venta.domain.servicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServicioRepository extends JpaRepository<Servicio, Long>{
    @Query("SELECT s FROM Servicio s WHERE s.serEstReg = 'A'")
    Page<Servicio> findAllActive(Pageable paginacion);
}
