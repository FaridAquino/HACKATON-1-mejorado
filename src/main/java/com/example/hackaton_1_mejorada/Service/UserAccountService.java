package com.example.hackaton_1_mejorada.Service;

import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {
    @Autowired
    UsuarioRepository repository; //Aqui se pone el repositorio de la entidad donde se va a validar

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Usuario> list() {
        return repository.findAll();
    } //Aqui se pone tu entidad

    public void save(Usuario empleado) {
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        repository.save(empleado);
    } //Cambias la variable por tu entidad
}
