package com.turismo.venta.domain.paquete;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosActualizarPaquete(
        @NotNull
        Long id,
        String paqImg,
        String paqNom,

        List<Long> serviciosCodigos
) {
}
