package com.turismo.venta.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "Empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private String idEmpleado;

    @Column(name = "nombre")
    private String name;

    @Column
    private String apellido;

    @Column
    private String direccion;

    @Column
    private String dni;

    @Column
    private String fecha_nac;

    @Column
    private String nacionalidad;

    @Column
    private String celular;

    @Column
    private String email;

    @Column
    private String cargo;

    @Column
    private Double sueldo;
}
