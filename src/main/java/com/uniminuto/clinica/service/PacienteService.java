package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para la gestión de pacientes.
 *
 * @author lmora
 */
public interface PacienteService {

    /**
     * Obtiene todos los pacientes.
     *
     * @return Lista de todos los pacientes
     */
    List<Paciente> encontrarTodosLosPacientes();

    /**
     * Obtiene todos los pacientes activos.
     *
     * @return Lista de pacientes activos
     */
    List<Paciente> encontrarPacientesActivos();

    /**
     * Busca un paciente por su ID.
     *
     * @param id ID del paciente
     * @return Optional con el paciente encontrado
     */
    Optional<Paciente> encontrarPacientePorId(Long id);

    /**
     * Busca un paciente por número de documento.
     *
     * @param numeroDocumento Número de documento del paciente
     * @return Optional con el paciente encontrado
     */
    Optional<Paciente> encontrarPacientePorDocumento(String numeroDocumento);

    /**
     * Busca pacientes por nombre (nombres o apellidos).
     *
     * @param nombre Nombre a buscar
     * @return Lista de pacientes que coinciden con el nombre
     */
    List<Paciente> buscarPacientesPorNombre(String nombre);

    /**
     * Busca pacientes por tipo de documento.
     *
     * @param tipoDocumento Tipo de documento
     * @return Lista de pacientes con el tipo de documento especificado
     */
    List<Paciente> buscarPacientesPorTipoDocumento(String tipoDocumento);

    /**
     * Busca pacientes por rango de edad.
     *
     * @param edadMinima Edad mínima
     * @param edadMaxima Edad máxima
     * @return Lista de pacientes en el rango de edad especificado
     */
    List<Paciente> buscarPacientesPorRangoEdad(int edadMinima, int edadMaxima);

    /**
     * Guarda un nuevo paciente.
     *
     * @param paciente Paciente a guardar
     * @return Paciente guardado
     */
    Paciente guardarPaciente(Paciente paciente);

    /**
     * Actualiza un paciente existente.
     *
     * @param paciente Paciente con los datos actualizados
     * @return Paciente actualizado
     */
    Paciente actualizarPaciente(Paciente paciente);

    /**
     * Elimina un paciente por su ID.
     *
     * @param id ID del paciente a eliminar
     */
    void eliminarPaciente(Long id);

    /**
     * Desactiva un paciente (cambio de estado activo a false).
     *
     * @param id ID del paciente a desactivar
     * @return Paciente desactivado
     */
    Paciente desactivarPaciente(Long id);

    /**
     * Activa un paciente (cambio de estado activo a true).
     *
     * @param id ID del paciente a activar
     * @return Paciente activado
     */
    Paciente activarPaciente(Long id);

    /**
     * Verifica si existe un paciente con el número de documento especificado.
     *
     * @param numeroDocumento Número de documento
     * @return true si existe, false en caso contrario
     */
    boolean existePacientePorDocumento(String numeroDocumento);

    /**
     * Cuenta el número total de pacientes activos.
     *
     * @return Número de pacientes activos
     */
    long contarPacientesActivos();

    /**
     * Lista pacientes ordenados por fecha de nacimiento descendente.
     *
     * @return Lista ordenada
     */
    List<Paciente> listarPorFechaNacimientoDesc();
}
