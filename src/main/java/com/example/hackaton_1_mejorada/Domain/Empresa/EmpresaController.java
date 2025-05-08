package com.example.hackaton_1_mejorada.Domain.Empresa;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
@RequiredArgsConstructor
public class EmpresaController {
    private final EmpresaService empresaService;

    @PostMapping()
    public ResponseEntity<Empresa> postEmpresa(@RequestBody Empresa empresa){
        Empresa nempresa=empresaService.crearEmpresa(empresa);
        return new ResponseEntity<>(nempresa, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<Empresa>> getEmpresas(){
        List<Empresa> empresas=empresaService.obtenerEmpresas();
        return new ResponseEntity<>(empresas,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable Long id){
        Empresa empresa=empresaService.informacionEmpresa(id);
        return new ResponseEntity<>(empresa,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> putEmpresa(@PathVariable Long id, @RequestBody Empresa empresa){
        Empresa nempresa=empresaService.actualizarEmpresa(id, empresa);
        return new ResponseEntity<>(nempresa,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Empresa> patchEstado(@PathVariable Long id){
        Empresa empresa= empresaService.actulizarestado(id);
        return new ResponseEntity<>(empresa,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @GetMapping("/{id}/consumption")
    public ResponseEntity<String> getConsumo(@PathVariable Long id){
        String texto=empresaService.obtenerGastos(id);
        return new ResponseEntity<>(texto,HttpStatus.OK);
    }


}
