package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.repository.EspecializacionRepository;
import com.uniminuto.clinica.service.EspecializacionService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de especializaciones médicas
 * 
 * @author JulianLopez
 */
@Service
public class EspecializacionServiceImpl implements EspecializacionService {
    
    @Autowired
    private EspecializacionRepository especializacionRepository;
    
    @Override
    public List<Especializacion> encontrarTodasLasEspecializaciones() {
        return this.especializacionRepository.findAll();
    }
    
    @Override
    public Especializacion buscarEspecializacionPorId(Integer id) throws BadRequestException {
        Optional<Especializacion> optEspecializacion = this.especializacionRepository.findById(id);
        if (!optEspecializacion.isPresent()) {
            throw new BadRequestException("No se encuentra la especialización con ID: " + id);
        }
        return optEspecializacion.get();
    }
    
    @Override
    public Especializacion crearEspecializacion(Especializacion especializacion) throws BadRequestException {
        // Validaciones
        if (especializacion.getCodigoEspecializacion() == null || especializacion.getCodigoEspecializacion().trim().isEmpty()) {
            throw new BadRequestException("El código de la especialización es requerido");
        }
        
        if (especializacion.getNombre() == null || especializacion.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre de la especialización es requerido");
        }
        
        if (especializacion.getDescripcion() == null || especializacion.getDescripcion().trim().isEmpty()) {
            throw new BadRequestException("La descripción de la especialización es requerida");
        }
        
        // Verificar que no exista una especialización con el mismo código
        Optional<Especializacion> existente = this.especializacionRepository.findByCodigoEspecializacion(especializacion.getCodigoEspecializacion());
        if (existente.isPresent()) {
            throw new BadRequestException("Ya existe una especialización con el código: " + especializacion.getCodigoEspecializacion());
        }
        
        return this.especializacionRepository.save(especializacion);
    }
    
    @Override
    public Especializacion actualizarEspecializacion(Integer id, Especializacion especializacion) throws BadRequestException {
        // Verificar que la especialización existe
        Especializacion especializacionExistente = buscarEspecializacionPorId(id);
        
        // Validaciones
        if (especializacion.getCodigoEspecializacion() == null || especializacion.getCodigoEspecializacion().trim().isEmpty()) {
            throw new BadRequestException("El código de la especialización es requerido");
        }
        
        if (especializacion.getNombre() == null || especializacion.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre de la especialización es requerido");
        }
        
        if (especializacion.getDescripcion() == null || especializacion.getDescripcion().trim().isEmpty()) {
            throw new BadRequestException("La descripción de la especialización es requerida");
        }
        
        // Verificar que no exista otra especialización con el mismo código (excepto la actual)
        Optional<Especializacion> existente = this.especializacionRepository.findByCodigoEspecializacion(especializacion.getCodigoEspecializacion());
        if (existente.isPresent() && !existente.get().getId().equals(id)) {
            throw new BadRequestException("Ya existe otra especialización con el código: " + especializacion.getCodigoEspecializacion());
        }
        
        // Actualizar campos
        especializacionExistente.setCodigoEspecializacion(especializacion.getCodigoEspecializacion());
        especializacionExistente.setNombre(especializacion.getNombre());
        especializacionExistente.setDescripcion(especializacion.getDescripcion());
        
        return this.especializacionRepository.save(especializacionExistente);
    }
    
    @Override
    public void eliminarEspecializacion(Integer id) throws BadRequestException {
        // Verificar que la especialización existe
        Especializacion especializacion = buscarEspecializacionPorId(id);
        
        // Eliminar
        this.especializacionRepository.delete(especializacion);
    }
}