-- Script de inicialización de base de datos para Commerce
-- Este script crea la base de datos y las tablas mínimas necesarias

-- Crear base de datos si no existe
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'BDCommerce')
BEGIN
    CREATE DATABASE BDCommerce;
END
GO

USE BDCommerce;
GO

-- Crear tabla de usuarios si no existe
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='ctrlUsuarios' AND xtype='U')
BEGIN
    CREATE TABLE ctrlUsuarios (
        idUsuario INT IDENTITY(1,1) PRIMARY KEY,
        nombreUsuario VARCHAR(50) NOT NULL UNIQUE,
        claveUsuario VARCHAR(50) NOT NULL,
        nombreEmpresa VARCHAR(200),
        nit VARCHAR(20),
        nivelUsuario INT NOT NULL DEFAULT 1,
        usuarioCorporativo INT DEFAULT 0,
        esActivo BIT DEFAULT 1,
        fechaCreacion DATETIME DEFAULT GETDATE(),
        fechaModificacion DATETIME DEFAULT GETDATE()
    );
    
    -- Insertar usuario administrador por defecto
    INSERT INTO ctrlUsuarios (nombreUsuario, claveUsuario, nombreEmpresa, nit, nivelUsuario, usuarioCorporativo, esActivo)
    VALUES ('admin', 'admin123', 'Plásticos Ambientales', '900000000-1', 3, 1, 1);
    
    -- Insertar usuario de prueba
    INSERT INTO ctrlUsuarios (nombreUsuario, claveUsuario, nombreEmpresa, nit, nivelUsuario, usuarioCorporativo, esActivo)
    VALUES ('vendedor', 'vendedor123', 'Plásticos Ambientales', '900000000-1', 1, 0, 1);
END
GO

-- Crear tabla de terceros (clientes/proveedores)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='terceros' AND xtype='U')
BEGIN
    CREATE TABLE terceros (
        idTercero INT IDENTITY(1,1) PRIMARY KEY,
        nit VARCHAR(20) NOT NULL,
        nombre VARCHAR(200) NOT NULL,
        direccion VARCHAR(200),
        telefono VARCHAR(50),
        email VARCHAR(100),
        tipoTercero INT, -- 1=Cliente, 2=Proveedor, 3=Ambos
        esActivo BIT DEFAULT 1,
        fechaCreacion DATETIME DEFAULT GETDATE()
    );
END
GO

-- Crear tabla de categorías
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='categorias' AND xtype='U')
BEGIN
    CREATE TABLE categorias (
        idCategoria INT IDENTITY(1,1) PRIMARY KEY,
        nombreCategoria VARCHAR(100) NOT NULL,
        descripcion VARCHAR(200),
        esActivo BIT DEFAULT 1
    );
    
    -- Insertar categorías de ejemplo
    INSERT INTO categorias (nombreCategoria, descripcion) VALUES 
    ('BOLSAS', 'Bolsas plásticas'),
    ('ROLLOS', 'Rollos de plástico'),
    ('LAMINAS', 'Láminas plásticas');
END
GO

-- Crear tabla de productos
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='productos' AND xtype='U')
BEGIN
    CREATE TABLE productos (
        idProducto INT IDENTITY(1,1) PRIMARY KEY,
        codigoProducto VARCHAR(50) NOT NULL UNIQUE,
        nombreProducto VARCHAR(200) NOT NULL,
        idCategoria INT,
        unidadMedida VARCHAR(20),
        precioVenta DECIMAL(18,2),
        costo DECIMAL(18,2),
        stock DECIMAL(18,2) DEFAULT 0,
        esActivo BIT DEFAULT 1,
        FOREIGN KEY (idCategoria) REFERENCES categorias(idCategoria)
    );
END
GO

-- Crear tabla de operaciones para producción
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='operaciones' AND xtype='U')
BEGIN
    CREATE TABLE operaciones (
        idOperacion INT IDENTITY(1,1) PRIMARY KEY,
        nombreOperacion VARCHAR(100) NOT NULL,
        descripcion VARCHAR(200),
        orden INT,
        esActivo BIT DEFAULT 1
    );
    
    -- Insertar operaciones típicas de plásticos
    INSERT INTO operaciones (nombreOperacion, descripcion, orden) VALUES 
    ('EXTRUSION', 'Proceso de extrusión', 1),
    ('IMPRESION', 'Proceso de impresión', 2),
    ('SELLADO', 'Proceso de sellado', 3),
    ('REFILADO', 'Proceso de refilado', 4),
    ('EMPAQUE', 'Proceso de empaque', 5);
END
GO

-- Crear tabla de órdenes de trabajo
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='ordenTrabajo' AND xtype='U')
BEGIN
    CREATE TABLE ordenTrabajo (
        idOrden INT IDENTITY(1,1) PRIMARY KEY,
        numeroOrden VARCHAR(20) NOT NULL UNIQUE,
        fechaOrden DATETIME DEFAULT GETDATE(),
        idProducto INT,
        cantidad DECIMAL(18,2),
        estado VARCHAR(20) DEFAULT 'PENDIENTE',
        observaciones TEXT,
        FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
    );
END
GO

-- Crear tabla de configuración
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='configuracion' AND xtype='U')
BEGIN
    CREATE TABLE configuracion (
        idConfig INT IDENTITY(1,1) PRIMARY KEY,
        clave VARCHAR(50) NOT NULL UNIQUE,
        valor VARCHAR(500),
        descripcion VARCHAR(200)
    );
    
    -- Insertar configuraciones básicas
    INSERT INTO configuracion (clave, valor, descripcion) VALUES 
    ('NOMBRE_EMPRESA', 'Plásticos Ambientales S.A.', 'Nombre de la empresa'),
    ('NIT_EMPRESA', '900000000-1', 'NIT de la empresa'),
    ('VERSION_SISTEMA', '1.0', 'Versión del sistema');
END
GO

PRINT 'Base de datos BDCommerce inicializada correctamente';
GO