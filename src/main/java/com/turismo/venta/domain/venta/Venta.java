package com.turismo.venta.domain.venta;

import com.turismo.venta.domain.usuario.Usuario;
import com.turismo.venta.domain.empleado.Empleado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "venta", schema = "web_tourist_bd")
public class Venta {
    @Id
    @Column(name = "venNum", nullable = false)
    private Integer id;

    @Column(name = "venFec", nullable = false)
    private LocalDate venFec;

    @Column(name = "venMedPag", nullable = false, length = 50)
    private String venMedPag;

    @ColumnDefault("'A'")
    @Column(name = "venEstReg", nullable = false)
    private Character venEstReg;

    @Column(name = "venMon", nullable = false, precision = 10, scale = 2)
    private BigDecimal venMon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empCod")
    private Empleado empCod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuCod")
    private Usuario usuCod;

}