package com.turismo.venta.domain.servicio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DatosRespuestaServicio(
        Long id,
        String image,
        String nombre,
        String descripcion,

        LocalDate fecha,
        BigDecimal costo,
        String tipo,
        String destino,
        Character estadoRegistro
) {
    public DatosRespuestaServicio(Servicio servicio) {
        this(servicio.getId(), servicio.getSerImg(),servicio.getSerNom(), servicio.getSerDes(), servicio.getSerFec(),
                servicio.getSerCos(), servicio.getSerTipo(), servicio.getSerDestino(), servicio.getSerEstReg());
    }
}
