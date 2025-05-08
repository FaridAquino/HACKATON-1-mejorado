package com.example.hackaton_1_mejorada.Domain.usuario;

import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;
import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import com.example.hackaton_1_mejorada.Domain.solicitud.Solicitud;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;

    @Enumerated(EnumType.STRING)
    private UsuarioRol rol;

    private Integer tokens_consumidos_en_total=0;

    @OneToOne(mappedBy = "administrador")
    private Empresa  empresa_administrada;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonBackReference
    private Empresa empresa;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Limites> limites=new ArrayList<>();

    @OneToMany(mappedBy = "consultante",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Solicitud> solicitudes=new ArrayList<>();

    //Para el espring securty
    private Boolean expired=false;

    private Boolean locked=false;

    private Boolean credentialsExpired=false;

    private Boolean enable=true;

    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getUsername() {
        return correo;
    }//Aqui cambiamos tambien

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
