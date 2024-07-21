package com.turismo.venta.domain.usuario;
import com.turismo.venta.domain.datosUsuario.DatosRespuestaDatosUsuario;

import java.util.List;
import java.util.stream.Collectors;

public record DatosRespuestaUsuario(
        Long id,
        String login,
        List<DatosRespuestaDatosUsuario> datosUsuarios
) {
    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getUsuEma(),
                usuario.getDatosUsuarios().stream()
                .map(DatosRespuestaDatosUsuario::new)
                .collect(Collectors.toList()));

    }
}
