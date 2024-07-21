package com.turismo.venta.domain.usuario;

import com.turismo.venta.domain.datosUsuario.DatosActualizarDatosUsuario;
import com.turismo.venta.domain.datosUsuario.DatosListadoDatosUsuario;
import com.turismo.venta.domain.datosUsuario.DatosRespuestaDatosUsuario;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public record DatosActualizarUsuario(
        String login,
        String rol,
        List<DatosActualizarDatosUsuario> datosUsuarios
) {
    public DatosActualizarUsuario(Usuario usuario) {
        this( usuario.getUsuEma(), usuario.getUsuRol(),
                usuario.getDatosUsuarios().stream()
                        .map(DatosActualizarDatosUsuario::new)
                        .collect(Collectors.toList()));

    }
}
