#!/bin/bash
# Script para reconstruir y reiniciar la aplicación

echo "Deteniendo contenedores..."
docker-compose down

echo "Reconstruyendo la imagen..."
docker-compose build --no-cache commerce-app

echo "Iniciando servicios..."
docker-compose up -d

echo "Esperando a que SQL Server esté listo..."
sleep 30

echo "Creando base de datos..."
docker exec -i commerce-sqlserver /opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'Tecnnova2018*' -C < docker/sql-scripts/01-init-database.sql

echo "Verificando logs..."
docker-compose logs --tail=20

echo ""
echo "La aplicación debería estar disponible en: http://localhost:9000"
echo "Usuario: admin"
echo "Contraseña: admin123"