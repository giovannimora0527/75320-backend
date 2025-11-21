# Documentación de Contenerización y Despliegue con Docker

## Descripción General

Este documento describe el proceso de contenerización y despliegue de la aplicación Clínica Uniminuto utilizando Docker. La aplicación está compuesta por tres servicios principales:

1. **Backend (Spring Boot)**: API REST en el puerto 8000
2. **Frontend (Angular)**: Aplicación web servida por Nginx en el puerto 4200
3. **MySQL**: Base de datos en el puerto 3307

## Arquitectura Docker

### Imágenes Docker

- **Backend**: Multi-stage build con Maven para compilar y JRE para ejecutar
- **Frontend**: Multi-stage build con Node.js para compilar y Nginx para servir
- **MySQL**: Imagen oficial de MySQL 8.0

### Red Docker

Todos los servicios están conectados a la red `clinica-network` para comunicación interna.

## Requisitos Previos

- Docker Desktop instalado y ejecutándose
- Docker Compose v3.8 o superior
- Al menos 4GB de RAM disponible
- Puertos libres: 8000, 4200, 3307

## Comandos Docker

### 1. Construir las Imágenes

**Construir todas las imágenes:**
```bash
docker-compose build
```

**Construir solo el backend:**
```bash
docker-compose build backend
```

**Construir solo el frontend:**
```bash
docker-compose build frontend
```

### 2. Crear y Ejecutar Contenedores

**Iniciar todos los servicios:**
```bash
docker-compose up -d
```

**Iniciar en modo interactivo (ver logs):**
```bash
docker-compose up
```

**Iniciar solo servicios específicos:**
```bash
docker-compose up -d mysql backend
```

### 3. Verificar el Estado

**Ver estado de los contenedores:**
```bash
docker-compose ps
```

**Ver logs de todos los servicios:**
```bash
docker-compose logs -f
```

**Ver logs de un servicio específico:**
```bash
docker-compose logs -f backend
docker-compose logs -f frontend
docker-compose logs -f mysql
```

### 4. Detener y Eliminar

**Detener los contenedores (mantiene datos):**
```bash
docker-compose stop
```

**Detener y eliminar contenedores:**
```bash
docker-compose down
```

**Detener y eliminar contenedores + volúmenes (⚠️ elimina datos de BD):**
```bash
docker-compose down -v
```

### 5. Reconstruir y Reiniciar

**Reconstruir imágenes y reiniciar:**
```bash
docker-compose up -d --build
```

**Reiniciar un servicio específico:**
```bash
docker-compose restart backend
```

## Variables de Entorno

### Backend

Las variables de entorno del backend se configuran en `docker-compose.yml`:

| Variable | Descripción | Valor por Defecto |
|----------|-------------|-------------------|
| `SPRING_DATASOURCE_URL` | URL de conexión a MySQL | `jdbc:mysql://mysql:3306/clinica` |
| `SPRING_DATASOURCE_USERNAME` | Usuario de MySQL | `root` |
| `SPRING_DATASOURCE_PASSWORD` | Contraseña de MySQL | `1234` |
| `JWT_SECRET` | Clave secreta para JWT | (configurada) |
| `SPRING_MAIL_USERNAME` | Usuario SMTP | `programacionwebuniminuto1@gmail.com` |
| `SPRING_MAIL_PASSWORD` | Contraseña SMTP | (configurada) |

**Para cambiar variables de entorno:**

1. Editar `docker-compose.yml`
2. O crear un archivo `.env` en la raíz del proyecto:
```env
MAIL_USERNAME=tu_email@gmail.com
MAIL_PASSWORD=tu_contraseña_app
MAIL_DEFAULT_TO=destino@gmail.com
```

### Frontend

El frontend usa la configuración de `environment.prod.ts` que apunta a:
- API URL: `http://localhost:8000/clinica/v1`

## Puertos Expuestos

| Servicio | Puerto Interno | Puerto Externo | URL de Acceso |
|----------|----------------|----------------|---------------|
| Backend | 8000 | 8000 | http://localhost:8000 |
| Frontend | 80 | 4200 | http://localhost:4200 |
| MySQL | 3306 | 3307 | localhost:3307 |

**Nota:** MySQL usa el puerto externo 3307 para evitar conflictos con una instalación local de MySQL.

