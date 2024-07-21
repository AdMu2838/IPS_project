package com.turismo.venta.domain.ventaDetalle;

import com.turismo.venta.domain.venta.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Long> {
    @Query("SELECT vd FROM VentaDetalle vd JOIN vd.venta v WHERE v.id = :ventaId")
    Page<VentaDetalle> findByVenta_Id(Long ventaId, Pageable pageable);
}
