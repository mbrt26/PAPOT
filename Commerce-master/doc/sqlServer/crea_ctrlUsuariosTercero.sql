USE [BDComercial]
GO
/****** Objeto:  Table [dbo].[ctrlUsuariosTercero]    Fecha de la secuencia de comandos: 07/18/2009 13:52:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ctrlUsuariosTercero](
	[idUsuario] [decimal](13, 0) NOT NULL,
	[idCliente] [nvarchar](20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[estado] [smallint] NOT NULL,
 CONSTRAINT [PK_ctrlUsuariosTercero] PRIMARY KEY CLUSTERED 
(
	[idUsuario] ASC,
	[idCliente] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]


---
Pasos

1. Crear tabla ctrlUsuariosTercero con este script
2. Crear USUARIO empresa con cedula en ctrlUsuarios, NIVEL = 5
3. Crear USUARIO en ctrlUsuariosRutas incluyendo el codigo de la ruta que atiende esa empresa
4. Crear USUARIO en ctrlUsuariosTercero incluyendo en CODIGO empresa


Cedula de usurio      ?
Codigo de las epresas ?