package com.therioncc.therion.models;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"du_correo"})})
public class Duenno implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDuenno;

    private String dunNombre;
    private String duAppat;
    private String duApmat;

    @Column(name = "du_correo")
    private String duCorreo;

    private String duContrasenna;

    @OneToMany(mappedBy = "duenno")
    private List<Negocio> negocios;

    @OneToMany(mappedBy = "duenno")
    private List<Contacto> contactos;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("Duenno"));
    }

    @Override
    public String getPassword() {
        return duContrasenna;
    }

    @Override
    public String getUsername() {
        return duCorreo;
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