## Verificación del Funcionamiento

### 1. Verificar Contenedores

```bash
docker-compose ps
```

Deberías ver los tres contenedores con estado "Up" y "healthy".

### 2. Verificar Backend

**Desde el navegador:**
- Swagger UI: http://localhost:8000/clinica/v1/swagger-ui/index.html
- API Docs: http://localhost:8000/clinica/v1/v3/api-docs

**Desde la terminal:**
```bash
curl http://localhost:8000/clinica/v1/usuario/listar
```

### 3. Verificar Frontend

**Desde el navegador:**
- Aplicación: http://localhost:4200
- Debería redirigir a: http://localhost:4200/#/login

### 4. Verificar MySQL

```bash
docker exec -it clinica-mysql mysql -u root -p1234 -e "SHOW DATABASES;"
```

## Health Checks

Todos los servicios tienen health checks configurados:

- **MySQL**: Verifica que el servidor responda a pings
- **Backend**: Verifica que Swagger UI esté accesible
- **Frontend**: Verifica que Nginx responda

Ver estado de health checks:
```bash
docker-compose ps
```

## Volúmenes Docker

### Volumen de MySQL

Los datos de MySQL se persisten en el volumen `mysql_data`. Para ver volúmenes:

```bash
docker volume ls
```

Para inspeccionar el volumen:
```bash
docker volume inspect clinica_mysql_data
```

## Solución de Problemas

### El backend no se conecta a MySQL

1. Verificar que MySQL esté saludable:
```bash
docker-compose ps mysql
```

2. Verificar logs de MySQL:
```bash
docker-compose logs mysql
```

3. Verificar que la URL de conexión sea correcta en `docker-compose.yml`

### El frontend no se conecta al backend

1. Verificar que el backend esté corriendo:
```bash
curl http://localhost:8000/clinica/v1/swagger-ui/index.html
```

2. Verificar la configuración de CORS en el backend

3. Verificar logs del frontend:
```bash
docker-compose logs frontend
```

### Reconstruir desde cero

```bash
# Detener y eliminar todo
docker-compose down -v

# Eliminar imágenes
docker-compose rm -f

# Reconstruir
docker-compose build --no-cache

# Iniciar
docker-compose up -d
```

## Comandos Útiles

### Acceder a un contenedor

```bash
# Backend
docker exec -it clinica-backend sh

# Frontend
docker exec -it clinica-frontend sh

# MySQL
docker exec -it clinica-mysql mysql -u root -p1234
```

### Ver uso de recursos

```bash
docker stats
```

### Limpiar recursos no utilizados

```bash
docker system prune -a
```

## Estructura de Archivos Docker

```
.
├── docker-compose.yml          # Orquestación de servicios
├── 75320-backend/
│   ├── Dockerfile              # Imagen del backend
│   └── .dockerignore          # Archivos a ignorar en build
└── 75320-frontend/
    ├── Dockerfile              # Imagen del frontend
    ├── nginx.conf              # Configuración de Nginx
    └── .dockerignore           # Archivos a ignorar en build
```

## Proceso Completo de Despliegue

1. **Construir imágenes:**
   ```bash
   docker-compose build
   ```

2. **Iniciar servicios:**
   ```bash
   docker-compose up -d
   ```

3. **Verificar estado:**
   ```bash
   docker-compose ps
   ```

4. **Ver logs:**
   ```bash
   docker-compose logs -f
   ```

5. **Acceder a la aplicación:**
   - Frontend: http://localhost:4200
   - Backend API: http://localhost:8000/clinica/v1
   - Swagger: http://localhost:8000/clinica/v1/swagger-ui/index.html

6. **Crear usuario administrador:**
   - Ejecutar el script SQL en MySQL o usar Swagger para crear usuario

## Notas Importantes

- La primera vez que se ejecuta, MySQL inicializa la base de datos y ejecuta el script `insertar-usuario-admin.sql` automáticamente
- El backend espera a que MySQL esté saludable antes de iniciar (usando `depends_on` con `condition: service_healthy`)
- Los datos de MySQL se persisten en un volumen Docker, por lo que sobreviven a reinicios de contenedores
- Para producción, se recomienda usar variables de entorno seguras y no hardcodear contraseñas

