package com.turismo.venta.domain.servicio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "servicio", schema = "web_tourist_bd")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serCod", nullable = false)
    private Integer id;

    @Column(name = "serimg")
    private String serImg;

    @Column(name = "serdes")
    private String serDes;

    @Column(name = "sernom", nullable = false, length = 50)
    private String serNom;

    @Column(name = "serfec", nullable = false)
    private LocalDate serFec;

    @Column(name = "sercos", nullable = false, precision = 10, scale = 2)
    private BigDecimal serCos;

    @ColumnDefault("'A'")
    @Column(name = "serestreg", nullable = false)
    private Character serEstReg;

    public Servicio(DatosRegistroServicio datosRegistroServicio) {
        this.serImg = datosRegistroServicio.image();
        this.serDes = datosRegistroServicio.descripcion();
        this.serNom = datosRegistroServicio.nombre();
        this.serCos = datosRegistroServicio.costo();
        this.serEstReg = datosRegistroServicio.estadoRegistro();
        this.serFec = datosRegistroServicio.fecha();

    }
}