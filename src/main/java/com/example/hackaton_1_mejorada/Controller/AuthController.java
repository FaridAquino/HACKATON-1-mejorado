package com.example.hackaton_1_mejorada.Controller;

import com.example.hackaton_1_mejorada.Domain.DTO.JwtAuthenticationResponse;
import com.example.hackaton_1_mejorada.Domain.DTO.SigninRequest;
import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.example.hackaton_1_mejorada.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacion")
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody Usuario request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
