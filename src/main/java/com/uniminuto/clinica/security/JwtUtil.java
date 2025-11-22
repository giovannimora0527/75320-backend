package com.uniminuto.clinica.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.uniminuto.clinica.entity.Usuario;
import com.auth0.jwt.JWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // ============================
    // GENERAR TOKEN
    // ============================
    public String generateToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());

        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + jwtExpiration);

        return JWT.create()
                .withSubject(usuario.getEmail())
                .withClaim("rol", usuario.getRol())
                .withIssuedAt(ahora)
                .withExpiresAt(expiracion)   // CREA EL CLAIM "exp"
                .sign(algorithm);
    }

    // ============================
    // EXTRAER USERNAME (email)
    // ============================
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ============================
    // OBTENER EXPIRACIÓN
    // ============================
    public Date getExpirationDateFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt();  // ← ESTA ES LA CORRECTA
    }

    // ============================
    // VALIDAR TOKEN
    // ============================
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
