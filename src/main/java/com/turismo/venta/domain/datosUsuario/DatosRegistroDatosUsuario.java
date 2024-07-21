package com.turismo.venta.domain.datosUsuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DatosRegistroDatosUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        String apellidoPaterno,
        @NotBlank
        String apellidoMaterno,
        @NotBlank
        String direccion,
        @NotNull
        LocalDate fechaNacimiento,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String dni,
        @NotBlank
        String nacionalidad,
        @NotBlank
        String celular
) {
}
