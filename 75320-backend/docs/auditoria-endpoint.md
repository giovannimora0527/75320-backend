# Endpoint de Auditoría de Login

## Descripción

Endpoint REST para consultar los logs de auditoría de inicio de sesión con soporte para filtros y paginación.

## Endpoint

**POST** `/auditoria/consultar`

## Autenticación

Requiere token JWT válido (solo administradores).

## Request Body

```json
{
  "username": "admin",              // Opcional: filtrar por usuario (búsqueda parcial)
  "fechaDesde": "2025-01-01T00:00:00",  // Opcional: fecha de inicio del rango
  "fechaHasta": "2025-12-31T23:59:59",  // Opcional: fecha de fin del rango
  "exitoso": true,                  // Opcional: true = exitosos, false = fallidos, null = todos
  "pagina": 0,                      // Opcional: número de página (base 0, default: 0)
  "tamano": 20,                     // Opcional: tamaño de página (default: 20, máximo: 100)
  "ordenarPor": "fechaHora",        // Opcional: campo para ordenar (default: "fechaHora")
  "direccion": "DESC"                // Opcional: ASC o DESC (default: "DESC")
}
```

## Response

```json
{
  "contenido": [
    {
      "id": 1,
      "usernameIngresado": "admin",
      "fechaHora": "2025-11-20T10:30:00",
      "exitoso": true,
      "descripcion": "Login exitoso",
      "ipOrigen": "127.0.0.1",
      "usuarioId": 1
    }
  ],
  "totalElementos": 150,
  "totalPaginas": 8,
  "paginaActual": 0,
  "tamanoPagina": 20,
  "tieneSiguiente": true,
  "tieneAnterior": false
}
```

## Ejemplos de Uso

### Consultar todos los logs (primera página)
```bash
curl -X POST "http://localhost:8000/clinica/v1/auditoria/consultar" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{
    "pagina": 0,
    "tamano": 20
  }'
```

### Filtrar por usuario
```bash
curl -X POST "http://localhost:8000/clinica/v1/auditoria/consultar" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{
    "username": "admin",
    "pagina": 0,
    "tamano": 20
  }'
```

### Filtrar por rango de fechas
```bash
curl -X POST "http://localhost:8000/clinica/v1/auditoria/consultar" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{
    "fechaDesde": "2025-11-01T00:00:00",
    "fechaHasta": "2025-11-30T23:59:59",
    "pagina": 0,
    "tamano": 20
  }'
```

### Filtrar solo intentos fallidos
```bash
curl -X POST "http://localhost:8000/clinica/v1/auditoria/consultar" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{
    "exitoso": false,
    "pagina": 0,
    "tamano": 20
  }'
```

## Notas

- La búsqueda por `username` es parcial y case-insensitive
- Las fechas deben estar en formato ISO 8601
- El tamaño máximo de página es 100 registros
- Los resultados se ordenan por fecha descendente por defecto

