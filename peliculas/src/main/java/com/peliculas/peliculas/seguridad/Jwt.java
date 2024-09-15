package com.peliculas.peliculas.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class Jwt {

    @Value("${jwt.secret}")
    private String secreto;

    @Value("${jwt.expiration}")
    private long tiempoExpiracion;

    // Extraer el nombre usuario del token
    public String extraerEmailUsuario(String token){
        return extraerClaim(token, Claims::getSubject);
    }

    // Extraer cualquier claim del token
    public <T> T extraerClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extraerTodasLasClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extraerTodasLasClaims(String token) {
        return Jwts.parser()
                .verifyWith(obtenerClaveDeLogin()) // Usar la misma clave que en la creación
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    // Verificar si el token ha expirado
    private Boolean tokenExpirado(String token){
        return extractoCaducidad(token).before(new Date());
    }

    public Date extractoCaducidad(String token){
        return extraerClaim(token, Claims::getExpiration);
    }

    //Generar el token
    public String generarToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return crearToken(claims, username);
    }

    private SecretKey obtenerClaveDeLogin(){
        byte [] keyBytes = Decoders.BASE64.decode(secreto);
        return  Keys.hmacShaKeyFor(keyBytes);
    }


    private String crearToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tiempoExpiracion))
                .signWith(obtenerClaveDeLogin(), Jwts.SIG.HS256)
                .compact();
    }

    //Validar el token
    public Boolean validarToken(String token, String email){
        final String extraerEmailUsuario = extraerEmailUsuario(token);
        return (extraerEmailUsuario.equals(email) && !tokenExpirado(token));
    }
}
