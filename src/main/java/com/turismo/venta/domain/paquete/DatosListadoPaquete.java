package com.turismo.venta.domain.paquete;

public record DatosListadoPaquete(
        Long id,
        String nombre,
        String imagen,
        Character estadoRegistro
) {
    public DatosListadoPaquete(Paquete paquete) {
        this(
                paquete.getId(),
                paquete.getPaqNom(),
                paquete.getPaqImg(),
                paquete.getPaqEstReg()
        );
    }
}
