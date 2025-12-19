<%-- 
    Document   : ControlInventarioConsulta
    Created on : 15-jul-2019, 10:18:03
    Author     : Desarrollador
--%>
<%@page import="java.util.Iterator"%>
<%@page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean"%>
<%@page import="java.util.Vector"%>
<%@page import="co.linxsi.modelo.maestro.bodega.BodegaDTO"%>
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
        <link href="resourcs/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
    </head>
    <body onload="">
        <!-- Boton hacia arriba -->
        <div> <a id="ir-arriba" class="ir-arriba"   title="Volver arriba">
                <span class="fa-stack">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-arrow-up fa-stack-1x fa-inverse"></i>
                </span>
            </a></div>
        <!-- Boton hacia arriba -->
        <div> <a id="ir-abajo" class="ir-abajo"   title="Ir Abajo">
                <span class="fa-stack">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-arrow-down fa-stack-1x fa-inverse"></i>
                </span>
            </a></div>
        <div class="page-header">
            <div  class="h5 titulo bg-success" ><i class="fas fa-truck-moving"></i> TRASLADO DE INVENTARIO </div> </div>
        <div id ="tabs" class="container col-12">

            <div id ="alertas"></div>
            <ul class="nav nav-tabs" id="contenedor">
                <li class="nav-item" style="font-weight: bold" >
                    <a class="nav-link active" data-toggle="tab" href="#PRINCIPAL">PRINCIPAL</a>
                </li>
                <li class="nav-item" style="font-weight: bold">
                    <a class="nav-link" data-toggle="tab" href="#HISTORIAL">HISTORIAL</a>

                </li>
            </ul>
            <p>

            <p>
            <div class="tab-content">
                <div id="PRINCIPAL" class="container tab-pane   active  col-12">
                    <div class="card card-body text-white badge-light mb-3 card border-primary mb-3">
                        <div class="panel panel-primary">
                            <div class="header"><div class="panel-title" style="font-weight: bold"> </div>
                                <div class="panel-body">
                                    <div class="input-group mb-3 input-group-sm">

                                        <div hidden = "true" class="input-group-prepend">
                                            <span class="input-group-text" style="font-weight: bold">BODEGA</span>
                                        </div>
                                        <select hidden = "true" class="custom-select  col-3" name="xBodega" id="xBodega" col="6" >
                                            <option selected value="0">TODAS</option>    
                                            <%ArrayList<BodegaDTO> lista2 = (ArrayList<BodegaDTO>) request.getAttribute("listaBodega");
                                                if (lista2 != null) {
                                                    for (BodegaDTO bdto : lista2) {
                                            %>                                    
                                            <option value="<%=bdto.getSk_bodega()%>"><%=bdto.getNombre()%></option>                                    
                                            <%                                    }
                                                }%>
                                        </select>
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" style="font-weight: bold">TIPO</span>
                                        </div>
                                        <select disabled class="custom-select  col-3" name="xBodega" id="xBodega" col="6" >
                                            <option selected value="0">Traslado entre ordenes</option>    
                                            <option >Traslado entre Bodegas</option>    
                                        </select>

                                        <div class="input-group-prepend">
                                            <span class="input-group-text" style="font-weight: bold">ORIGEN</span>
                                        </div>
                                        <input class="form-control input-group-append col-md-2 text-md-center" type="text" required autofocus="autofocus" disabled="true" id ="ordenOrigen" value="" onkeypress="buscarOrden(event);" title="Escriba nombre de la orden origen" placeholder="Orden Origen">
                                        <div class="input-group-append">
                                            <button id ="botonBuscarOrden" class="btn btn-success btn btn-primary btn-sm"  disabled="true" type="button" onclick="buscarOrdenBoton()" title="Buscar Pedido"><span id="botonBusqueda" class="fa fa-search"></span><span class="spinner-border spinner-border-sm" id="spinner2" hidden = "true" role="status" aria-hidden="true"></span></button>  
                                        </div>
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" style="font-weight:bold">DESTINO</span>
                                        </div>
                                        <input class="form-control input-group-append col-md-2 text-md-center" type="text" required id ="ordenDestino" disabled="true" onkeypress="buscarOrden2(event)" title="Escriba nombre de la orden destino" placeholder="Orden Destino">
                                        <div class="input-group-append">
                                            <button id ="botonBuscarOrden2" class="btn btn-success btn btn-primary btn-sm"  disabled="true" type="button" onclick="buscarOrdenBoton2()" title="Buscar Pedido"><span class="fa fa-search"></span><span class="spinner-border spinner-border-sm" id="spinner3" hidden = "true" role="status" aria-hidden="true"></span></button>  
                                        </div>
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"  style="font-weight:bold">Nº</span>
                                        </div>
                                        <input class="form-control input-group-append col-md-2 text-md-center" style="font-weight:bold" readOnly="readOnly" id="campoNumeroDocumento">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <p>
                    <form method="POST" action="GralControladorServlet">
                        <input type="hidden" name="nombrePaginaRequest" value="/vista/inventario/traslado/ControlInventarioTraslado.jsp">
                        <input hidden = "true" class="col-1 text-md-center col-md-2" style="font-weight:bold" readOnly="readOnly" id="campoNumeroOrden" name="campoNumeroOrden">

                  <div class="input-group mb-3 input-group-sm ">
                            <div class = "col-4"></div>
                            <div class="btn-group">
                                <button type="button" id="botonNuevo" onclick="crear()" class="btn btn-success btn-sm">
                                    <span class="fa fa-plus-circle" ></span> Nuevo </button> 
                                <button id ="botonEjecutarTraslado" disabled = "true" type="button"  class="btn btn-success btn-sm" onclick ="ejecutarTraslado()" >
                                    <span id="icono" class="fa fa-truck"></span><span class="spinner-border spinner-border-sm" id="spinner" hidden = "true" role="status" aria-hidden="true"></span>  Trasladar  </button>
                                <button id ="botonImprimirPDFSpinner"  hidden ="true" class="btn btn-success  btn-sm" >
                                    <span class="spinner-border spinner-border-sm" id="spinner" role="status" aria-hidden="true"></span> Cargando...</button>
                                <button id ="botonImprimirPDF" onclick = "mostrarCarga()" type="submit" disabled = "true" value="ImprimirPDF" name ="xoption"  class="btn btn-success  btn-sm" >
                                    <span class="fas fa-print"></span> Imprimir </button>
                            </div>
                            <br>
                        </div>
                    </form>
                    <div class="form-group shadow-textarea  ">
                        <textarea class="form-control z-depth-1 " maxlength="99"  disabled="true" id="ControlTextarea" rows="1" placeholder="Escriba alguna observacion aqui..."></textarea>
                    </div>
                    <div class="input-group mb-3 input-group-sm">
                        <div class="card card-body  mb-3 bg-transparent col-md-6">
                            <div>
                                <button class="btn btn-success btn-sm col-md-4" type="button" style="font-weight:bold" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapseOne">
                                    <i class="fa fa-warehouse"></i>  ORDEN ORIGEN </button>
                                <br>
                                <br>
                            </div>
                            <div class="panel-group"  id="accordion">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <div class="input-group mb-3 input-group">
                                            <div class="input-group-prepend ">
                                                <span class="input-group-text " style="font-weight: bold"><i class="fas fa-id-card"></i></span>
                                            </div>
                                            <input class="col-1 text-md-left col-md-10" style="font-weight:bold" readOnly="readOnly" id="campoNombreProducto">

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="collapse1" class="panel-collapse collapse">
                                <div class="card-header bg-light card border-primary mb-3" id="headingOne"><div id = "detalle">
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="card card-body  mb-3 bg-transparent  mb-3  col-md-6">
                            <div>
                                <button class="btn btn-success btn-sm col-md-4 text-md-center" type="button" data-toggle="collapse" data-target="#collapse2" aria-expanded="true" aria-controls="collapseOne">
                                    <i class="fa fa-warehouse"></i><h7 class="card-title" style="font-weight: bold" > ORDEN DESTINO </h7></button>
                                <br>
                                <br>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="input-group mb-3 input-group">
                                        <div class="input-group-prepend ">
                                            <span class="input-group-text" style="font-weight: bold"><i class="fas fa-id-card"></i></span>
                                        </div>
                                        <input class="col-1 text-md-left col-md-10" value="" style="font-weight:bold" readOnly="readOnly" id="campoNombreProducto2">

                                    </div>
                                </div>
                            </div>
                            <div id="collapse2" class="panel-collapse collapse">
                                <div class="card-header bg-light card border-primary mb-3 col-md-12" id="headingTwo"> <div id = "detalle2"></div></div>
                            </div>
                        </div>
                    </div>

                    <br>
                </div>  
                <div id="HISTORIAL" class="container tab-pane fade col-12">

                    <div class="panel panel-primary">

                        <div class="input-group mb-3 input-group-sm">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="font-weight: bold">FECHA INICIAL:</span>
                            </div>

                            <input  class="form-control input-group-append col-md-2 text-md-center form-control datetimepicker-input" data-date-format="dd/mm/yyyy"  id="datepicker" type="text" required autofocus="autofocus" onchange="consultarHistorico()"  placeholder="Fecha Inicial" >
                            <div class="input-group-append">
                                <button  class="btn btn-success btn btn-primary btn-sm"  id ="botonFechaInicio" type="button"  title=""><span class="fa fa-calendar-alt"></span></button>  
                            </div>
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="font-weight:bold">FECHA FINAL:</span>
                            </div>
                            <input class="form-control input-group-append col-md-2 text-md-center form-control datetimepicker-input" data-date-format="dd/mm/yyyy" type="text" required autofocus="autofocus"  onchange="consultarHistorico()" id="datepicker2"  title="" placeholder="Fecha Final">
                            <div class="input-group-append">
                                <button id ="botonBuscarOrden" class="btn btn-success btn btn-primary btn-sm" type="button" onclick="buscarOrdenBoton()" title="Buscar Pedido"><span class="fa fa-calendar-alt"></span></button>  
                            </div>
                            <input class="form-control input-group-append col-md-2 text-md-center" type ="number" id ="ordenBusqueda" onkeypress="consultarHistoricoTeclado(event)" title="Escriba nombre de la orden a buscar" placeholder="Numero de Orden">
                            <select class="custom-select  col-2" name="xBodega" id="tipoDoc" >
                                <option class="text-md-center" value ="numeroOrden" > NUMERO ORDEN </option>    
                                <option class="text-md-center" value ="idBodegaOrigen"> ORDEN ORIGEN </option>    
                                <option class="text-md-center" value ="idBodegaDestino"> ORDEN DESTINO </option>  
                            </select>
                            <div class="input-group-append">
                                <button id ="botonBuscarTraslado" class="btn btn-success btn btn-primary btn-sm"  type="button" onclick="consultarHistorico()" title="Buscar traslado"><span class="fa fa-search"></span></button>  
                                <button id ="botonBuscarSpinner" hidden ="true" class="btn btn-success btn btn-primary btn-sm"  type="button" title="Buscando"><span class="spinner-border spinner-border-sm" id="spinner7" role="status" aria-hidden="true"></span></button>  
                            </div>
                        </div>
                        <form method="POST" action="GralControladorServlet">
                            <input type="hidden" name="nombrePaginaRequest" value="/vista/inventario/traslado/ControlInventarioTraslado.jsp">

                            <div class="card-header bg-light card border-primary mb-3 col-md-12" id="headingTwo"><div id = "historial"></div></div>
                        </form>
                    </div> 
                </div>
                <br>

            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header bg-success">
                        <div class="modal-title " id="exampleModalLabel ">
                            <h6 class="modal-title text-md-left text-white">¿Que cantidad desea agregar a destino? <span class="fa fa-truck-loading"></span></h6>
                        </div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="input-group mb-3 input-group-sm">
                            <input hidden="true" type="text" name="xPLU"  readonly="true" class="form-control input-group-append col-md-1"  id="xPLU"  >
                            <input hidden="true" type="text" name="xCampoBoton"  readonly="true" class="form-control input-group-append col-md-1"  id ="xCampoBoton"  >
                            <input hidden="true" type="text" name="xPLU"  readonly="true" class="form-control input-group-append col-md-1"  id="xIdOp"  >
                            <input hidden="true" type="text" name="xItem"  readonly="true" class="form-control input-group-append col-md-1"  id="xItem"  >
                            <input type="text" name="xNombreActualiza"  style="font-weight: bold" readonly="readOnly" class="input-group-append col-md-8"  id="nombreProducto"  >
                            <input hidden class="form-control input-group-append  col-md-2 text-md-center" style="font-weight: bold" type="number" autofocus="autofocus" id ="campoTara" onkeypress="moverEnter(event)" autocomplete="on" min="0" step="0.1" placeholder="TARA" >
                            <input class="form-control input-group-append  col-md-2 text-md-center" style="font-weight: bold" type="number" autofocus="autofocus" id ="campoCantidad" onkeypress="moverEnter(event)" autocomplete="on" min="0" step="0.1" title="Cantidad Trasladar" placeholder="CANT" >
                            <input class="form-control input-group-append  col-md-2 text-md-center" style="font-weight: bold" type="number"  id ="campoPeso" onkeypress="moverEnter(event)" autocomplete="on" min="0" step="0.1" title="Peso Trasladar" placeholder="PESO" >
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal"><i class="fa fa-window-close"></i> Cancelar </button>
                        <button id = "botonTrasladar" type="button" onclick="moverItem()" class="btn btn-success btn-sm" data-dismiss="modal"><i class="fa fa-truck"></i> Trasladar </button>
                    </div>
                </div>
            </div>
        </div>


        <!-- Modal -->
        <div class="modal fade bd-example-modal-sm" id="bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                            <input hidden = "true" id="itemB" value = "0">
                            <input hidden = "true" id="pluB" value = "0">
                            <input hidden = "true" id="operacionB" value = "0">
                                <input hidden = "true" id="botonBorrarCampo" value = "0">
                        </h6>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary btn-sm col-md-3" data-dismiss="modal"><i class="fa fa-window-close"></i> Cancelar </button>
                        <button id = "botonRetirar" type="button" onclick="borrarPLU()" class="btn btn-danger btn-sm col-md-3" data-dismiss="modal"><i class="fa fa-trash-alt"></i> Retirar </button>
                    </div>
                </div>
            </div>
        </div>


        <script src="resourcs/js/jquery.min.js"></script>
        <script src="resourcs/js/bootstrap.min.js"></script>
        <script src="resourcs/js/popper.min.js"></script>
        <script src="resourcs/js/inventario/trasladoInventario.js"></script>
        <script src="resourcs/js/fontawesome-all.min.js"></script>  
        <script src="resourcs/js/bootstrap-datepicker.min.js"></script>
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
                            var primerDia = new Date(date.getFullYear(), date.getMonth(), 1);
                                              $('#datepicker').datepicker("setDate", primerDia);
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