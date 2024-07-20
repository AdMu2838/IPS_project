package com.turismo.venta.domain.servicio;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DatosListadoServicio(
        Long id,
        String image,
        String descripcion,
        String nombre,
        BigDecimal costo,
        String destino,
        LocalDate fecha
) {
    public DatosListadoServicio(Servicio servicio) {
        this(servicio.getId(), servicio.getSerImg(), servicio.getSerDes(), servicio.getSerNom(), servicio.getSerCos(),
                servicio.getSerDestino(), servicio.getSerFec());
    }
}
