package com.turismo.venta.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtils {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Puedes usar tu propia clave secreta
    private static final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos

    // Generar un token JWT
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // Validar y obtener los claims del token JWT
    public static Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // Manejar la excepción si el token es inválido
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    // Obtener el username del token JWT
    public static String getUsernameFromToken(String token) {
        Claims claims = validateToken(token);
        return claims.getSubject();
    }
}
