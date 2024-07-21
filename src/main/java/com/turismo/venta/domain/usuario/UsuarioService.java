package com.turismo.venta.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        Usuario usuario = new Usuario(datosRegistroUsuario);
        String claveEncriptada = passwordEncoder.encode(datosRegistroUsuario.clave());
        usuario.setUsuPas(claveEncriptada);
        return usuarioRepository.save(usuario);
    }


    public void cambiarContrasena(Long id, String nuevaClave) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String claveEncriptada = passwordEncoder.encode(nuevaClave);
        usuario.setUsuPas(claveEncriptada);
        usuarioRepository.save(usuario);
    }
}
