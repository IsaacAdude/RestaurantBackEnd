package com.isaacadude.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;

@Service
public class JwtProvider {

    private SecretKey key = Keys.hmacShaKeyFor(com.isaacadude.config.JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth) {
        Collection <? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder().setIssuedAt(new Date())
                .setExpiration((new Date(new Date().getTime()+86400000)))
                .claim("email",auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();

        return jwt;
    }

    public String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        // Extract the email and authorities from claims
        String email = claims.get("email", String.class);

        return email;
    }

    private String populateAuthorities(Collection <? extends GrantedAuthority> authorities) {
        Set<String> auth = new HashSet<>();

        for(GrantedAuthority authority:authorities){
            auth.add(authority.getAuthority());
        }
        return String.join(",",auth);
    }

}
