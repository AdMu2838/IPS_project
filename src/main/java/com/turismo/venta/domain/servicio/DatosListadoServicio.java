package com.turismo.venta.domain.servicio;

public record DatosListadoServicio(
        String image,
        String descripcion,
        String nombre,
        Double costo,
        char serEst
) {
}
