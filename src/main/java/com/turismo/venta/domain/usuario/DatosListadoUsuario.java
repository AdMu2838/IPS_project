package com.turismo.venta.domain.usuario;

import com.turismo.venta.domain.datosUsuario.DatosListadoDatosUsuario;
import com.turismo.venta.domain.datosUsuario.DatosRespuestaDatosUsuario;

import java.util.List;
import java.util.stream.Collectors;

public record DatosListadoUsuario(
        Long id,
        String login,
        Character estadoRegistro,
        String rol,
        List<DatosListadoDatosUsuario> datosUsuarios
) {
    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getUsuEma(),usuario.getUsuEstReg(), usuario.getUsuRol(),
                usuario.getDatosUsuarios().stream()
                        .map(DatosListadoDatosUsuario::new)
                        .collect(Collectors.toList()));

    }
}
