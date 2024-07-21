package com.turismo.venta.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.turismo.venta.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            Instant expiracion = generarFechaExpiracion();
            System.out.println("Fecha de expiracion al generar el token" + expiracion);
            return JWT.create()
                    .withIssuer("turismo venta")
                    .withSubject(usuario.getUsuEma())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(expiracion)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token is null");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("turismo venta")
                    .build()
                    .verify(token);
            return verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println("JWTVerificationException: " + exception.toString());
            throw new RuntimeException("Token verification failed: " + exception.getMessage());
        } catch (Exception e) {
            System.out.println("General exception: " + e.toString());
            throw new RuntimeException("An error occurred during token verification: " + e.getMessage());
        }
    }


    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
    // Metodo de antes :v
//    public String getSubject(String token) {
//        if (token == null) {
//            throw new RuntimeException("Token esta nulo :C");
//        }
//        DecodedJWT verifier = null;
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
//            verifier = JWT.require(algorithm)
//                    .withIssuer("turismo venta")
//                    .build()
//                    .verify(token);
//            verifier.getSubject();
//
//        } catch (JWTVerificationException exception) {
//            System.out.println(exception.toString());
//        }
//        if (verifier.getSubject() == null) {
//            throw new RuntimeException("Verifier invalido");
//        }
//        return verifier.getSubject();
//    }



}