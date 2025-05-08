package com.example.hackaton_1_mejorada.Service;

import com.example.hackaton_1_mejorada.Domain.DTO.JwtAuthenticationResponse;
import com.example.hackaton_1_mejorada.Domain.DTO.SigninRequest;
import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder; //Necesita estar configurado

    @Autowired
    JwtService jwtService; //Necesita estar configurado

    @Autowired
    AuthenticationManager authenticationManager; //Necesita estar configurado

    public JwtAuthenticationResponse signup(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuarioRepository.save(usuario);
        var jwt = jwtService.generateToken(usuario);

        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);

        return response;
    }

    public JwtAuthenticationResponse signin(SigninRequest request) throws IllegalArgumentException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword())); //Si cambio la forma de registrarme, cambio la carpeta de filter

        var user = usuarioRepository.findByCorreo(request.getCorreo());

        var jwt = jwtService.generateToken(user);

        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);

        return response;
    }
}//Copiar y pegar
