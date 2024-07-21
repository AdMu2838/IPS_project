package com.turismo.venta.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarContrasena(
        String nuevaClave
) {
}
