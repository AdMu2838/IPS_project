package com.turismo.venta.controller;


import com.turismo.venta.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/active")
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuariosActivos(@PageableDefault(size = 7) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAllActive(paginacion).map(DatosListadoUsuario::new));
    }

    @GetMapping("/inactive")
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuariosInactivos(@PageableDefault(size = 7) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAllInactive(paginacion).map(DatosListadoUsuario::new));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<DatosListadoUsuario>> listarTodosLosUsuarios(@PageableDefault(size = 7) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new));
    }

    @GetMapping("/admins")
    public ResponseEntity<Page<DatosListadoUsuario>> listarAdmins(@PageableDefault(size = 7) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAllAdmins(paginacion).map(DatosListadoUsuario::new));
    }
    @GetMapping("/normales")
    public ResponseEntity<Page<DatosListadoUsuario>> listarNormales(@PageableDefault(size = 7) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAllUsers(paginacion).map(DatosListadoUsuario::new));
    }

    @DeleteMapping("/inactivar/{id}")
    @Transactional
    public ResponseEntity<Void> inactivarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.inactivar();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/activar/{id}")
    @Transactional
    public ResponseEntity<Void> activarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.activar();
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
    @PutMapping("/{id}/rol")
    public ResponseEntity<Void> actualizarRolUsuario(@PathVariable Long id, @RequestParam String nuevoRol) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setUsuRol(nuevoRol);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }


}
