package com.therioncc.therion.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String clNombre;
    private String clAppat;
    private String clApmat;

    private String clCorreo;
    private String clContrasenna;
    private String clNumero1;
    private String clNumero2;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Direccion direccion;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("Cliente"));
    }

    @Override
    public String getPassword() {
        return clContrasenna;
    }

    @Override
    public String getUsername() {
        return clCorreo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}