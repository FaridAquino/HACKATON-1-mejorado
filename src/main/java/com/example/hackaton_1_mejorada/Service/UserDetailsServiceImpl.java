package com.example.hackaton_1_mejorada.Service;

import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository repository;//Aqui nos indica donde se va a guardar
                                    //Ademas mi entidad tiene que implementar de UserDetails
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        System.out.println("Intentando autenticar al usuario: " + correo);
        var empleado = repository.findByCorreo(correo);
        if (empleado == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con correo: " + correo);
        }
        return repository.findByCorreo(correo);
    }

    public UserDetailsService userDetailsService() {
        return this;
    }
}
