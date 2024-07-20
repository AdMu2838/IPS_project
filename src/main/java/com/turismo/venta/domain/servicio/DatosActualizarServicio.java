package com.turismo.venta.domain.servicio;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DatosActualizarServicio(
        @NotNull
        Long id,
        String image,
        LocalDate fecha,
        BigDecimal costo
) {
}
