# Documentación Completa - Vista de Auditoría de Login

## 📋 Tabla de Contenidos

1. [Descripción General](#descripción-general)
2. [Arquitectura del Módulo](#arquitectura-del-módulo)
3. [Backend - Endpoints y Rutas](#backend---endpoints-y-rutas)
4. [Frontend - Componentes y Rutas](#frontend---componentes-y-rutas)
5. [Flujo de Datos](#flujo-de-datos)
6. [Configuración de Seguridad](#configuración-de-seguridad)
7. [Ejemplos de Uso](#ejemplos-de-uso)
8. [Estructura de Archivos](#estructura-de-archivos)

---

## Descripción General

El módulo de auditoría permite consultar y visualizar los logs de intentos de inicio de sesión (exitosos y fallidos) con capacidades avanzadas de filtrado, paginación y búsqueda en tiempo real.

### Características Principales

- ✅ Consulta paginada de logs de auditoría
- ✅ Filtros por usuario, fecha y tipo de evento
- ✅ Búsqueda en tiempo real con debounce
- ✅ Tabla dinámica responsive
- ✅ Paginación completa con navegación
- ✅ Acceso restringido a administradores

---

## Arquitectura del Módulo

```
┌─────────────────┐
│   Frontend      │
│   Angular       │
│                 │
│  Auditoria      │
│  Component      │
└────────┬────────┘
         │ HTTP POST
         │ /auditoria/consultar
         ▼
┌─────────────────┐
│   Backend       │
│   Spring Boot   │
│                 │
│  AuditoriaApi   │
│  Controller     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│   Service       │
│  AuditoriaLogin │
│    Service      │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Repository     │
│ AuditoriaLogin  │
│  Repository     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│   Database      │
│   MySQL         │
│ auditoria_login │
└─────────────────┘
```

---

## Backend - Endpoints y Rutas

### Endpoint Principal

**Ruta:** `POST /clinica/v1/auditoria/consultar`

**Context Path Base:** `/clinica/v1`

**URL Completa:** `http://localhost:8000/clinica/v1/auditoria/consultar`

### Configuración de Seguridad

El endpoint está protegido y requiere:
- Token JWT válido en el header `Authorization: Bearer <token>`
- Rol de administrador (verificado por `adminGuard` en el frontend)

**Configuración en SecurityConfig:**
```java
// El endpoint /auditoria/** requiere autenticación
.anyRequest().authenticated()
```

### Request Body

```json
{
  "username": "admin",                    // Opcional: búsqueda parcial por usuario
  "fechaDesde": "2025-11-01T00:00:00",    // Opcional: fecha inicio (ISO 8601)
  "fechaHasta": "2025-11-30T23:59:59",    // Opcional: fecha fin (ISO 8601)
  "exitoso": true,                        // Opcional: true/false/null (todos)
  "pagina": 0,                            // Opcional: número de página (default: 0)
  "tamano": 20,                           // Opcional: tamaño página (default: 20, max: 100)
  "ordenarPor": "fechaHora",              // Opcional: campo ordenamiento (default: "fechaHora")
  "direccion": "DESC"                     // Opcional: ASC o DESC (default: "DESC")
}
```

### Response Body

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

### Clases Backend

#### Modelos

1. **`AuditoriaLoginRq`** (`com.uniminuto.clinica.model.AuditoriaLoginRq`)
   - Request DTO con filtros y parámetros de paginación

2. **`AuditoriaLoginRs`** (`com.uniminuto.clinica.model.AuditoriaLoginRs`)
   - Response DTO con datos paginados

#### Servicios

1. **`AuditoriaLoginService`** (`com.uniminuto.clinica.service.AuditoriaLoginService`)
   - Interfaz del servicio

2. **`AuditoriaLoginServiceImpl`** (`com.uniminuto.clinica.service.impl.AuditoriaLoginServiceImpl`)
   - Implementación con lógica de filtrado y paginación

#### Repositorio

**`AuditoriaLoginRepository`** (`com.uniminuto.clinica.repository.AuditoriaLoginRepository`)
- Extiende `JpaRepository<AuditoriaLogin, Long>`
- Método personalizado: `buscarConFiltros()` con consulta JPQL dinámica

#### API y Controlador

1. **`AuditoriaApi`** (`com.uniminuto.clinica.api.AuditoriaApi`)
   - Interfaz con anotaciones Swagger
   - Ruta base: `/auditoria`

2. **`AuditoriaApiController`** (`com.uniminuto.clinica.apicontroller.AuditoriaApiController`)
   - Implementación del controlador REST

---

## Frontend - Componentes y Rutas

### Ruta de Acceso

**Ruta Angular:** `/inicio/auditoria`

**URL Completa:** `http://localhost:4200/#/inicio/auditoria`

**Configuración en `app-routing.module.ts`:**
```typescript
{
  path: 'auditoria',
  component: AuditoriaComponent,
  canActivate: [adminGuard], // Solo administradores
  data: {
    title: 'Logs de Auditoría',
    module: 'auditoria',
    roles: ['ADMIN']
  }
}
```

### Menú de Navegación

**Item agregado en `navigation.ts`:**
```typescript
{
  id: 'auditoria',
  title: 'Logs de Auditoría',
  type: 'item',
  url: '/inicio/auditoria',
  icon: 'feather icon-shield',
  classes: 'nav-item'
}
```

### Componente Principal

**`AuditoriaComponent`** (`src/app/demo/pages/auditoria/auditoria.component.ts`)

**Características:**
- Componente standalone
- Formulario reactivo para filtros
- Debounce de 500ms para búsqueda en tiempo real
- Paginación con navegación
- Formateo de fechas y estados

### Servicio Angular

**`AuditoriaService`** (`src/app/demo/pages/auditoria/service/auditoria.service.ts`)

**Método principal:**
```typescript
consultarAuditoria(filtros: AuditoriaLoginRq): Observable<AuditoriaLoginRs>
```

**Endpoint consumido:**
- Base URL: `environment.apiUrl` (http://localhost:8000/clinica/v1)
- Endpoint: `auditoria/consultar`
- Método: POST

### Modelos TypeScript

1. **`AuditoriaLogin`** - Entidad de auditoría
2. **`AuditoriaLoginRq`** - Request con filtros
3. **`AuditoriaLoginRs`** - Response paginado

---

## Flujo de Datos

### 1. Usuario Accede a la Vista

```
Usuario → Navegador → http://localhost:4200/#/inicio/auditoria
         ↓
    adminGuard verifica token y rol
         ↓
    AuditoriaComponent se carga
         ↓
    ngOnInit() ejecuta buscar()
```

### 2. Carga Inicial de Datos

```
AuditoriaComponent.buscar()
         ↓
AuditoriaService.consultarAuditoria(filtros)
         ↓
HTTP POST → http://localhost:8000/clinica/v1/auditoria/consultar
         ↓
JwtTokenFilter valida token
         ↓
AuditoriaApiController.consultarAuditoria()
         ↓
AuditoriaLoginServiceImpl.consultarAuditoria()
         ↓
AuditoriaLoginRepository.buscarConFiltros()
         ↓
MySQL Query → SELECT FROM auditoria_login WHERE ...
         ↓
Response paginado → Frontend
         ↓
Tabla se actualiza con datos
```

### 3. Filtrado en Tiempo Real

```
Usuario escribe en campo "Usuario"
         ↓
onFiltroChange() se ejecuta
         ↓
searchSubject$.next() (debounce 500ms)
         ↓
Después de 500ms → buscar()
         ↓
Nueva petición al backend con filtros
         ↓
Tabla se actualiza automáticamente
```

---

## Configuración de Seguridad

### Backend

**SecurityConfig.java:**
```java
.authorizeHttpRequests(requests -> requests
    .requestMatchers(
        "/auth/login",
        "/auth/recuperar-contrasena",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    ).permitAll()
    // /auditoria/** requiere autenticación
    .anyRequest().authenticated()
)
```

### Frontend

**Guards (`guards.ts`):**
```typescript
export const adminGuard: CanActivateFn = (route, state) => {
  // Verifica que el usuario tenga rol ADMIN
  const roles = authService.getRoles();
  return roles.includes('ADMIN');
};
```

**Ruta protegida:**
```typescript
{
  path: 'auditoria',
  canActivate: [adminGuard], // Solo ADMIN puede acceder
  ...
}
```

---

## Ejemplos de Uso

### Ejemplo 1: Consultar Todos los Logs

**Request:**
```json
POST http://localhost:8000/clinica/v1/auditoria/consultar
Headers:
  Authorization: Bearer <token>
  Content-Type: application/json

Body:
{
  "pagina": 0,
  "tamano": 20
}
```

### Ejemplo 2: Filtrar por Usuario

**Request:**
```json
{
  "username": "admin",
  "pagina": 0,
  "tamano": 20
}
```

### Ejemplo 3: Filtrar por Rango de Fechas

**Request:**
```json
{
  "fechaDesde": "2025-11-01T00:00:00",
  "fechaHasta": "2025-11-30T23:59:59",
  "pagina": 0,
  "tamano": 20
}
```

### Ejemplo 4: Solo Intentos Fallidos

**Request:**
```json
{
  "exitoso": false,
  "pagina": 0,
  "tamano": 20
}
```

### Ejemplo 5: Combinación de Filtros

**Request:**
```json
{
  "username": "admin",
  "fechaDesde": "2025-11-01T00:00:00",
  "fechaHasta": "2025-11-30T23:59:59",
  "exitoso": true,
  "pagina": 0,
  "tamano": 50,
  "ordenarPor": "fechaHora",
  "direccion": "DESC"
}
```

---

## Estructura de Archivos

### Backend

```
75320-backend/
└── src/main/java/com/uniminuto/clinica/
    ├── api/
    │   └── AuditoriaApi.java                    # Interfaz API con Swagger
    ├── apicontroller/
    │   └── AuditoriaApiController.java           # Controlador REST
    ├── entity/
    │   └── AuditoriaLogin.java                  # Entidad JPA
    ├── model/
    │   ├── AuditoriaLoginRq.java                # Request DTO
    │   └── AuditoriaLoginRs.java                # Response DTO
    ├── repository/
    │   └── AuditoriaLoginRepository.java        # Repositorio JPA
    └── service/
        ├── AuditoriaLoginService.java           # Interfaz servicio
        └── impl/
            └── AuditoriaLoginServiceImpl.java   # Implementación servicio
```

### Frontend

```
75320-frontend/
└── src/app/demo/pages/auditoria/
    ├── models/
    │   ├── auditoria-login.ts                   # Entidad TypeScript
    │   ├── auditoria-login-rq.ts                # Request TypeScript
    │   └── auditoria-login-rs.ts                # Response TypeScript
    ├── service/
    │   └── auditoria.service.ts                 # Servicio Angular
    ├── auditoria.component.ts                    # Componente principal
    ├── auditoria.component.html                  # Template HTML
    ├── auditoria.component.scss                  # Estilos
    └── README.md                                 # Documentación del componente
```

### Configuración de Rutas

**Backend - `application.properties`:**
```properties
server.port=8000
server.servlet.contextPath=/clinica/v1
```

**Frontend - `app-routing.module.ts`:**
```typescript
{
  path: 'inicio',
  component: AdminComponent,
  children: [
    {
      path: 'auditoria',
      component: AuditoriaComponent,
      canActivate: [adminGuard]
    }
  ]
}
```

**Frontend - `navigation.ts`:**
```typescript
{
  id: 'auditoria',
  title: 'Logs de Auditoría',
  url: '/inicio/auditoria',
  icon: 'feather icon-shield'
}
```

---

## Rutas Completas del Sistema

### Backend API

| Método | Ruta | Descripción | Autenticación |
|--------|------|-------------|---------------|
| POST | `/clinica/v1/auditoria/consultar` | Consultar logs con filtros | JWT + ADMIN |
| POST | `/clinica/v1/auth/login` | Autenticación | Público |
| POST | `/clinica/v1/auth/recuperar-contrasena` | Recuperar contraseña | Público |
| GET | `/clinica/v1/usuario/listar` | Listar usuarios | JWT |
| POST | `/clinica/v1/usuario/guardar` | Crear usuario | JWT |
| GET | `/clinica/v1/paciente/listar` | Listar pacientes | JWT |
| GET | `/clinica/v1/swagger-ui/index.html` | Swagger UI | Público |

### Frontend

| Ruta | Componente | Guard | Descripción |
|------|------------|-------|-------------|
| `/login` | LoginComponent | loginGuard | Página de login |
| `/inicio/auditoria` | AuditoriaComponent | adminGuard | Logs de auditoría |
| `/inicio/usuario` | UsuarioComponent | adminGuard | Gestión de usuarios |
| `/inicio/paciente` | PacienteComponent | medicoAdminGuard | Gestión de pacientes |
| `/inicio/cita` | CitaComponent | authGuard | Gestión de citas |
| `/inicio/medico` | MedicoComponent | adminGuard | Gestión de médicos |
| `/inicio/medicamento` | MedicamentoComponent | medicoAdminGuard | Gestión de medicamentos |
| `/inicio/formula-medica` | FormulaMedicaComponent | medicoAdminGuard | Fórmulas médicas |
| `/inicio/historia-medica` | HistoriaMedicaComponent | medicoAdminGuard | Historias clínicas |
| `/inicio/especializacion` | EspecializacionComponent | adminGuard | Especializaciones |

---

## Configuración de Variables de Entorno

### Backend

No requiere variables de entorno adicionales. Usa la configuración de `application.properties`:
- `spring.datasource.url`: Conexión a MySQL
- `jwt.secret`: Clave para firmar JWT
- `security.login.max.failed.attempts`: Intentos máximos (default: 3)
- `security.login.block.duration.minutes`: Duración bloqueo (default: 5)

### Frontend

**`environment.ts`:**
```typescript
export const environment = {
  apiUrl: 'http://localhost:8000/clinica/v1',
  apiUrlAuth: 'http://localhost:8000/clinica/v1'
};
```

---

## Pruebas y Verificación

### 1. Verificar Endpoint en Swagger

1. Iniciar backend: `mvn spring-boot:run`
2. Abrir: http://localhost:8000/clinica/v1/swagger-ui/index.html
3. Buscar tag "Auditoría"
4. Probar endpoint `POST /auditoria/consultar`

### 2. Verificar Componente Frontend

1. Iniciar frontend: `npm start`
2. Iniciar sesión como administrador
3. Navegar a: http://localhost:4200/#/inicio/auditoria
4. Verificar que se cargan los datos
5. Probar filtros y paginación

### 3. Verificar desde Postman/cURL

```bash
# Obtener token primero
curl -X POST "http://localhost:8000/clinica/v1/auth/login" \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123"}'

# Consultar auditoría
curl -X POST "http://localhost:8000/clinica/v1/auditoria/consultar" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{
    "pagina": 0,
    "tamano": 20
  }'
```

---

## Troubleshooting

### Error 403 Forbidden

**Causa:** Token JWT inválido o usuario sin rol ADMIN.

**Solución:**
- Verificar que el token esté en el header `Authorization: Bearer <token>`
- Verificar que el usuario tenga rol `ADMIN`
- Verificar que el token no haya expirado

### No se muestran datos

**Causa:** No hay registros en la tabla `auditoria_login`.

**Solución:**
- Realizar algunos intentos de login (exitosos o fallidos)
- Verificar que la tabla tenga datos: `SELECT * FROM auditoria_login;`

### Filtros no funcionan

**Causa:** Formato de fecha incorrecto o valores null.

**Solución:**
- Usar formato ISO 8601 para fechas: `YYYY-MM-DDTHH:mm:ss`
- Verificar que los filtros se envíen correctamente en el request

---

## Mejoras Futuras

- [ ] Exportar resultados a CSV/Excel
- [ ] Gráficos de estadísticas (intentos por día, usuarios más activos)
- [ ] Filtro adicional por IP de origen
- [ ] Búsqueda avanzada con múltiples criterios
- [ ] Notificaciones en tiempo real de intentos fallidos
- [ ] Dashboard de seguridad con métricas

---

**Versión:** 1.0.0  
**Fecha:** Noviembre 2025  
**Autor:** Equipo 827766 - Anyi Natkary Gómez

