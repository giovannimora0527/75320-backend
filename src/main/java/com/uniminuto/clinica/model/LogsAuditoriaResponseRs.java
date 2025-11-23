package com.uniminuto.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

/**
 * Modelo de respuesta paginada para los logs de auditoría.
 * Contiene la lista de logs y la información de paginación.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogsAuditoriaResponseRs {

    /** Lista de logs de auditoría */
    private List<LogAuditoriaRs> data;

    /** Número total de registros */
    private Long total;

    /** Página actual */
    private Integer pagina;

    /** Tamaño de página */
    private Integer tamanoPagina;

    /** Número total de páginas */
    private Integer totalPaginas;
}

