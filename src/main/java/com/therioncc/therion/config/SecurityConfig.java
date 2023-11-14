package com.therioncc.therion.config;

import com.therioncc.therion.jwt.JwtAthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.spi.SessionEventListenerManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAthenticationFilter jwtAthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                    .csrf(csrf ->
                            csrf
                                    .disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/cliente/**").permitAll()
                                .requestMatchers("/auth/duenno/**").permitAll()
                                .anyRequest().authenticated()

                        )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
