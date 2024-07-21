package com.turismo.venta.domain.usuario;

import com.turismo.venta.domain.datosUsuario.DatosActualizarDatosUsuario;
import com.turismo.venta.domain.datosUsuario.DatosListadoDatosUsuario;
import com.turismo.venta.domain.datosUsuario.DatosRespuestaDatosUsuario;

import java.util.List;
import java.util.stream.Collectors;

public record DatosActualizarUsuario(
        Long id,
        String login,
        String rol,
        List<DatosActualizarDatosUsuario> datosUsuarios
) {
    public DatosActualizarUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getUsuEma(), usuario.getUsuRol(),
                usuario.getDatosUsuarios().stream()
                        .map(DatosActualizarDatosUsuario::new)
                        .collect(Collectors.toList()));

    }
}
