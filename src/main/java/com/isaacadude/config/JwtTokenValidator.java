package com.isaacadude.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(com.isaacadude.config.JwtConstant.JWT_HEADER);

        // Bearer token
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);  // Remove "Bearer " prefix

            try {
                // Decode the secret key from JwtConstant
                SecretKey key = Keys.hmacShaKeyFor(com.isaacadude.config.JwtConstant.SECRET_KEY.getBytes());
                // Parse the claims from JWT
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                // Extract the email and authorities from claims
                String email = claims.get("email", String.class);
                String authorities = claims.get("authorities", String.class);

                // Convert authorities string to GrantedAuthority list
                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                // Create an authentication token and set it in the security context
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null, auth);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                throw new BadCredentialsException("Invalid JWT token", e);
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
