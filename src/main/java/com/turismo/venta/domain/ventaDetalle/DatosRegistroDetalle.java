package com.turismo.venta.domain.ventaDetalle;

import com.turismo.venta.domain.paquete.DatosRegistroPaquete;
import com.turismo.venta.domain.servicio.DatosRegistroServicio;
import com.turismo.venta.domain.venta.DatosRegistroVenta;

import java.math.BigDecimal;

public record DatosRegistroDetalle(
        BigDecimal subtotal,
        Integer cantidad,
        Long servicioCodigo,
        Long paqueteCodigo
) {
}