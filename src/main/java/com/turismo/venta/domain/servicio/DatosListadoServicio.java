package com.turismo.venta.domain.servicio;

public record DatosListadoServicio(
        Long id,
        String image,
        String descripcion,
        String nombre,
        Double costo,
        String destino
) {
}
