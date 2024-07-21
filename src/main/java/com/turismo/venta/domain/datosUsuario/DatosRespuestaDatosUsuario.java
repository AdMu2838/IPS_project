package com.turismo.venta.domain.datosUsuario;

import java.time.LocalDate;


public record DatosRespuestaDatosUsuario(
        Integer id,
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        String direccion,
        LocalDate fechaNacimiento,
        String nacionalidad,
        String celular
) {
    public DatosRespuestaDatosUsuario(DatoUsuario datoUsuario) {
        this(
                datoUsuario.getId(),
                datoUsuario.getUsuNom(),
                datoUsuario.getUsuApePa(),
                datoUsuario.getUsuApeMa(),
                datoUsuario.getUsuDir(),
                datoUsuario.getUsuFecNac(),
                datoUsuario.getUsuNac(),
                datoUsuario.getUsuCel()
        );
    }
}
