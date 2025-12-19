<%-- 
    Document   : VistaReporteRetal
    Created on : 15-jul-2019, 10:18:03
    Author     : Edgar J. García L.
--%>
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

    <body onload="printListaMaquinasReporteUnaVez()">

        <div class="page-header">
            <div class="p-1 h5 titulo bg-success "><i class="fas fa-trash"></i> HISTORIAL RETAL </div>
        </div>
    </form>
    <form method="POST" id="formularioRetal" action="GralControladorServlet">
        <input type="hidden" name="nombrePaginaRequest" value="vista/Touch/VistaReporteRetal.jsp">
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

                </div>
            </div>
        </nav>
        <nav class="navbar navbar-expand-lg mb-2    navbar-dark bg-light">

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="input-group mb-2 input-group-sm">
                    <div class="input-group-prepend">
                        <span class="input-group-text bg-success text-white"><i class="fas fa-industry"></i></span>   
                    </div>

                    <select id="listaProcesos" name="listaProcesos" title="Proceso" onchange="printListaMaquinasReporte()" class="custom-select col-md-2">
                        <option class="text-md-center" value ="0" >TODOS</option>    

                        <% ArrayList<Operaciones_DTO> lista = (ArrayList<Operaciones_DTO>) new Operaciones_DAO().listaProcesosPlanta();
                            for (Operaciones_DTO t : lista) {
                        %>
                        <option value="<%=t.getSk_operacion()%>" ><%=t.getNombre_operacion()%></option>
                        <% }%>
                    </select>
                    <div class="input-group-prepend">
                        <span class="input-group-text bg-success text-white"><i class="fas fa-space-shuttle"></i></span>   
                    </div>
                    <select class="custom-select  col-2" title ="Maquina" name="listaMaquinas" id="listaMaquinas">

                    </select>
                    <div class="input-group-prepend">
                        <span class="input-group-text bg-success text-white"><i class="fas fa-file-alt"></i></span>   
                    </div>
                    <input class="form-control input-group-append col-md-2 text-md-center" title="Numero Orden" type="search" min="1" id="ordenBusqueda" name="ordenBusqueda" onkeypress="consultarHistoricoTeclado(event)" title="Escriba nombre de la orden a buscar" placeholder="Orden Trabajo">
                    <div class="input-group-append">

                        <!-- Default unchecked -->
                        <div class="custom-control custom-checkbox col-md- ml-2 mr-2">
                            <input type="checkbox" class="custom-control-input" id="checkAgrupar" value="true" name="checkAgrupar">
                            <label class="custom-control-label" for="checkAgrupar">Agrupar</label>
                        </div>

                        <div class="btn-group">

                            <button type="button" id="botonBuscarTraslado" onclick="consultarGlobal();" class="btn btn-outline-success btn-sm ">
                                <i class="fa fa-search-plus" ></i> Buscar </button>

                            <button id="botonBuscarSpinner" hidden="true" class="btn btn-outline-success  btn-sm" type="button" title="Buscando">
                                <span class="spinner-border spinner-border-sm" id="spinner7" role="status" aria-hidden="true"></span> Buscando...</button>
                            <button id="botonBuscarSpinner2" hidden="true" class="btn btn-outline-success  btn-sm" type="button" title="Generando Excel">
                                <span class="spinner-border spinner-border-sm" id="spinner7" role="status"  aria-hidden="true"></span> Generando...</button>

                            <button hidden="true"  id="botonExcelDetallado" name="xOption" value="excelDet" class="btn btn-outline-success btn btn-sm" type="submit" ></button>
                            <button hidden="true"  id="botonExcelGlobal" name="xOption" value="excelGlob" class="btn btn-outline-success btn btn-sm" type="submit"></button>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">
                                <a class="dropdown-item"  onclick="loadWaitingSpinnerGlobal()()" href="#">Global</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" onclick="loadWaitingSpinner()" title="Haga click para descargar" href="#">Detallado</a>
                            </div>
                            <a id="botonExcel" class="btn btn-outline-success btn btn-sm" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-file-excel"></i> Excel
                            </a>

                        </div>

                    </div>

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

    <!-- Nav tabs -->
    <ul class="nav nav-tabs ml-3">

        <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#menu1">  <i class="fas fa-chart-pie"></i> GLOBAL</a>
        </li>
        <li class="nav-item">
            <a class="nav-link " data-toggle="tab" href="#home"> <i class="fas fa-list"></i> DETALLE</a>
        </li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content mt-2">
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
                <div class="row">
                    <div class="col-lg-8">
                        <div class="card mb-2">
                            <div class="card-header">
                                <i class="fas fa-chart-bar"></i>
                                Retal por Maquina </div>
                            <div class="card-body">
                                <canvas id="myBarChart" width="100%" height="40%"></canvas>
                            </div>
                            <div id="pieBarra"  class="card-footer small text-muted"></div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="card mb-2">
                            <div class="card-header">
                                <i class="fas fa-chart-pie"></i>
                                Causas </div>
                            <div class="card-body">
                                <canvas id="myPieChart" width="100%" height="89.5%"></canvas>
                            </div>
                            <div id="piePie" class="card-footer small text-muted"></div>
                        </div>
                    </div>
                </div>

                <div class="card mb-3">
                    <div class="card-header">
                        <div class="card-tools">
                        </div>
                        <i class="fas fa-table"></i>
                        Retales Reportados </div>
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
    <script src="resourcs/js/touch/touch.js"></script>
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
                                    //  var primerDia = new Date(date.getFullYear(), date.getMonth(), 1);
                                    //  $('#datepicker').datepicker("setDate", primerDia);
                                    $('#datepicker').datepicker("setDate", new Date());
                                    $('#datepicker2').datepicker({
                                        weekStart: 1,
                                        daysOfWeekHighlighted: "6,0",
                                        autoclose: true,
                                        todayHighlight: true,

                                    });
                                    $('#datepicker2').datepicker("setDate", new Date());
    </script>

</body>

</html>