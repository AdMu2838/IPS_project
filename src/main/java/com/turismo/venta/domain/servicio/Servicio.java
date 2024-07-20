package com.turismo.venta.domain.servicio;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "servicio", schema = "web_tourist_bd")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sercod", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "serimg")
    private String serImg;

    @Size(max = 255)
    @Column(name = "serdes")
    private String serDes;

    @Size(max = 50)
    @NotNull
    @Column(name = "sernom", nullable = false, length = 50)
    private String serNom;

    @NotNull
    @Column(name = "serfec", nullable = false)
    private LocalDate serFec;

    @NotNull
    @Column(name = "sercos", nullable = false, precision = 10, scale = 2)
    private BigDecimal serCos;

    @Size(max = 50)
    @NotNull
    @Column(name = "sertipo", nullable = false, length = 50)
    private String serTipo;

    @Size(max = 50)
    @NotNull
    @Column(name = "serdestino", nullable = false, length = 50)
    private String serDestino;

    @NotNull
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
        this.serDestino = datosRegistroServicio.destino();
        this.serTipo = datosRegistroServicio.tipo();
    }

    public void actualizarDatos(DatosActualizarServicio datosActualizarServicio) {
        if (datosActualizarServicio.image() != null) {
            this.serImg = datosActualizarServicio.image();
        }
        if (datosActualizarServicio.costo() != null) {
            this.serCos = datosActualizarServicio.costo();
        }
        if (datosActualizarServicio.fecha() != null) {
            this.serFec = datosActualizarServicio.fecha();
        }
    }

    public void eliminar() {
        this.serEstReg = 'I';
    }
}