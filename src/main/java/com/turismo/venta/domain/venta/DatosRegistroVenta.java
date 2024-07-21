package com.turismo.venta.domain.venta;

import java.time.LocalDate;
import java.util.List;

public record DatosRegistroVenta(
        LocalDate fecha,
        String medioPago,
        Character estadoRegistro,
        Long usuarioCodigo,
        List<Long> detalles
) {

}
