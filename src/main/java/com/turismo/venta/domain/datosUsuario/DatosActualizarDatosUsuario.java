package com.turismo.venta.domain.datosUsuario;

import java.time.LocalDate;

public record DatosActualizarDatosUsuario(
        Integer id,
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        String direccion,
        LocalDate fechaNacimiento,
        String dni,
        String nacionalidad,
        String celular
) {
    public DatosActualizarDatosUsuario(DatoUsuario datoUsuario) {
        this(
                datoUsuario.getId(),
                datoUsuario.getUsuNom(),
                datoUsuario.getUsuApePa(),
                datoUsuario.getUsuApeMa(),
                datoUsuario.getUsuDir(),
                datoUsuario.getUsuFecNac(),
                datoUsuario.getUsuDNI(),
                datoUsuario.getUsuNac(),
                datoUsuario.getUsuCel()
        );
    }
}
