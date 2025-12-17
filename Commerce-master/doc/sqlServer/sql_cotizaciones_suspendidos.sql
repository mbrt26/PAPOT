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
WHERE [ESTADO] = 13

--- SUSPENDIDOS
SELECT [IDLOG]
      ,[idCliente]
      ,[IDUSUARIO]
      ,[STRIDSUCURSAL]
      ,[IDPERIODO]
      ,[FECHAVISITA]
      ,[IDESTADOVISITA]
      ,[ESTADO]
FROM [BDCommerce].[dbo].[tblAgendaLogVisitas]
WHERE [ESTADO] = 8 

--- Coloca ESTADO = 8, una orden en ESTADO = 1
UPDATE [BDCommerce].[dbo].[tblAgendaLogVisitas]
SET [ESTADO] = 8
WHERE idLog  = 2876