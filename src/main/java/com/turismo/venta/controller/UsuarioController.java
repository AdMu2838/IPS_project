package com.turismo.venta.controller;


import com.turismo.venta.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public ResponseEntity<DatosRespuestaUsuario> login(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                                       UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioService.registrarUsuario(datosRegistroUsuario);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario);
        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuarios(@PageableDefault(size = 2) Pageable paginacion) {
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
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarDatosUsuario(datosActualizarUsuario);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/cambiar-contrasena")
    public ResponseEntity<Void> cambiarContrasena(@RequestBody @Valid DatosActualizarContrasena datosActualizarContrasena) {
        usuarioService.cambiarContrasena(datosActualizarContrasena.id(), datosActualizarContrasena.nuevaClave());
        return ResponseEntity.ok().build();
    }


}
