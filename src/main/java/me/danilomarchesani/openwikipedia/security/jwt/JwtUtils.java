package me.danilomarchesani.openwikipedia.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import me.danilomarchesani.openwikipedia.security.service.UserDetailsImpl;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${myapp.jwtSecret}")
    private String jwtSecret = "FAJ333NcRfUjXn2r5u8xAABDTGGKaPdSgVkYp3s6v9yBBUE9H0MbQeThWmZq4t7wHzFAJ333NcRfUjXn2r5u8xAABDTGGKaPdSgVkYp3s6v9yBBUE9H0MbQeThWmZq4t7wHz";

    @Value("${myapp.jwtExpirationMs}")
    private int jwtExpirationTimeInMs;

    //private final SecretKeySpec secretKeySpec = new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS512.toString());

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();


        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationTimeInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String authToken) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(authToken)
                .getPayload()
                .getSubject();
    }

    public boolean verifyJwtToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT Token signature: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Jwt Token has expired! {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Jwt token failed to verify it: {}", e.getMessage());
        }
        return false;
    }



}
