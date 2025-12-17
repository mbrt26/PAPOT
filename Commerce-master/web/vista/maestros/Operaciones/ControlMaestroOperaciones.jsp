<%-- 
    Document   : ControlMaestroOperaciones
    Created on : 15-jul-2019, 10:18:03
    Author     : Desarrollador
--%>
<%@page import="co.linxsi.modelo.maestro.retales.Retales_DTO"%>
<%@page import="co.linxsi.modelo.maestro.unidades.Unidades_DTO"%>
<%@page import="co.linxsi.modelo.maestro.operaciones.Operaciones_DTO"%>
<%@page import="co.linxsi.modelo.maestro.paro_maquina.Paro_Maquina_DTO"%>
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
        <!-- Boton hacia arriba -->
        <div> <a class="ir-arriba"   title="Volver arriba">
                <span class="fa-stack">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-arrow-up fa-stack-1x fa-inverse"></i>
                </span>
            </a></div>
        <div class="h5 titulo" >MAESTRO PROCESOS</div> 
        <div id ="tabs" class="container col-12">
            <ul class="nav nav-tabs" id="contenedor">
                <li class="nav-item" style="font-weight: bold" >
                    <a class="nav-link active" data-toggle="tab" href="#PROCESOS">PROCESOS</a>
                </li>
                <li class="nav-item" style="font-weight: bold">
                    <a class="nav-link" data-toggle="tab" href="#PARO_MAQUINA">PARO MAQUINA</a>
                </li>
                <li class="nav-item" style="font-weight: bold">
                    <a class="nav-link" data-toggle="tab" href="#RETALES">RETALES</a>
                </li>
                <li class="nav-item" style="font-weight: bold">
                    <a class="nav-link" data-toggle="tab" href="#UNIDADES">VELOCIDADES</a>
                </li>
                <li class="nav-item" style="font-weight: bold">
                    <a class="nav-link" data-toggle="tab" href="#MATERIAS">MATERIAS PRIMAS</a>
                </li>
                <li class="nav-item" style="font-weight: bold">
                    <a class="nav-link" data-toggle="tab" href="#ACCESORIOS">ACCESORIOS</a>
                </li>
            </ul>
            <div class="tab-content">
                <hr>
                <div id="PROCESOS" class="container tab-pane active col-12">
                    <form>
                        <div class="input-group mb-3 input-group-sm">
                            <div class="input-group-prepend">
                                <span class="input-group-text">PROCESO</span>
                            </div>
                            <input class="form-control input-group-append col-md-6  " type="text" id ="bodega" title="Escriba el nombre de Nuevo Proceso" placeholder="Nombre Nuevo Proceso " onkeypress="focusNext('servicios', event)">
                            <input class="form-control input-group-append col-md-2  " type="number" min="0" id ="servicios" title="Costo Servicios Publicos" placeholder="Costo Servicios " onkeypress="focusNext('arriendo', event)" >
                            <input class="form-control input-group-append col-md-2" type="number" min="0" id ="arriendo" title="Costo Arriendo" placeholder="Costo Arriendo " onkeypress="focusNext('manoObra', event)">
                            <input class="form-control input-group-append col-md-2" type="number" min="0" id ="manoObra" title="Costo Mano de obra por KG" placeholder="Costo Mano de Obra " onkeypress="focusNext('CostoProceso', event)">
                            <input class="form-control input-group-append col-md-2" type="number" min="0" id ="CostoProceso" title="Costo Con Retal" placeholder="Costo Con Retal " onkeypress="validar(event)">
                            <select id ="listaConteo" class="custom-select col-md-2">
                                <option value="0" selected>AUTOMATICO</option>
                                <option value="1" >MANUAL</option>
                            </select>
                            <div class="input-group-append">
                                <button id ="botonCrearBodega" class="btn btn-success btn btn-primary btn-sm" type="button" onclick="creaBodega()" title="Cree una Operación al hacer click">CREAR</button>  
                            </div>
                        </div>
                    </form>
                    <div id="detalle"></div>
                    <!-- Modal -->
                    <div class="modal fade" id="edita" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header tituloModal">
                                    <h5 class="modal-title text-md-left ">EDITA PROCESO</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>                                
                                </div>
                                <div class="modal-body container">
                                    <table border="0" width="100%" class="table table-sm">
                                        <tr>
                                            <td align="center">
                                                <div class="input-group mb-3 input-group-sm">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">PROCESO</span>
                                                    </div>
                                                    <input type="hidden" name="skbodegaactualiza" id="skbodegaactualiza" value="" >
                                                    <input type="text" name="xNombreActualiza" class="form-control input-group-append col-md-10" id="actualizabodega" value="" onkeypress="focusNext('xServicio', event)" >
                                                </div>
                                                <div class="input-group mb-3 input-group-sm">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">SERV.</span>
                                                    </div>
                                                    <input type="number" name="xNombreActualiza" class="form-control input-group-append col-md-6" id="xServicio" value="" onkeypress="focusNext('xArriendo', event)" >

                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">ARR.</span>
                                                    </div>
                                                    <input type="number"  class="form-control input-group-append col-md-6" id="xArriendo" value="" onkeypress="focusNext('xManoObra', event)"  >

                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">M. OBRA</span>
                                                    </div>
                                                    <input type="number" class="form-control input-group-append col-md-6" id="xManoObra" value="" onkeypress="focusNext('xCostoRetalModal', event)" >


                                                </div>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td> 
                                                <div class="input-group mb-3 input-group-sm">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">CONTROL TIEMPO</span>
                                                    </div>
                                                    <select id ="listaConteo2" class="custom-select col-md-4">
                                                        <option value="0" selected>AUTOMATICO</option>
                                                        <option value="1" >MANUAL</option>
                                                    </select>
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">COSTO</span>
                                                    </div>
                                                    <input type="number" class="form-control input-group-append col-md-6" id="xCostoRetalModal" value="" onkeypress="" >
                                                </div>

                                                <div class="input-group mb-2 input-group-sm">

                                                </div>
                                            </td>

                                        </tr>

                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn-danger" data-dismiss="modal">Cerrar</button>
                                    <button type="button" class="btn-primary" onclick="ActualizarOperacion()" data-dismiss="modal" >Guardar</button>
                                </div>
                            </div>
                        </div>
                    </div> 

                </div>
                <div id="PARO_MAQUINA" class="container tab-pane fade col-12">
                    <form>
                        <div class="input-group mb-3 input-group-sm">
                            <div class="input-group-prepend">
                                <span class="input-group-text">PROCESO</span>
                            </div>
                            <select id ="listaProcesos1"  class="custom-select">
                                <option value="0" selected>SELECCIONE PROCESO</option>
                                <% ArrayList<Operaciones_DTO> lista = (ArrayList<Operaciones_DTO>) request.getAttribute("listaProceso");
                                    for (Operaciones_DTO t : lista) {
                                %>
                                <option value="<%=t.getSk_operacion()%>" ><%=t.getNombre_operacion()%></option>
                                <% }%>
                            </select>
                            <div class="input-group-prepend">
                                <span class="input-group-text">PARO MAQUINA</span>
                            </div>
                            <select id ="listaParos"  class="custom-select">
                                <option value="0" selected>SELECCIONE PARO MAQUINA</option>
                                <% ArrayList<Paro_Maquina_DTO> lista2 = (ArrayList<Paro_Maquina_DTO>) request.getAttribute("listaParo");
                                    for (Paro_Maquina_DTO t : lista2) {
                                %>
                                <option value="<%=t.getSk_paro_maquina()%>" ><%=t.getNombre_paro_maquina()%></option>
                                <% }%>
                            </select>
                            <div class="input-group-append">
                                <button id ="botonCrearParo" class="btn btn-success btn btn-primary btn-sm" onclick="crearParo()" type="button"  title="Cree una Operación al hacer click">CREAR</button>  
                            </div>
                        </div>
                        <div id = "detalleParo" ></div>
                    </form>
                    <div class="modal fade" id="editaParo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header tituloModal">
                                    <h5 class="modal-title text-md-left ">EDITAR PARO MAQUINA</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>                                
                                </div>

                                <div class="input-group mb-3 input-group-sm">
                                    <select id ="listaProcesos1"  class="custom-select">
                                        <option value="0" selected>SELECCIONE PROCESO</option>
                                        <%  lista = (ArrayList<Operaciones_DTO>) request.getAttribute("listaProceso");
                                            for (Operaciones_DTO t : lista) {
                                        %>
                                        <option value="<%=t.getSk_operacion()%>" ><%=t.getNombre_operacion()%></option>
                                        <% }%>
                                    </select>

                                    <select id ="listaParos"  class="custom-select">
                                        <option value="0" selected>SELECCIONE PARO MAQUINA</option>
                                        <%  lista2 = (ArrayList<Paro_Maquina_DTO>) request.getAttribute("listaParo");
                                            for (Paro_Maquina_DTO t : lista2) {
                                        %>
                                        <option value="<%=t.getSk_paro_maquina()%>" ><%=t.getNombre_paro_maquina()%></option>
                                        <% }%>
                                    </select>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn-danger" data-dismiss="modal">Cerrar</button>
                                    <button type="button" class="btn-primary" onclick="ActualizarOperacion()" data-dismiss="modal" >Guardar</button>
                                </div>
                            </div>
                        </div>
                    </div> 

                </div>
                <div id="RETALES" class="container tab-pane fade col-12">
                    <form>
                        <div class="input-group mb-3 input-group-sm">
                            <div class="input-group-prepend">
                                <span class="input-group-text">PROCESO</span>
                            </div>
                            <select id ="listaProcesos2" class="custom-select">
                                <option selected>SELECCIONE PROCESO</option>
                                <%
                                    for (Operaciones_DTO t : lista) {
                                %>
                                <option value="<%=t.getSk_operacion()%>" ><%=t.getNombre_operacion()%></option>
                                <% }%>
                            </select>
                            <div class="input-group-prepend">
                                <span class="input-group-text">RETAL</span>
                            </div>
                            <select id ="listaRetal"  class="custom-select">
                                <option selected>SELECCIONE RETAL</option>
                                <% ArrayList<Retales_DTO> lista4 = (ArrayList<Retales_DTO>) request.getAttribute("listaRetales");
                                    for (Retales_DTO t : lista4) {
                                %>
                                <option value="<%=t.getSk_retal()%>" ><%=t.getNombre_retal()%></option>
                                <% }%>
                            </select>
                            <div class="input-group-append">
                                <button id ="botonCrearRetal" class="btn btn-success btn btn-primary btn-sm" type="button" onclick="crearRetal()" title="Cree una Operación al hacer click">CREAR</button>  
                            </div>
                        </div>
                        <div id = "detalleRetal" ></div>
                    </form>
                </div>
                <div id="UNIDADES" class="container tab-pane fade  col-12">
                    <form >
                        <div class="input-group mb-3 input-group-sm">
                            <div class="input-group-prepend">
                                <span class="input-group-text">PROCESO</span>
                            </div>
                            <select id ="listaProcesos3" class="custom-select">
                                <option selected>SELECCIONE PROCESO</option>
                                <% for (Operaciones_DTO t : lista) {
                                %>
                                <option value="<%=t.getSk_operacion()%>" ><%=t.getNombre_operacion()%></option>
                                <% }%>
                            </select>
                            <div class="input-group-prepend">
                                <span class="input-group-text">UNIDAD VELOCIDAD</span>
                            </div>
                            <select id ="listaUnidad3"  class="custom-select">
                                <option selected value="0">SELECCIONE UNIDAD</option>
                                <% ArrayList<Unidades_DTO> lista3 = (ArrayList<Unidades_DTO>) request.getAttribute("listaUnidad");
                                    for (Unidades_DTO t : lista3) {
                                %>
                                <option value="<%=t.getSk_unidad()%>" ><%=t.getNombre_unidades()%></option>
                                <% }%>
                            </select>
                            <div class="input-group-append">
                                <button id ="botonCrearUnidad "class="btn btn-success btn btn-primary btn-sm" type="button" onclick="crearUnidad()" title="Cree una Operación al hacer click">CREAR</button>  
                            </div>
                        </div>
                        <div id = "detalleUnidad" ></div>
                    </form>
                </div>
                <div id="MATERIAS" class="container tab-pane fade  col-12">
                    <div class="input-group mb-3 input-group-sm col-12">
                        <div class="input-group-prepend">
                            <span class="input-group-text">NOMBRE</span>
                        </div>
                        <input class="form-control input-group-append col-11" type="text" id ="nombreMatPrima" title="Escriba el nombre de materia prima" placeholder="Nombre de la materia prima">
                        <div class="input-group-prepend">
                            <span class="input-group-text">COSTO</span>
                        </div>
                        <input class="form-control input-group-append col-2" type="text" id ="costoMatPrima" title="Escriba el costo de materia prima" placeholder="Costo">

                        <div  class="input-group-append">
                            <button id ="btnCrearMat" class="btn-success btn btn-primary btn-sm" type="button" onclick="crearMateriaPrima()" title="Cree una materia prima">CREAR</button>  
                        </div> 
                    </div>
                    <div id = "detalleMateria" ></div>
                </div>
                <div id="ACCESORIOS" class="container tab-pane fade  col-12">
                    <div class="input-group mb-3 input-group-sm col-12">
                        <div class="input-group-prepend">
                            <span class="input-group-text">NOMBRE</span>
                        </div>
                        <input class="form-control input-group-append col-11" type="text" id ="nombreMatPrima" title="Escriba el nombre del accesorio" placeholder="Escriba el nombre del accesorio">
                        <div class="input-group-prepend">
                            <span class="input-group-text">COSTO</span>
                        </div>
                        <input class="form-control input-group-append col-2" type="text" id ="costoMatPrima" title="Escriba el costo del accesorio" placeholder="Costo">

                        <div  class="input-group-append">
                            <button id ="btnCrearMat" class="btn-success btn btn-primary btn-sm" type="button" onclick="" title="Cree un accesorio">CREAR</button>  
                        </div> 
                    </div>
                    <div id = "detalleAccesorios2" ></div>
                </div-->
            </div>
            <hr>
        </div>

        <!--Modal Productos-->
                     <div class="modal fade" id="editaMateria" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header tituloModal">
                                    <h5 class="modal-title text-md-left ">EDITA MATERIA</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>                                
                                </div>
                                <div class="modal-body container">
                                    <table border="0" width="100%" class="table table-sm">
                                        <tr>
                                            <td align="center">
                                                <div class="input-group mb-3 input-group-sm">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">NOMBRE</span>
                                                    </div>
                                                    <input type="hidden" name="sk_materiax" id="sk_materia" value="" >
                                                    <input type="text" name="xNombreActualiza" class="form-control input-group-append col-md-10" id="nombreMateriax" value="" onkeypress="focusNext('costox', event)" >
                                                </div>
                                                <div class="input-group mb-3 input-group-sm">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">COSTO</span>
                                                    </div>
                                                    <input type="text" name="xCostoX" class="form-control input-group-append col-md-10" id="costoxA" value="" onkeypress="" >                                              
                                                </div>
                                            </td>
                                        </tr>                          

                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn-danger" data-dismiss="modal">Cerrar</button>
                                    <button type="button" class="btn-primary" onclick="actMateria()" data-dismiss="modal" >Guardar</button>
                                </div>
                            </div>
                        </div>
                    </div> 

        <div class="toast">
            <div class="toast-header">
                Toast Header
            </div>
            <div class="toast-body">
                Actualizado exitosamente
            </div>
        </div>
        <script src="resourcs/js/jquery.min.js"></script>
        <script src="resourcs/js/bootstrap.min.js"></script>
        <script src="resourcs/js/popper.min.js"></script>
        <script src="resourcs/js/maestros/operaciones/operaciones.js"></script>
        <script src="resourcs/js/fontawesome-all.min.js"></script>  
    </body>

</html>