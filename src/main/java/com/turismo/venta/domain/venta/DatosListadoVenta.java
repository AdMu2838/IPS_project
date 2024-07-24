package com.turismo.venta.domain.venta;

import com.turismo.venta.domain.ventaDetalle.VentaDetalle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DatosListadoVenta(
        Long id,
        LocalDate fecha,
        String medioPago,
        Character estadoRegistro,
        BigDecimal montoTotal,
        String nombreUsuario

) {
    public DatosListadoVenta(Venta venta) {
        this(
                venta.getId(),
                venta.getVenFec(),
                venta.getVenMedPag(),
                venta.getVenEstReg(),
                venta.getVenMon(),
                venta.getUsuCod().getUsuEma()

        );
    }
}
