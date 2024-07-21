package com.turismo.venta.domain.datosUsuario;


public record DatosListadoDatosUsuario(
        Integer id,
        String nombre,
        String apellidoPaterno,
        String celular
) {
    public DatosListadoDatosUsuario(DatoUsuario datoUsuario) {
        this(
                datoUsuario.getId(),
                datoUsuario.getUsuNom(),
                datoUsuario.getUsuApePa(),
                datoUsuario.getUsuCel()
        );
    }
}
