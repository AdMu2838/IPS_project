package com.turismo.venta.controller;


import com.turismo.venta.domain.usuario.*;
import com.turismo.venta.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuarios(@PageableDefault(size = 15) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAllActive(paginacion).map(DatosListadoUsuario::new));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarUsuario(@RequestParam("id") Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        usuario.eliminar();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/modificarDatos")
    @Transactional
    public ResponseEntity actualizarDatosUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

        if (!usuarioAutenticado.getId().equals(usuarioAutenticado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // O lanzar una excepción adecuada
        }
        Usuario usuario = usuarioRepository.getReferenceById(usuarioAutenticado.getId());
        usuario.actualizarDatosUsuario(datosActualizarUsuario);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/cambiar-contrasena")
    @Transactional
    public ResponseEntity<Void> cambiarContrasena(@RequestBody @Valid DatosActualizarContrasena datosActualizarContrasena) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

        if (!usuarioAutenticado.getId().equals(usuarioAutenticado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        usuarioService.cambiarContrasena(usuarioAutenticado.getId(), datosActualizarContrasena.nuevaClave());
        return ResponseEntity.ok().build();
    }


}
