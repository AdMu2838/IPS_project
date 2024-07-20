package com.turismo.venta.domain.datosUsuario;

import com.turismo.venta.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "dato_usuario", schema = "web_tourist_bd")
public class DatoUsuario {
    @Id
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
    private Usuario usuCod;

}