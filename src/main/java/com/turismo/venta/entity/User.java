package com.turismo.venta.entity;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "Personas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

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
    private String rol;
    @Column
    private Double sueldo;
    @Column(name = "email", unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "pass")
    private String password;

}