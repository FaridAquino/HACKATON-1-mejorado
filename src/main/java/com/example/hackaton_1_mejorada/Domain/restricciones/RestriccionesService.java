package com.example.hackaton_1_mejorada.Domain.restricciones;

import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;
import com.example.hackaton_1_mejorada.Domain.Empresa.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestriccionesService {
    private final RestriccionesRepository restriccionesRepository;
    private final EmpresaRepository empresaRepository;

    public List<Restricciones> findAll() {
        return restriccionesRepository.findAll();
    }

    public Restricciones save(Restricciones restricciones, Long id) {
        Empresa empresa=empresaRepository.findById(id).orElseThrow(()->new RuntimeException("Empresa no encontrada"));
        Restricciones restricciones1=restriccionesRepository.save(restricciones);

        restricciones1.setEmpresa(empresa);


        return restriccionesRepository.save(restricciones1);
    }

    public Restricciones actualizar(Long id, Restricciones restricciones){
        Restricciones restricciones1=restriccionesRepository.findById(id).orElseThrow(()->new RuntimeException("No se encontro la restriccion"));
        restricciones1.setDescripcion(restricciones.getDescripcion());
        restricciones1.setTipo(restricciones.getTipo());

        return restriccionesRepository.save(restricciones1);
    }

    public void delete(Long id) {
        restriccionesRepository.deleteById(id);
    }
}
