package com.isaacadude.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.security.Security;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration // Marks this class as a configuration class in Spring, meaning it contains beans and settings
// for Spring’s application context.
@EnableWebSecurity // Activates Spring Security’s web security features for this application,
// enabling security filters and providing customization points.
public class AppConfig {

    @Bean // allows Spring to manage it and use it to configure the security of HTTP requests.
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sets the application to be stateless,
                // meaning it does not keep user sessions on the server.
                // This authorizeHttpRequests(...) defines rules for authorizing requests to specific URL patterns.
                .authorizeHttpRequests(Authorize -> Authorize
                        .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNR", "ADMIN")
                        .requestMatchers("/api/**").authenticated() // Any request to URLs starting with /api/ requires the user to be authenticated.
                        .anyRequest().permitAll()
                ).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf->csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList(
                        "https://isaac-food.vercel.app/",
                        "http://localhost:3000/"
                ));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList("Authorization"));
                config.setMaxAge(3600L);
                return config;
            }
        };
    }
    /**
     * BCryptPasswordEncoder makes it easy to securely hash and validate passwords in a Spring application,
     * following best practices for password management.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
