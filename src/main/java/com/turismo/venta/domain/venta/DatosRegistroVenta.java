package com.turismo.venta.domain.venta;

import com.turismo.venta.domain.usuario.DatosRegistroUsuario;
import com.turismo.venta.domain.ventaDetalle.DatosRegistroDetalle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DatosRegistroVenta(
        LocalDate fecha,
        String medioPago,
        Character estadoRegistro,
        Long usuarioCodigo,
        List<DatosRegistroDetalle> detalles
) {

}
