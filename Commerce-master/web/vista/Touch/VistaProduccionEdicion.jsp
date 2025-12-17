<%-- 
    Document   : VistaReporteRetal
    Created on : 15-jul-2019, 10:18:03
    Author     : Edgar J. García L.
--%>
<%@page import="co.linxsi.modelo.maestro.accesorios.Accesorios_DTO"%>
<%@page import="co.linxsi.modelo.maestro.accesorios.Accesorios_DAO"%>
<%@page import="co.linxsi.modelo.maestro.operaciones.Proceso_Materia_DTO"%>
<%@page import="co.linxsi.modelo.maestro.operaciones.Proceso_Materia_DAO"%>
<%@page import="co.linxsi.modelo.cliente.cotizacion.DAO_Cliente"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="co.linxsi.modelo.maestro.operaciones.Operaciones_DAO"%>
<%@page import="co.linxsi.modelo.touch.retal.Touch_Retal_DTO"%>
<%@page import="java.util.List"%>
<%@page import="co.linxsi.modelo.touch.retal.Touch_Retal_DAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean"%>
<%@page import="java.util.Vector"%>
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
        <link href="resourcs/css/bootstrap-datepicker.min.css" rel="stylesheet" />

    </head>

    <body onload="">

        <div class="page-header">
            <div class="p-1 h5 titulo bg-success "><i class="fas fa-table"></i> EDICION ORDENES </div>
        </div>
    </form>
    <form method="POST" id="formularioEditarOrdenes" action="GralControladorServlet">
        <input type="hidden" name="nombrePaginaRequest" value="vista/Touch/VistaEdicionOrdenes.jsp">
        <nav class="navbar navbar-expand-lg navbar-dark bg-light">

            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <div class="input-group mb-0 input-group-sm ">
                    <div class="input-group-prepend">
                        <span class="input-group-text bg-success text-white"><i class="far fa-calendar-alt"></i></span>   
                    </div>

                    <input title="Fecha Inicial" class="form-control input-group-append col-md-2 text-md-center form-control datetimepicker-input " data-date-format="dd/mm/yyyy" id="datepicker" name="datepicker" value="2020/12/12" type="text" autofocus="autofocus" placeholder="Fecha Inicial">
                    <div class="input-group-prepend">
                        <span class="input-group-text bg-success text-white"><i class="far fa-calendar-alt"></i></span>   
                    </div>
                    <input title="Fecha Final" class="form-control input-group-append col-md-2 text-md-center form-control datetimepicker-input" data-date-format="dd/mm/yyyy" name="datepicker2" type="text" autofocus="autofocus" id="datepicker2" title="" placeholder="Frequireecha Final">
                    <div class="input-group-prepend">
                        <span class="input-group-text bg-success  text-white"><i class="fas fa-user"></i></span>   

                    </div>
                    <select title="Operador" class="custom-select  col-md-10" name="listaOperadores" id="listaOperadores">
                        <option class="text-md-center" value ="0" >TODOS</option>    
                        <%
                            Touch_Retal_DAO dao = new Touch_Retal_DAO();
                            List<Touch_Retal_DTO> listaOperador = dao.getListOperatorAll();
                            for (Touch_Retal_DTO operador : listaOperador) {
                        %>
                        <option class="text-md-center" value ="<%=operador.getSk_Operario()%>" ><%= operador.getNombreOperario()%></option>    
                        <%   }%>
                    </select>
                    <div class="input-group-prepend">
                        <span class="input-group-text bg-success text-white"><i class="fas fa-industry"></i></span>   
                    </div>

                    <select id="listaProcesos" name="listaProcesos" title="Proceso"  class="custom-select col-md-3">
                        <option class="text-md-center" value ="0" >TODOS</option>    
                        <% ArrayList<Operaciones_DTO> lista = (ArrayList<Operaciones_DTO>) new Operaciones_DAO().listaAllEdicion();
                            for (Operaciones_DTO t : lista) {
                                if (t.getSk_operacion() == 2) {
                        %>
                        <option selected value="<%=t.getSk_operacion()%>" ><%=t.getNombre_operacion()%></option>
                        <%} else {

                        %><option  value="<%=t.getSk_operacion()%>" ><%=t.getNombre_operacion()%></option>
                        <%
                                }
                            }%>     
                    </select>
                </div>

            </div>
        </nav>
        <nav class="navbar navbar-expand-lg mb-2    navbar-dark bg-light">

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="input-group mb-2 input-group-sm">

                    <div hidden class="input-group-prepend">
                        <span class="input-group-text bg-success text-white"><i class="fas fa-space-shuttle"></i></span>   
                    </div>
                    <select hidden="" class="custom-select  col-3" title ="Maquina" name="listaMaquinas" id="listaMaquinas">

                    </select>
                    <div class="input-group-prepend">
                        <span class="input-group-text bg-success text-white"><i class="fas fa-file-alt"></i></span>   
                    </div>
                    <input class="form-control input-group-append col-md-2 text-md-center" title="Numero Orden-item" type="search" min="1" id="ordenBusqueda" name="ordenBusqueda" onkeypress="" title="Escriba nombre de la orden a buscar puede incluir el item despues de un signo de -" value ="" placeholder="Orden Trabajo-Item">
                    <div class="input-group-append">
                        <button type="button" id="botonBuscarTraslado" onclick="printListHistory();" class="btn btn-outline-success btn-sm ">
                            <i class="fa fa-search-plus" ></i> Buscar </button>

                        <button id="botonBuscarSpinner" hidden="true" class="btn btn-outline-success  btn-sm" type="button" title="Buscando">
                            <span class="spinner-border spinner-border-sm" id="spinner7" role="status" aria-hidden="true"></span> Buscando...</button>
                    </div>

                </div>
        </nav>
    </form>


    <!-- Boton hacia arriba -->
    <div>
        <a id="ir-arriba" class="ir-arriba" title="Volver arriba">
            <span class="fa-stack">
                <i class="fa fa-circle fa-stack-2x"></i>
                <i class="fa fa-arrow-up fa-stack-1x fa-inverse"></i>
            </span>
        </a>
    </div>
    <!-- Boton hacia arriba -->
    <div>
        <a id="ir-abajo" class="ir-abajo" title="Ir Abajo">
            <span class="fa-stack">
                <i class="fa fa-circle fa-stack-2x"></i>
                <i class="fa fa-arrow-down fa-stack-1x fa-inverse"></i>
            </span>
        </a>
    </div>

    <ul class="nav nav-tabs ml-3">


        <li class="nav-item">
            <a class="nav-link " data-toggle="tab" href="#home"> <i class="fas fa-list"></i> DETALLE</a>
        </li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content mt-2">
        <div class="toast fade" data-autohide="true" data-delay="5000"  style="position: absolute; top: 80px; right:10px;">
            <div class="toast-header bg-success text-white">

                <strong class="mr-auto">Notificación</strong>
                <small></small>
                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body text-success ">
                <h6 class="alert-heading">¡Registro Actualizado Exitosamente!  <i class="far fa-check-circle text-success"></i></h6>
            </div>
        </div>
        <div class="tab-pane container fade col-md-12 mt-1" id="home">   

            <div id="content-wrapper">

                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fas fa-table"></i>
                        Retales Reportados </div>
                    <div class="card-body">
                        <div id="historial" class="container-fluid"></div>
                    </div>
                    <div id="pieTablaGlobal" class="card-footer small text-muted"></div>
                </div>
            </div>

        </div>
        <div class="tab-pane container col-md-12  active" id="menu1"> 

            <div id="content-wrapper">


                <div class="card mb-3">
                    <div class="card-header">
                        <div class="card-tools">
                        </div>
                        <i class="fas fa-table"></i>
                        Detalle Transacciones </div>
                    <div class="card-body">
                        <div id="historialGlobal"></div>
                    </div>
                    <div id="pieTablaDetalle" class="card-footer small text-muted"></div>
                </div>
            </div>

        </div>
    </div>

    <!-- Sticky Footer -->
    <footer class="sticky-footer mb-3 footer"  >
        <div class="container my-auto">
            <div class="copyright text-center my-auto">
                <h7></h7>
            </div>
        </div>
    </footer>

    <!-- Sticky Footer -->
    <footer class="sticky-footer mb-1">
        <div class="container my-auto">
            <div class="copyright text-center my-auto">
                <span>Copyright © Plasticos Union <%=  Integer.toString(Calendar.getInstance().get(Calendar.YEAR))%></span>
            </div>
        </div>
    </footer>
    <script src="resourcs/js/jquery.min.js"></script>
    <script src="resourcs/js/bootstrap.min.js"></script>
    <script src="resourcs/js/popper.min.js"></script>

    <script src="resourcs/js/touch/ControladorEdicionOrdenesProduccionRetal.js"></script>
    <script src="resourcs/js/fontawesome-all.min.js"></script>
    <script src="resourcs/js/bootstrap-datepicker.min.js"></script>
    <script src="resourcs/js/jbootstrap.bundle.min.js"></script>
    <script src="resourcs/js/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <script src="resourcs/js/Chart.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="resourcs/js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="resourcs/js/chart-area-demo.js"></script>

    <script type="text/javascript">
                            $('#datepicker').datepicker({
                                weekStart: 1,
                                daysOfWeekHighlighted: "6,0",
                                autoclose: true,
                                todayHighlight: true,
                                bottomBar: true,
                                trigger: "botonFechaInicio",
                            });
                            var date = new Date();

                            $('#datepicker').datepicker("setDate", new Date());
                            $('#datepicker2').datepicker({
                                weekStart: 1,
                                daysOfWeekHighlighted: "6,0",
                                autoclose: true,
                                todayHighlight: true,

                            });
                            $('#datepicker2').datepicker("setDate", new Date());
    </script>

    <div class="modal fade bd-example-modal-lg" id="modal-ref-cliente" role="dialog"   aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header bg-success">
                    <div class="modal-title " id="exampleModalLabel">
                        <h6 class="modal-title text-white text-md-center" >Editar Orden <i class="fa fa-edit"></i></h6>
                    </div>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="input-group mb-2 input-group-sm col-12">
                        <input hidden ="true" class="form-control input-group-append col-1" type="text" disabled = "true" id ="idOrden" >
                        <div class="input-group-prepend">
                            <span class="input-group-text">Numero Orden</span>
                        </div>
                        <input class="form-control input-group-append col-2 text-center"  disabled = "true"  type="text" id ="numeroOrden" title="" placeholder="">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Item</span>
                        </div>
                        <input class="form-control input-group-append col-1 text-center"  disabled = "true"  type="text" id ="item" title="" placeholder="">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Cliente</span>
                        </div>
                        <input class="form-control input-group-append col-9"  disabled = "true"  type="text" id ="cliente" title="" placeholder="">
                    </div>
                    <div class="input-group mb-2 input-group-sm col-12">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Ficha</span>
                        </div>
                        <input   disabled = "true" class="form-control input-group-append col-1" type="text" id="ficha"
                                 value="">
                        <div class="input-group-prepend"> <span class="input-group-text">Referencia</span></div>   
                        <input class="form-control input-group-append col-11"  disabled = "true"  type="text" id ="referencia" title="" placeholder="">
                    </div>
                    <div class="input-group mb-2 input-group-sm col-12">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Fecha Inicio</span>
                        </div>
                        <input class="form-control input-group-append col-6" type="datetime-local" id="fechaInicio">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Fecha Fin</span>
                        </div>
                        <input class="form-control input-group-append col-6" type="datetime-local" id="fechaFin">
                    </div>
                    <div id="grupoProduccion" class="input-group mb-2 input-group-sm col-12">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Cant. Finalizada</span>
                        </div>
                        <input class="form-control input-group-append col-4"  type="number" min="0" id ="cantFin" style="text-align:center" title="" placeholder="">

                        <div class="input-group-prepend">
                            <span class="input-group-text">Peso (Kg)</span>
                        </div>
                        <input class="form-control input-group-append col-4"  type="number" min="0" id ="pesoFin" style="text-align:center" title="" placeholder="">

                        <div class="input-group-prepend">
                            <span class="input-group-text">Tara(Kg)</span>
                        </div>
                        <input class="form-control input-group-append col-4"  type="number" min="0"   id ="tara" style="text-align:center"  title="" placeholder="">
                    </div>
                    <div  id="grupoRetal" class="input-group mb-2 input-group-sm col-12">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Retal (Kg)</span>
                        </div>
                        <input class="form-control input-group-append col-2" type="number" min="0"id ="retal" style="text-align:center" title="" placeholder="">

                        <select class="custom-select  col-md-9" name="listaRetal" id="listaRetal"></select>

                    </div>
                    <div id="grupoParo" class="input-group mb-2 input-group-sm col-12">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Paro (min)</span>
                        </div>
                        <input class="form-control input-group-append col-2"  type="number" min="0"   value="0" id ="paro" style="text-align:center"  title="" placeholder="">
                        <select title="ParoCausa" class="custom-select  col-md-10" name="listaParo" id="listaParo"></select>
                    </div>
                    <div class="input-group mb-2 input-group-sm col-12">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Operador</span>
                        </div>
                        <select title="Operador" class="custom-select  col-md-8" name="listaOperadoresModal" id="listaOperadores2">

                            <%
                                for (Touch_Retal_DTO operador : listaOperador) {
                            %>
                            <option class="text-md-center" value ="<%=operador.getSk_Operario()%>" ><%= operador.getNombreOperario()%></option>    
                            <%   }%>
                        </select>
                        <div hidden class="input-group-prepend">
                            <span class="input-group-text">Proceso</span>
                        </div>
                        <select hidden disabled id="listaProcesos2" name="listaProcesos2" onchange ="" title="Proceso" class="custom-select col-md-2">
                            <%
                                for (Operaciones_DTO t : lista) {
                            %>
                            %> <option  value="<%=t.getSk_operacion()%>" ><%=t.getNombre_operacion()%></option>
                            <%
                                }%>     
                        </select>
                        <div class="input-group-prepend">
                            <span class="input-group-text">Maq</span>
                        </div>
                        <select class="custom-select  col-4" title ="Maquina" name="listaMaquinas" id="listaMaquinas2">
                        </select>

                    </div>
                    <div class="input-group mb-2 input-group-sm col-12">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Fecha Producción</span>
                        </div>
                        <input class="form-control input-group-append col-6" type="datetime-local"  id="fechaProduccion">
                         <div class="input-group-prepend">
                            <span class="input-group-text">Turno</span>
                        </div>
                        <select  id="listaTurnos" name="listaTurnos" onchange ="" title="Turno"   class="custom-select col-md-6">
                            <option value="<%=0%>" >SELECCIONE</option>                                        
                            <option value="<%=1%>" >1- 06:00 am - 02:00 pm</option>
                            <option value="<%=2%>" >2- 02:00 pm - 09:00 pm</option>
                            <option value="<%=3%>" >3- 09:00 pm - 06:00 am</option>
                            <option value="<%=4%>" >4- 06:00 am - 06:00 pm</option>
                            <option value="<%=5%>" >5- 06:00 pm - 06:00 am</option>
                            <option value="<%=6%>" >6- 06:00 am - 04:00 pm</option>
                            <option value="<%=7%>" >7- 07:00 am - 05:00 pm</option>
                            <option value="<%=8%>" >8- 02:00 pm - 10:00 pm</option>
                            <option value="<%=9%>" >9-10:00 pm - 06:00 am</option>
                            <option value="<%=10%>" >10- 06:00 am - 03:00 pm</option>
                            <option value="<%=11%>" >11- 08:00 am - 04:00 pm</option>
                        </select>
                    </div>
                    <div class="container">
                        <textarea name ="xPiePagina" class="form-control z-depth-1 " id="observacion" maxlength="100" id="areaPie" rows="2"   placeholder="Observaciones"></textarea> 

                    </div>
                </div>
                <div class="modal-footer">
                    <button id = "botonUpdate1" type="button" onclick="save(true)" class="btn btn-success btn-sm col-md-2" ><i class="fa fa-save"></i> Guardar </button-->
                        <button hidden="true" id="botonUpdate2" type="button" onclick="save(false)" class="btn btn-success btn-sm col-md-2" ><i class="fa fa-save"></i> Guardar </button-->

                            <button type="button" class="btn btn-danger btn-sm col-md-2" data-dismiss="modal"><i class="fa fa-window-close"></i>  Cancelar  </button>
                            </div>
                            </div>
                            </div>
                            </div>
                            <div class="modal fade bd-example-modal-sm" id="modal-delete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header bg-danger">
                                            <div class="modal-title " id="exampleModalLabel">
                                                <h6 class="modal-title text-white text-md-center" >¿Desea retirar este elemento? <i class="fa fa-trash-alt"></i></h6>
                                            </div>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body"><h6 class="text-md-center">
                                                <input hidden id="itemM" value = "0">
                                                <input hidden id="idOrdenM" value = "0">
                                                <input hidden id="idOperacionM" value = "0">

                                            </h6>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary btn-sm col-md-3" data-dismiss="modal"><i class="fa fa-window-close"></i> Cancelar </button>
                                            <button id = "botonRetirar" type="button" onclick="deleteRecord()" class="btn btn-danger btn-sm col-md-3" data-dismiss="modal"><i class="fa fa-trash-alt"></i> Retirar </button>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            </body>

                            </html>
