                 SELECT tmpPEN.idLocal,                                 
                        tmpPEN.idTipoOrden,                           
                        tmpPEN.idOrden,                               
                        tmpPEN.itemPadre,                             
                        tmpPEN.idCliente,                             
                        tmpPEN.numeroOrden,                           
                        tmpPEN.idFicha,                               
                        tmpPEN.fechaEntrega,                          
                        tmpPEN.referencia,                            
                        tmpPEN.nombreOperacion,                       
                        tmpPEN.referenciaCliente,                     
                        tmpPEN.nombreReferencia,                      
                        tmpPEN.nombreItem,                            
                        tmpPEN.nombreTercero,                         
                        tmpPEN.idOperacionAnterior,                   
                        tmpPEN.idOperacion,                           
                        CASE                                          
                        WHEN   tmpPEN.idOperacionAnterior = 1         
                                       THEN tmpPEN.pesoPedido         
                        ELSE   tmpPEN.pesoTerminadoAnterior           
                        END AS pesoTerminadoAnterior,                 
                        CASE                                          
                        WHEN   tmpPEN.idOperacionAnterior = 1         
                                   THEN tmpPEN.cantidadPedida         
                        ELSE   tmpPEN.cantidadTerminadaAnterior       
                        END AS cantidadTerminadaAnterior,             
                        tmpPEN.pesoTerminadoActual,                   
                        tmpPEN.cantidadTerminadaActual                
                 FROM (                                               
                 SELECT tmpPED.*,                                     
                 ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)   
                 FROM tblDctosOrdenesDetalle                          
                 WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     
                                                    tmpPED.idLocal    
                 AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     
                                                tmpPED.idTipoOrden    
                 AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     
                                                    tmpPED.idOrden    
                 AND   tblDctosOrdenesDetalle.itemPadre         =     
                                                  tmpPED.itemPadre    
                 AND   tblDctosOrdenesDetalle.idOperacion       =     
                                        tmpPED.idOperacionAnterior    
                 AND   tblDctosOrdenesDetalle.idPlu         = 209     
                 AND   tblDctosOrdenesDetalle.cantidad      < 0 )     
                                        AS pesoTerminadoAnterior,     
                 ( SELECT                                             
                     SUM(tblDctosOrdenesDetalle.cantidadTerminada)    
                 FROM tblDctosOrdenesDetalle                          
                 WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     
                                                    tmpPED.idLocal    
                 AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     
                                                tmpPED.idTipoOrden    
                 AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     
                                                    tmpPED.idOrden    
                 AND   tblDctosOrdenesDetalle.itemPadre         =     
                                                  tmpPED.itemPadre    
                 AND   tblDctosOrdenesDetalle.idOperacion       =     
                                        tmpPED.idOperacionAnterior    
                 AND   tblDctosOrdenesDetalle.idPlu         = 209     
                 AND   tblDctosOrdenesDetalle.cantidad      < 0 )     
                                     AS cantidadTerminadaAnterior,    
                 ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)   
                 FROM tblDctosOrdenesDetalle                          
                 WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     
                                                     tmpPED.idLocal   
                 AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     
                                                 tmpPED.idTipoOrden   
                 AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     
                                                     tmpPED.idOrden   
                 AND   tblDctosOrdenesDetalle.itemPadre         =     
                                                   tmpPED.itemPadre   
                 AND   tblDctosOrdenesDetalle.idOperacion       =     
                                                 tmpPED.idOperacion   
                 AND   tblDctosOrdenesDetalle.idPlu         = 209     
                 AND   tblDctosOrdenesDetalle.cantidad      < 0 )     
                                             AS pesoTerminadoActual,  
                 ( SELECT                                             
                      SUM(tblDctosOrdenesDetalle.cantidadTerminada)   
                 FROM tblDctosOrdenesDetalle                          
                 WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     
                                                     tmpPED.idLocal   
                 AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     
                                                 tmpPED.idTipoOrden   
                 AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     
                                                     tmpPED.idOrden   
                 AND   tblDctosOrdenesDetalle.itemPadre         =     
                                                   tmpPED.itemPadre   
                 AND   tblDctosOrdenesDetalle.idOperacion       =     
                                                 tmpPED.idOperacion   
                 AND   tblDctosOrdenesDetalle.idPlu         = 209     
                 AND   tblDctosOrdenesDetalle.cantidad      < 0 )     
                                        AS cantidadTerminadaActual    
                 FROM (                                               
                  SELECT tblDctosOrdenes.idLocal,                     
                         tblDctosOrdenes.idTipoOrden,                 
                         tblDctosOrdenes.idOrden,                     
                         tblDctosOrdenesDetalle.itemPadre,            
                         tblDctosOrdenes.idCliente,                   
                         tblDctosOrdenes.numeroOrden,                 
                         tblDctosOrdenes.idFicha,                     
                         tblDctosOrdenesDetalle.fechaEntrega,         
                         tblDctosOrdenesDetalle.cantidad              
                                           AS cantidadPedida,         
                         tblDctosOrdenesDetalle.pesoPedido,           
                         tmpFIC.referencia,                           
                         tmpFIC.nombreOperacion,                      
                         tmpFIC.referenciaCliente,                    
                         tmpFIC.nombreReferencia,                     
                         tmpFIC.nombreItem,                           
                         tblTerceros.nombreTercero,                   
                         (SELECT TOP 1 tblPlusFicha.idOperacion       
                         FROM   tblPlusFicha                          
                         INNER JOIN tblJobOperacion                   
                         ON tblPlusFicha.idOperacion =                
                                  tblJobOperacion.idOperacion         
                         WHERE   tblPlusFicha.idFicha =               
                                     tblDctosOrdenes.idFicha          
                         AND tblPlusFicha.idEscala    =  610          
                         AND tblPlusFicha.vrEscala    = 5  )                                
                                        AS idOperacionAnterior,       
                         tmpFIC.idOperacion                           
                   FROM   tblDctosOrdenes                             
                   INNER JOIN tblTerceros                             
                   ON  tblTerceros.idCliente =                        
                                       tblDctosOrdenes.idCliente      
                   INNER JOIN (                                       
                   SELECT tblPlusFicha.idFicha,                       
                          MAX(tblPlusFicha.referencia)                
                                        AS referencia,                
                          MAX(tblJobOperacion.nombreOperacion)        
                                           AS nombreOperacion,        
                          MAX(tblPlusFicha.referenciaCliente)         
                                         AS referenciaCliente,        
                          MAX(tblPlusFicha.nombreReferencia)          
                                          AS nombreReferencia,        
                          tblJobEscalaDetalle.nombreItem,             
                          tblPlusFicha.idOperacion                    
                   FROM   tblPlusFicha                                
                   INNER JOIN tblJobOperacion                         
                   ON tblPlusFicha.idOperacion      =                 
                                 tblJobOperacion.idOperacion          
                   INNER JOIN tblJobEscalaDetalle                     
                   ON tblPlusFicha.idEscala         =                 
                                tblJobEscalaDetalle.idEscala          
                   AND tblPlusFicha.vrEscala        =                 
                                    tblJobEscalaDetalle.item          
                   WHERE   tblPlusFicha.idOperacion = 5                
                   AND tblPlusFicha.idEscala                          
                                    IN (600, 710, 910, 1000)          
                   AND tblPlusFicha.vrEscala        =  15             
                   GROUP BY tblPlusFicha.idFicha,                     
                            tblPlusFicha.idOperacion,                 
                            tblJobEscalaDetalle.nombreItem)           
                                                  AS tmpFIC           
                   ON tblDctosOrdenes.idFicha  =                      
                                             tmpFIC.idFicha           
                   INNER JOIN tblDctosOrdenesDetalle                  
                   ON tblDctosOrdenesDetalle.idLocal     =            
                                          tblDctosOrdenes.idLocal     
                   AND   tblDctosOrdenesDetalle.idTipoOrden =         
                                      tblDctosOrdenes.idTipoOrden     
                   AND   tblDctosOrdenesDetalle.idOrden     =         
                                        tblDctosOrdenes.idOrden       
                   WHERE tblDctosOrdenes.idLocal            =    1     
                   AND   tblDctosOrdenes.idTipoOrden        =   59      
                  AND tblDctosOrdenesDetalle.idEstadoRefOriginal !=9  
                   AND   tblDctosOrdenes.numeroOrden        > 0       
                   AND   tblTerceros.idTipoTercero          = 1 )     
                                                      AS tmpPED )     
                                                      AS tmpPEN       
                  ORDER BY tmpPEN.fechaEntrega,                       
                           tmpPEN.numeroOrden ;
