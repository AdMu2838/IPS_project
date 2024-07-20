package com.turismo.venta.domain.servicio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record DatosRegistroServicio(
        @NotBlank
        String image,
        @NotBlank
        String descripcion,
        @NotBlank
        String nombre,
        @NotNull
        LocalDate fecha,
        @NotBlank
        BigDecimal costo,
        @NotBlank
        Character estadoRegistro
) {

}
