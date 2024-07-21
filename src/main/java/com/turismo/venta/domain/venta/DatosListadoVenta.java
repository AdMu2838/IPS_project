package com.turismo.venta.domain.venta;

import com.turismo.venta.domain.ventaDetalle.VentaDetalle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DatosListadoVenta(
        Long id,
        LocalDate fecha,
        Character estado,
        BigDecimal total,
        List<VentaDetalle> detalles

) {
    public DatosListadoVenta(Venta venta) {
        this(
                venta.getId(),
                venta.getVenFec(),
                venta.getVenEstReg(),
                venta.getVenMon(),
                venta.getDetalles()
        );
    }
}
