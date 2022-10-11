package com.vtb.payandsave.jwt;

import com.vtb.payandsave.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    @Value("${jwt.jwtSecret}")
    private String jwtSecret;

    public String generateJwtToken(Authentication authentication) {
        Account userPrincipal = (Account) authentication.getPrincipal();
        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public boolean validateJwtToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }
}
