package com.springestoque.springestoque_backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "sua_chave_secreta_suficientemente_longa_para_256bits";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    // Gera um token JWT
    public String gerarToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 1 dia de expiração
                .sign(ALGORITHM);
    }

    // Valida o token e retorna true se estiver válido
    public boolean validarToken(String token, String username) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withSubject(username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    // Extrai o username do token
    public String extrairUsername(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }

    // Verifica se o token está expirado
    public boolean tokenExpirado(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt().before(new Date());
    }
}
