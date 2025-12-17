---
COTIZACIONES estado=13

SELECT [IDLOG]
      ,[idCliente]
      ,[IDUSUARIO]
      ,[STRIDSUCURSAL]
      ,[IDPERIODO]
      ,[FECHAVISITA]
      ,[IDESTADOVISITA]
      ,[ESTADO]
FROM [BDCommerce].[dbo].[tblAgendaLogVisitas]
WHERE [ESTADO]=13