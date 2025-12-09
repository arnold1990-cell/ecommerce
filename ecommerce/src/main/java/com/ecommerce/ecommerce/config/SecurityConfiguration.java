package com.ecommerce.ecommerce.config;

import com.ecommerce.ecommerce.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import static org.apache.tomcat.util.http.Method.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
@SuppressWarnings("unused")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {"/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**"};

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // ✅ Define LogoutHandler bean
    @Bean
    public LogoutHandler logoutHandler() {
        return new SecurityContextLogoutHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers(GET, "/api/products/**").hasAnyRole("ADMIN", "SELLER", "CUSTOMER")
                        .requestMatchers(POST, "/api/products/**").hasAnyRole("ADMIN", "SELLER")
                        .requestMatchers(PUT, "/api/products/**").hasAnyRole("ADMIN", "SELLER")
                        .requestMatchers(DELETE, "/api/products/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .addLogoutHandler(logoutHandler())
                        .logoutSuccessHandler((req, res, auth) -> SecurityContextHolder.clearContext())
                );

        return http.build();
    }
}
