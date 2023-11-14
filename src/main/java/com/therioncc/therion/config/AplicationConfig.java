package com.therioncc.therion.config;

import com.therioncc.therion.models.Cliente;
import com.therioncc.therion.models.Duenno;
import com.therioncc.therion.repositories.IClienteRepositorio;
import com.therioncc.therion.repositories.IDuennoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AplicationConfig {

    private final IDuennoRepositorio iDuennoRepositorio;
    private final IClienteRepositorio iClienteRepositorio;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return DuCorreo -> {
            Optional<Duenno> duennoOptional = iDuennoRepositorio.findByDuCorreo(DuCorreo);
            Optional<Cliente> clienteOptional = iClienteRepositorio.findByClCorreo(DuCorreo);

            if (duennoOptional.isPresent()) {
                return duennoOptional.get();
            } else if (clienteOptional.isPresent()) {
                return clienteOptional.get();
            } else {
                throw new UsernameNotFoundException("Usuario No Encontrado");
            }
        };
    }
}
