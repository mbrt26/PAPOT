<%-- 
    Document   : ControlMaestroBodega
    Created on : 09-jul-2019, 10:18:03
    Author     : Desarrollador
--%>

<%@page import="co.linxsi.modelo.maestro.bodega.BodegaDTO"%>
<%@page import="co.linxsi.modelo.maestro.operaciones.Operaciones_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="resourcs/css/bootstrap.min.css">
        <link href="resourcs/css/parametrosGenerales.css" rel="stylesheet" type="text/css">      
    </head>
    <body onload="cargarBodegas()" >
        <div  class="h5 titulo" >MAESTRO PARO MAQUINA</div> 
        <!-- Boton hacia arriba -->
        <div> <a class="ir-arriba"   title="Volver arriba">
                <span class="fa-stack">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-arrow-up fa-stack-1x fa-inverse"></i>
                </span>
            </a></div>

        <div class="container col-12">
            <div class="input-group mb-3 input-group-sm">
                <div class="input-group-prepend">
                    <span class="input-group-text">PARO MAQUINA</span>
                </div>
                <input class="form-control input-group-append" type="text" id ="bodega" title="Escriba el nombre Paro Maquina" placeholder="Nombre del nuevo Paro Maquina" onkeypress="validar(event)">

                <div class="input-group-append">
                    <button id ="botonCrearBodega" class="btn-success btn btn-primary btn-sm" type="button" onclick="creaBodega()" title="Cree un Causal al hacer click">CREAR</button>  
                </div>
            </div>
            <hr>
            <div id="detalle"></div>
            <hr>
            <script src="resourcs/js/jquery.min.js"></script>
            <script src="resourcs/js/bootstrap.min.js"></script>
            <script src="resourcs/js/popper.min.js"></script>
            <script src="resourcs/js/maestros/paro_maquina/paroMaquina.js"></script>
            <script src="resourcs/js/fontawesome-all.min.js"></script>
            <!-- Modal -->
            <div class="modal fade" id="edita" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header tituloModal">
                            <h5 class="modal-title text-md-left ">EDITA PARO MAQUINA</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>                                
                        </div>
                        <div class="modal-body container">
                            <table border="0" width="100%" class="table table-sm">
                                <thead class="thead-light">
                                    <tr>
                                        <th width="100%" class="text-md-center">CAUSAL</th>
                                    </tr>
                                    <tr>
                                        <td align="center">
                                            <input type="hidden" name="skbodegaactualiza" id="skbodegaactualiza" value="">
                                            <input type="text" name="xNombreActualiza" class="input-group col-10 text-uppercase" id="actualizabodega" value="" onkeypress="verificarEnterModal(event)">
                                        </td>

                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn-danger" data-dismiss="modal">Cerrar</button>
                            <button type="button" class="btn-primary" onclick="ActualizarBodega()" data-dismiss="modal" >Guardar</button>
                        </div>
                    </div>
                </div>
            </div> 
        </div> 
    </body>
</html>