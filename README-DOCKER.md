# Guía de Arranque con Docker - Commerce Plásticos Ambientales

## Requisitos Previos
- Docker Desktop instalado
- Docker Compose instalado
- Al menos 4GB de RAM disponible
- Puertos 9000 y 1433 libres

## Estructura de Archivos Docker
```
Commerce-master/
├── Dockerfile                    # Imagen para la aplicación Tomcat
├── docker-compose.yml           # Orquestación de servicios
├── web/META-INF/
│   ├── context.xml             # Configuración original
│   └── context-docker.xml      # Configuración para Docker
└── docker/sql-scripts/
    └── 01-init-database.sql    # Script inicial de BD
```

## Pasos para Arrancar la Aplicación

### 1. Construir y Levantar los Servicios
```bash
# Navegar al directorio del proyecto
cd /Users/miguelrodriguez/code/PlasticosAmbientales/Commerce-master

# Construir y levantar los contenedores
docker-compose up -d --build

# Ver los logs
docker-compose logs -f
```

### 2. Verificar el Estado de los Servicios
```bash
# Ver contenedores en ejecución
docker ps

# Verificar la salud de SQL Server
docker exec commerce-sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 'Tecnnova2018*' -Q "SELECT 1"
```

### 3. Acceder a la Aplicación
- **URL de la aplicación**: http://localhost:9000
- **Usuario administrador**: admin
- **Contraseña**: admin123

### 4. Acceder a la Base de Datos
```bash
# Conectar a SQL Server desde línea de comandos
docker exec -it commerce-sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 'Tecnnova2018*'

# O usar cualquier cliente SQL Server con:
# Host: localhost
# Puerto: 1433
# Usuario: sa
# Contraseña: Tecnnova2018*
# Base de datos: BDCommerce
```

## Comandos Útiles

### Detener los servicios
```bash
docker-compose down
```

### Detener y eliminar volúmenes (resetear BD)
```bash
docker-compose down -v
```

### Ver logs de un servicio específico
```bash
docker-compose logs -f commerce-app
docker-compose logs -f sqlserver
```

### Ejecutar comandos en los contenedores
```bash
# Acceder al contenedor de la aplicación
docker exec -it commerce-app bash

# Acceder al contenedor de SQL Server
docker exec -it commerce-sqlserver bash
```

### Reconstruir solo la aplicación
```bash
docker-compose build commerce-app
docker-compose up -d commerce-app
```

## Solución de Problemas

### 1. Error de conexión a la base de datos
- Verificar que SQL Server esté saludable: `docker-compose ps`
- Revisar los logs: `docker-compose logs sqlserver`
- Asegurarse de que el script SQL se ejecutó correctamente

### 2. La aplicación no compila
- Verificar que todas las librerías estén en el directorio `lib/`
- Revisar los logs de construcción: `docker-compose build --no-cache commerce-app`

### 3. Error 404 al acceder a la aplicación
- Verificar que Tomcat esté corriendo: `docker-compose logs commerce-app`
- Comprobar que los archivos se copiaron correctamente al contenedor

### 4. Problemas de permisos
```bash
# Dar permisos de ejecución si es necesario
chmod +x docker/sql-scripts/*.sql
```

## Notas de Seguridad
⚠️ **IMPORTANTE**: Esta configuración es SOLO para desarrollo/pruebas:
- Las contraseñas están hardcodeadas
- SQL Server está expuesto públicamente
- No hay HTTPS configurado
- La aplicación tiene vulnerabilidades de seguridad conocidas

## Personalización

### Cambiar puertos
Editar `docker-compose.yml`:
```yaml
ports:
  - "9000:8080"  # Puerto actual de la aplicación (host:contenedor)
  - "1433:1433"  # Puerto de SQL Server
```
Para usar otro puerto, cambiar el primer número. Por ejemplo:
- "9001:8080" para usar el puerto 9001
- "8080:8080" para usar el puerto 8080

### Cambiar credenciales de BD
1. Editar `docker-compose.yml` (variable SA_PASSWORD)
2. Editar `web/META-INF/context-docker.xml`
3. Reconstruir y reiniciar los servicios

### Agregar más datos iniciales
Crear nuevos archivos SQL en `docker/sql-scripts/` con nombres como:
- `02-datos-productos.sql`
- `03-datos-clientes.sql`

Los scripts se ejecutan en orden alfabético al iniciar SQL Server por primera vez.

## Monitoreo
```bash
# Ver uso de recursos
docker stats

# Ver espacio en disco usado por Docker
docker system df
```

## Backup de la Base de Datos
```bash
# Crear backup
docker exec commerce-sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 'Tecnnova2018*' \
  -Q "BACKUP DATABASE BDCommerce TO DISK = '/var/opt/mssql/backup/BDCommerce.bak'"

# Copiar backup al host
docker cp commerce-sqlserver:/var/opt/mssql/backup/BDCommerce.bak ./backup/
```