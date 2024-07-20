package com.turismo.venta.domain.servicio;

import java.math.BigDecimal;

public record DatosListadoServicio(
        Long id,
        String image,
        String descripcion,
        String nombre,
        BigDecimal costo,
        String destino
) {
    public DatosListadoServicio(Servicio servicio) {
        this(servicio.getId(), servicio.getSerImg(), servicio.getSerDes(), servicio.getSerNom(), servicio.getSerCos(),
                servicio.getSerDestino());
    }
}
