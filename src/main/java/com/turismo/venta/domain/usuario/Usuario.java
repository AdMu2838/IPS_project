package com.turismo.venta.domain.usuario;

import com.turismo.venta.domain.datosUsuario.DatoUsuario;
import com.turismo.venta.domain.datosUsuario.DatosActualizarDatosUsuario;
import com.turismo.venta.domain.datosUsuario.DatosRegistroDatosUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", schema = "web_tourist_bd")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usucod", nullable = false)
    private Long id;

    @Column(name = "usuema", nullable = false, length = 50)
    private String usuEma;

    @Column(name = "usupas", nullable = false, length = 50)
    private String usuPas;
    @Column(name = "usuestreg", nullable = false, length = 50)
    @ColumnDefault("'A'")
    private Character usuEstReg;
    @Column(name = "usurol", nullable = false, length = 50)
    private String usuRol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatoUsuario> datosUsuarios;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.usuEma = datosRegistroUsuario.login();
        this.usuPas = datosRegistroUsuario.clave();
        this.usuRol = datosRegistroUsuario.rol();
        this.usuEstReg = datosRegistroUsuario.estadoRegistro();
        this.datosUsuarios = new ArrayList<>();
        for (DatosRegistroDatosUsuario dato : datosRegistroUsuario.datosUsuarios()) {
            this.datosUsuarios.add(new DatoUsuario(dato, this));
        }
    }

    public void eliminar() {
        this.usuEstReg = 'I';
    }

    public void actualizarDatosUsuario(DatosActualizarUsuario datosActualizarUsuario) {
        this.usuRol = datosActualizarUsuario.rol();
        this.datosUsuarios = new ArrayList<>();
        for (DatosActualizarDatosUsuario datoActualizado : datosActualizarUsuario.datosUsuarios()) {
            this.datosUsuarios.add(new DatoUsuario(datoActualizado, this));
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        // Asegúrate de que el rol tenga el prefijo "ROLE_"
        roles.add(new SimpleGrantedAuthority("ROLE_" + usuRol.toUpperCase()));
        return roles;
    }

    @Override
    public String getPassword() {
        return usuPas;
    }

    @Override
    public String getUsername() {
        return usuEma;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}