package com.example.hackaton_1_mejorada.Domain.Empresa;

import com.example.hackaton_1_mejorada.Domain.DTO.EmpresaRequestDTO;
import com.example.hackaton_1_mejorada.Domain.DTO.EmpresaResponseDTO;
import com.example.hackaton_1_mejorada.Domain.Mappers.EmpresaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/companies")
@RequiredArgsConstructor
public class EmpresaController {
    private final EmpresaService empresaService;
    private final EmpresaMapper empresaMapper;

    @PostMapping()
    public ResponseEntity<EmpresaResponseDTO> postEmpresa(@RequestBody EmpresaRequestDTO empresaDTO) {
        Empresa empresa = empresaMapper.toEntity(empresaDTO);
        Empresa nuevaEmpresa = empresaService.crearEmpresa(empresa);
        return new ResponseEntity<>(empresaMapper.toDTO(nuevaEmpresa), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<EmpresaResponseDTO>> getEmpresas() {
        List<Empresa> empresas = empresaService.obtenerEmpresas();
        List<EmpresaResponseDTO> empresasDTO = empresas.stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(empresasDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> getEmpresa(@PathVariable Long id) {
        Empresa empresa = empresaService.informacionEmpresa(id);
        return new ResponseEntity<>(empresaMapper.toDTO(empresa), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> putEmpresa(@PathVariable Long id, @RequestBody EmpresaRequestDTO empresaDTO) {
        Empresa empresa = empresaMapper.toEntity(empresaDTO);
        Empresa empresaActualizada = empresaService.actualizarEmpresa(id, empresa);
        return new ResponseEntity<>(empresaMapper.toDTO(empresaActualizada), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<EmpresaResponseDTO> patchEstado(@PathVariable Long id) {
        Empresa empresa = empresaService.actulizarestado(id);
        return new ResponseEntity<>(empresaMapper.toDTO(empresa), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @GetMapping("/{id}/consumption")
    public ResponseEntity<String> getConsumo(@PathVariable Long id) {
        String texto = empresaService.obtenerGastos(id);
        return new ResponseEntity<>(texto, HttpStatus.OK);
    }
}
