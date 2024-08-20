package com.example.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.Map;


@Service
public class JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_MIN_MINUTES;
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY ;


    public String generateToken (UserDetails userDetails,  Map<String, Object> extraClaims){

        Date issuedAt= new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_MIN_MINUTES * 60 * 1000)+issuedAt.getTime());
        return Jwts.builder()
                .header()
                   .type("JWT")
                .and()
                .subject(userDetails.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(extraClaims)
                .signWith(generateKey(),Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername (String jwt){
        return extractAllClaims(jwt).getSubject();

    }
    public String extractJwtFromRequest (HttpServletRequest request){

        String authorizationHeader = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorizationHeader)|| !authorizationHeader.startsWith("Bearer ")){
            return null;
        }

        return  authorizationHeader.split(" ")[1];
    }
    private SecretKey generateKey (){
        byte[] key=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser().verifyWith(generateKey()).build()
                .parseSignedClaims(jwt).getPayload();
    }

    public Date extractExpiration(String jwt) {
        return extractAllClaims(jwt).getExpiration();
    }
}
