package com.turismo.venta.domain.usuario;
import com.turismo.venta.domain.datosUsuario.DatosRespuestaDatosUsuario;

import java.util.List;
import java.util.stream.Collectors;

public record DatosRespuestaUsuario(
        Long id,
        String login,
        List<DatosRespuestaDatosUsuario> datosUsuarios,
        String token
) {
    public DatosRespuestaUsuario(Usuario usuario, String token) {
        this(usuario.getId(), usuario.getUsuEma(),
                usuario.getDatosUsuarios().stream()
                .map(DatosRespuestaDatosUsuario::new)
                .collect(Collectors.toList()), token);

    }
}
