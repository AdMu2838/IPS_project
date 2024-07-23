package com.turismo.venta.domain.paquete;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record PaqueteServicio(

        @NotBlank
        Character paqEstReg,
        @NotBlank
        String paqImg,
        String nombre,
        @NotNull
        List<Long> serviciosCodigos
) {
}
