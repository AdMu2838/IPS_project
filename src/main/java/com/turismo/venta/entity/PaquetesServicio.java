package com.turismo.venta.entity;

import com.turismo.venta.domain.paquete.Paquete;
import com.turismo.venta.domain.servicio.Servicio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "paquetes_servicios", schema = "web_tourist_bd")
public class PaquetesServicio {
    @EmbeddedId
    private PaquetesServicioId id;

    @MapsId("serCod")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serCod", nullable = false)
    private Servicio serCod;

    @MapsId("paqCod")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paqCod", nullable = false)
    private Paquete paqCod;

}