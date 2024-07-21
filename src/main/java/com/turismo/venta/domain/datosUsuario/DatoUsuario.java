package com.turismo.venta.domain.datosUsuario;

import com.turismo.venta.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dato_usuario", schema = "web_tourist_bd")
public class DatoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usudatcod", nullable = false)
    private Integer id;

    @Column(name = "usunom", nullable = false, length = 50)
    private String usuNom;

    @Column(name = "usuapepa", nullable = false, length = 50)
    private String usuApePa;

    @Column(name = "usuapema", nullable = false, length = 50)
    private String usuApeMa;

    @Column(name = "usudir", length = 100)
    private String usuDir;

    @Column(name = "usufecnac", nullable = false)
    private LocalDate usuFecNac;

    @Column(name = "usudni", nullable = false, length = 20)
    private String usuDNI;

    @Column(name = "usunac", length = 50)
    private String usuNac;

    @Column(name = "usucel", length = 15)
    private String usuCel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usucod", nullable = false)
    private Usuario usuario;


    public DatoUsuario(DatosRegistroDatosUsuario datosRegistroDatosUsuario, Usuario usuario) {
        this.usuNom = datosRegistroDatosUsuario.nombre();
        this.usuApePa = datosRegistroDatosUsuario.apellidoPaterno();
        this.usuApeMa = datosRegistroDatosUsuario.apellidoMaterno();
        this.usuDir = datosRegistroDatosUsuario.direccion();
        this.usuFecNac = datosRegistroDatosUsuario.fechaNacimiento();
        this.usuDNI = datosRegistroDatosUsuario.dni();
        this.usuNac = datosRegistroDatosUsuario.nacionalidad();
        this.usuCel = datosRegistroDatosUsuario.celular();
        this.usuario = usuario;
    }

    public DatoUsuario(DatosActualizarDatosUsuario datoActualizado, Usuario usuario) {
        this.usuNom = datoActualizado.nombre();
        this.usuApePa = datoActualizado.apellidoPaterno();
        this.usuApeMa = datoActualizado.apellidoMaterno();
        this.usuDir = datoActualizado.direccion();
        this.usuFecNac = datoActualizado.fechaNacimiento();
        this.usuDNI = datoActualizado.dni();
        this.usuNac = datoActualizado.nacionalidad();
        this.usuCel = datoActualizado.celular();
        this.usuario = usuario;
    }
}