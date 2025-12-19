<%-- 
    Document   : ControlMaestroMaquinas
    Created on : 09-jul-2019, 10:18:03
    Author     : Desarrollador
--%>

<%@page import="co.linxsi.modelo.maestro.operaciones.Operaciones_DTO"%>
<%@page import="co.linxsi.modelo.maestro.maquinas.Maquinas_DTO"%>
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
        <div class="h5 titulo" class = "position-fixed" >MAESTRO MAQUINAS</div> 
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
                    <span class="input-group-text">PROCESO</span>
                </div>
                <select id ="listaOperaciones" onchange="focusNombreMaquina()" class="custom-select col-md-4">
                    <option selected>SELECCIONE PROCESO</option>
                    <%ArrayList<Operaciones_DTO> lista = (ArrayList<Operaciones_DTO>) request.getAttribute("listaOperaciones");
                        for (Operaciones_DTO t : lista) {
                    %>
                    <option value="<%=t.getSk_operacion()%>" ><%=t.getNombre()%></option>
                    <% }%>
                </select>
                <div class="input-group-prepend">
                    <span class="input-group-text">MAQUINA  </span>
                </div>
                <input class="form-control input-group-append col-md-6" type="text" id ="bodega" title="Escriba el nombre de la maquina" placeholder="Nombre de la nueva Maquina" onkeypress="transferFocus(event, 'tMontaje')" >
                <div class="input-group-prepend">
                    <span class="input-group-text">VR.ESTANDAR</
                        span>
                </div>
                <input class="form-control input-group-append col-md-1 text-md-center" type="text" id ="tMontaje" title="Vr Estandar"  onkeypress="validar(event)" placeholder="Vr.Estandar">
                <div class="input-group-append">
                    <button id ="botonCrearBodega" class="btn btn-success btn btn-primary btn-sm" type="button" onclick="crear()" title="Cree una Maquina al hacer click">CREAR</button>  
                </div>
            </div>
            <div class="input-group mb-3 input-group-sm">
                <input hidden="true" class="form-control input-group-append" type="text" id ="capInstalada" title="Capacidad Instalada" placeholder="Capacidad Instalada" onkeypress="transferFocus(event, 'velocidad')">
                <input hidden="true" class="form-control input-group-append" type="text" id ="velocidad" title="Velocidad Máxima" placeholder="Velocidad" onkeypress="validar(event)">
            </div>
            <hr>    
            <div id="detalle"></div>

            <hr>  
            <script src="resourcs/js/jquery.min.js"></script>
            <script src="resourcs/js/bootstrap.min.js"></script>
            <script src="resourcs/js/popper.min.js"></script>
            <script src="resourcs/js/maestros/maquinas/maquinas.js"></script>
            <script src="resourcs/js/fontawesome-all.min.js"></script>
            <script >

            </script>
            <!-- Modal -->
            <div class="modal fade" id="edita" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header tituloModal">
                            <h5 class="modal-title text-md-left ">EDITA MAQUINA</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>                                
                        </div>
                        <div class="modal-body container">

                            <div class="input-group mb-3 input-group-sm">
                                <select   class="form-control input-group-append"  id ="listaOperaciones2" onkeypress="focusNombreMaquina2()" onchange="focusNombreMaquina2()" title="Seleccione la operación"  class="custom-select">
                                    <option value="0" selected>SELECCIONE LA OPERACIÓN</option>
                                    <%  lista = (ArrayList<Operaciones_DTO>) request.getAttribute("listaOperaciones");
                                        for (Operaciones_DTO t : lista) {
                                    %>
                                    <option value="<%=t.getSk_operacion()%>" ><%=t.getNombre()%></option>
                                    <% }%>
                                </select>
                                <input   class="form-control input-group-append" type="hidden"  name="skbodegaactualiza" id="skbodegaactualiza" value="">
                                <input class="form-control input-group-append" type="text" id ="actualizabodega" onkeypress="transferFocus(event, 'tMontaje2')" title="Edite el nombre de la maquina" placeholder="Nombre Maquina" >
                            </div>

                            <div class="input-group mb-3 input-group-sm">
                                <input class="form-control input-group-append" type="text" id ="tMontaje2" title="Valor Estandar"  onkeypress="verificarEnterModal(event)" placeholder="Valor Estandar">
                                <input hidden ="true"  class="form-control input-group-append" type="text" id ="capInstalada2" title="Capacidad Instalada" placeholder="Capacidad Instalada" onkeypress="transferFocus(event, 'velocidad2')">
                            </div>
                            <div class="input-group mb-3 input-group-sm">
                                <input hidden ="true" class="form-control input-group-append" type="text" id ="velocidad2" title="Velocidad Máxima" placeholder="Velocidad" onkeypress="verificarEnterModal(event)">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn-danger" data-dismiss="modal"> Cerrar </button>
                            <button type="button" class="btn-success" onclick="ActualizarBodega()" data-dismiss="modal" > Guardar </button>
                        </div>
                    </div>
                </div>
            </div> 
        </div>

    </body>
</html>