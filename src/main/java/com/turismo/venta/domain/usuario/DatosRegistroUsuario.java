package com.turismo.venta.domain.usuario;

import com.turismo.venta.domain.datosUsuario.DatosRegistroDatosUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosRegistroUsuario(
        @NotBlank
        @Email
        String login,
        @NotBlank
        String clave,
        @NotBlank
        String rol,
        @NotNull
        Character estadoRegistro,
        @NotNull
        @Valid
        List<DatosRegistroDatosUsuario>datosUsuarios
) {
}
