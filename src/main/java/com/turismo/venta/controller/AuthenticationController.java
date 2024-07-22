package com.turismo.venta.controller;

import com.turismo.venta.domain.usuario.*;
import com.turismo.venta.infra.security.DatosJWTToken;
import com.turismo.venta.infra.security.TokenService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioReposito;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<DatosRespuestaUsuario> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication autToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(autToken);
        Usuario usuario = (Usuario) usuarioAutenticado.getPrincipal();
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        String rol = usuarioAutenticado.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario, JWTtoken));
    }
    @PostMapping("/register")
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        Usuario usuarioPorRegistrar = usuarioService.registrarUsuario(datosRegistroUsuario);

//        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(usuario.getId()).toUri();
        Authentication autToken = new UsernamePasswordAuthenticationToken(datosRegistroUsuario.login(),
                datosRegistroUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(autToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        var usuarioRegistrado = (Usuario) usuarioAutenticado.getPrincipal();
//        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuarioPorRegistrar , JWTtoken);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuarioRegistrado, JWTtoken));
    }

}
