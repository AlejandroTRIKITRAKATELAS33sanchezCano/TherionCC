package com.therioncc.therion.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDuenno")
public class Duenno implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idDuenno")
    private Long idDuenno;

    @JsonProperty("dunNombre")
    private String dunNombre;

    @JsonProperty("duAppat")
    private String duAppat;

    @JsonProperty("duApmat")
    private String duApmat;

    @Column(name = "du_correo")
    @JsonProperty("duCorreo")
    private String duCorreo;

    @JsonProperty("duContrasenna")
    private String duContrasenna;

    @OneToMany(mappedBy = "duenno")
    private List<Negocio> negocios;


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