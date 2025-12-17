 DECLARE @strDatabase nVarchar(50)
 DECLARE @strCarpeta  nVarchar(500)
 
 --Seleccione el nombre de base de datos y su ubicación
 SET @strDatabase = N'BDCommerce'
 
 --Seleccione la ubicación donde se guardara la copia de seguridad
 SET @strCarpeta = 'C:\proyectoWeb\BD'
 
 
 DECLARE @tToday datetime
 SET     @tToday = GETDATE()
 
 DECLARE @strBackupName nVarchar(100)
 SET     @strBackupName =   @strDatabase + N' '
                          + STR(DATEPART(year, @tToday ), 4 , 0)
                          + REPLACE(STR(DATEPART(month, @tToday), 2, 0), N' ', N'0')
                          + REPLACE(STR(DATEPART(day, @tToday), 2, 0), N' ', N'0')
 --                         + REPLACE(STR(DATEPART(hour, @tToday), 2, 0), N' ', N'0')
 --                         + REPLACE(STR(DATEPART(minute, @tToday), 2, 0), N' ', N'0')
                          
DECLARE @strBackupFile nvarchar(600)
SET     @strBackupFile =   @strCarpeta + N'\'
                         + @strBackupName + N'.bak'                      
                         
BACKUP DATABASE @strDatabase
TO DISK =  @strBackupFile
WITH
NOFORMAT,
INIT,
SKIP,
NAME = @strBackupName     