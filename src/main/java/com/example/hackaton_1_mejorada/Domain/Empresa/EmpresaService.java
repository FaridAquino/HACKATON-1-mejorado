package com.example.hackaton_1_mejorada.Domain.Empresa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public Empresa crearEmpresa(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    public List<Empresa> obtenerEmpresas(){
        return empresaRepository.findAll();
    }

    public Empresa informacionEmpresa(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }

    public Empresa actualizarEmpresa(Long id, Empresa nuevaEmpresa) {
        Empresa empresaExistente = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        empresaExistente.setNombre(nuevaEmpresa.getNombre());
        empresaExistente.setRUC(nuevaEmpresa.getRUC());
        empresaExistente.setFecha_de_afiliacion(nuevaEmpresa.getFecha_de_afiliacion());
        empresaExistente.setEstado(nuevaEmpresa.getEstado());
        empresaExistente.setAdministrador(nuevaEmpresa.getAdministrador());

        return empresaRepository.save(empresaExistente);
    }


    public Empresa actulizarestado(Long id){
        Empresa empresaExistente = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        if (empresaExistente.getEstado()==EmpresaRole.DESACTIVA){
            empresaExistente.setEstado(EmpresaRole.ACTIVA);
            return empresaRepository.save(empresaExistente);
        }

        empresaExistente.setEstado(EmpresaRole.DESACTIVA);

        return empresaRepository.save(empresaExistente);
    }

    public String obtenerGastos(Long id){
        Empresa empresaExistente = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        String texto="La empresa ah gastado: "+empresaExistente.getGastado() +" soles";

        return texto;
    }





}

