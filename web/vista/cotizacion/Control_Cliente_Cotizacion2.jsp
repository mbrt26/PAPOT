<%-- 
    Document   : ControlInventarioConsulta
    Created on : 15-jul-2019, 10:18:03
    Author     : Desarrollador
--%>
<%@page import="co.linxsi.modelo.cliente.cotizacion2.DAO_Cliente2"%>
<%@page import="co.linxsi.modelo.cliente.cotizacion.DAO_Dcto_Cot"%>
<%@page import="com.solucionesweb.losbeans.fachada.FachadaUsuarioBean"%>
<%@page import="com.solucionesweb.losbeans.negocio.UsuarioBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="co.linxsi.modelo.maestro.accesorios.Accesorios_DTO"%>
<%@page import="co.linxsi.modelo.maestro.accesorios.Accesorios_DAO"%>
<%@page import="co.linxsi.modelo.maestro.operaciones.Operaciones_DAO"%>
<%@page import="co.linxsi.modelo.maestro.operaciones.Proceso_Materia_DTO"%>
<%@page import="co.linxsi.modelo.maestro.operaciones.Proceso_Materia_DAO"%>
<%@page import="co.linxsi.modelo.cliente.cotizacion.DAO_Cliente"%>
<%@page import="java.util.List"%>
<%@page import="co.linxsi.modelo.cliente.cotizacion.DTO_Cliente"%>
<%@page import="co.linxsi.modelo.touch.retal.Touch_Retal_DAO"%>
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
            <div  class="h5 titulo bg-success" ><i class="fas fa-dollar-sign"></i> COTIZACION  FICHAS</div> </div>
        <div id ="tabs" class="container col-12">

            <div id ="alertas"></div>
            <ul class="nav nav-tabs" id="contenedor">
                <li hidden  class="nav-item" style="font-weight: bold" >
                    <a class="nav-link" data-toggle="tab" href="#COTIZACION"><i class="fas fa-user-shield"></i> COTIZACIÓN</a>
                </li>
                <li hidden class="nav-item" style="font-weight: bold">
                    <a class="nav-link" data-toggle="tab" href="#HISTORIAL">  <i class="fas fa-history"></i> HISTORIAL</a>

                </li>
                <li hidden class="nav-item" style="font-weight: bold" >
                    <a class="nav-link" data-toggle="tab" href="#CLIENTES"><i class="fas fa-user-friends"></i> CLIENTES</a>
                </li>

                <li hidden class="nav-item" style="font-weight: bold">
                    <a class="nav-link" data-toggle="tab" href="#REFERENCIAS"><i class="fas fa-boxes"></i> REFERENCIAS</a>

                </li>

                <li class="nav-item" style="font-weight: bold">
                    <a class="nav-link active" data-toggle="tab" href="#REPORTE">  <i class="fas fa-clipboard-list"></i> REPORTE</a>

                </li>
            </ul>
            <p>

            <p>

            <div  class="tab-content">

                <div hidden id="COTIZACION" class="container  col-12">  <div class="content-wrapper">
                        <form method="POST" action="GralControladorServlet">
                            <section class="content bg-light">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-12">



                                            <!-- Main content -->
                                            <div class="invoice p-3 mb-3">
                                                <!-- title row -->
                                                <div class="row">
                                                    <div class="col-12">
                                                        <h4>
                                                            <i class="fas fa-globe"></i> Plasticos Union S.A.S<%
                                                                String strDateFormat = "dd/MM/yyyy";
                                                                String strDateFormat2 = "yyyy-MM-dd";
                                                                SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                                                                SimpleDateFormat sdf2 = new SimpleDateFormat(strDateFormat2);

                                                            %>
                                                            <small class="float-right">  <%= sdf.format(new Date())%></small>
                                                        </h4>
                                                    </div>
                                                    <!-- /.col -->
                                                </div>
                                                <!-- info row -->
                                                <div class="row invoice-info">
                                                    <div class="col-sm-4 invoice-col">
                                                        De
                                                        <address>
                                                            <strong>Plasticos Union S.A.S</strong><br>
                                                            CRA 46 # 48 C SUR 92<br>
                                                            Envigado-Antioquia<br>
                                                            Telefono: PBX 4444664<br>
                                                            Correo: plasticosunion@plasticosunion.com
                                                        </address>
                                                    </div>
                                                    <!-- /.col -->
                                                    <div class="col-sm-4 invoice-col">

                                                        <button type="button" class="bg-transparent border-0"  name="xoption" onclick = "listClientsModal()" value="Editar" data-toggle="modal" data-target="#modal-sel-cliente">Para <i class="fas fa-search"></i></button>
                                                        <address id="detalleClienteCot"> </address>

                                                    </div>
                                                    <!-- /.col -->
                                                    <div class="col-sm-4 invoice-col">
                                                        <b>Cotizacion # <%= new DAO_Dcto_Cot().getMaxDcto()%> </b><br>
                                                        <br>
                                                        <b></b><br>
                                                        <b></b><br>
                                                        <b></b>
                                                    </div>
                                                    <!-- /.col -->
                                                </div>
                                                <!-- /.row -->

                                                <div id="accordion">
                                                    <div class="card">
                                                        <div class="card-header">
                                                            <a class="card-link" data-toggle="collapse" href="#collapseOne">
                                                                Encabezado
                                                            </a>
                                                        </div>
                                                        <div id="collapseOne" class="collapse"  >

                                                            <textarea name ="xEncabezado" class="form-control z-depth-1 " maxlength="300" id="areaEnc" name="areaEnc" rows="3" placeholder="">Cordial Saludo
Nos permitimos cotizar el siguiente empaque plástico esperando se adapten a sus necesidades y expectativas. </textarea>

                                                        </div>
                                                    </div>
                                                    <div class="card">
                                                        <div class="card-header">
                                                            <a class="collapsed card-link" data-toggle="collapse" href="#collapseTwo">
                                                                Detalle
                                                            </a>
                                                        </div>
                                                        <div id="collapseTwo" class="collapse show" >


                                                            <div class="row">
                                                                <div id ="detalleReferenciasCot" class="col-12 container-fluid">

                                                                </div>
                                                                <!-- /.col -->
                                                            </div>

                                                        </div>
                                                    </div>
                                                    <div class="card">
                                                        <div class="card-header">
                                                            <a class="collapsed card-link" data-toggle="collapse" href="#collapseThree">
                                                                Pie Pagina
                                                            </a>
                                                        </div>
                                                        <div id="collapseThree" class="collapse">

                                                            <textarea name ="xPiePagina" class="form-control z-depth-1 " maxlength="500" id="areaPie" rows="6" placeholder="">Precio antes de IVA. 
Tener en cuenta que la cantidad puede variar un +/- 10% a la cantidad solicitada.
Costo de fotopolímeros por cuenta del cliente.
Tiempo de entrega aproximadamente para materiales impresos es de 20 días.
Oferta sujeta a variaciones de las resinas e insumos en el mercado.
Cualquier inquietud con gusto será atendida
Cordialmente,</textarea>

                                                        </div>
                                                    </div>
                                                </div>



                                                <!-- /.row -->

                                                <div class="row">
                                                    <!-- accepted payments column -->
                                                    <div class="col-6">
                                                        <!--p class="lead">Payment Methods:</p>
                                                        <img src="../../dist/img/credit/visa.png" alt="Visa">
                                                        <img src="../../dist/img/credit/mastercard.png" alt="Mastercard">
                                                        <img src="../../dist/img/credit/american-express.png" alt="American Express">
                                                        <img src="../../dist/img/credit/paypal2.png" alt="Paypal"-->

                                                        <p class="text-muted well well-sm shadow-none" style="margin-top: 10px;">

                                                        </p>
                                                    </div>

                                                    <div class="col-12 mt-2">
                                                        <div class="input-group mb-2 input-group-sm">
                                                            <div class="input-group-prepend">
                                                                <span class="input-group-text" style="font-weight: bold">Firmante</span>
                                                            </div>

                                                            <select title="Cliente" class="custom-select-sm" name="listaFirmante" onchange="" id="listaFirmante">

                                                                <%
                                                                    UsuarioBean usuarioBean = new UsuarioBean();
                                                                    usuarioBean.setIdUsuario(5);
                                                                    usuarioBean.setIdLocalUsuario(1);
                                                                    usuarioBean.setEstado(1);
                                                                    Vector listaVector = usuarioBean.listaNivelOpcion("5");
                                                                    listaVector.remove(0);

                                                                    for (Object usuario : listaVector) {
                                                                        FachadaUsuarioBean xUsuario = (FachadaUsuarioBean) usuario;
                                                                %>
                                                                <option class="text-md-center" value ="<%=xUsuario.getIdUsuarioSf0()%>" ><%= xUsuario.getNombreUsuario()%></option>    
                                                                <%   }%>
                                                            </select>


                                                            <div class="input-group-prepend">
                                                                <span class="input-group-text" style="font-weight: bold">Cargo</span>
                                                            </div>
                                                            <input  class="form-control input-group-append col-md-2 text-md-center form-control"  id="xCargo" type="text" name ="xCargo" required autofocus="autofocus" onchange=""  placeholder="Escriba aqui el cargo" >

                                                        </div>
                                                        <!-- /.row -->

                                                        <!-- this row will not appear when printing -->
                                                        <div class="row no-print">
                                                            <div class="col-12">

                                                                <input type="hidden" name="nombrePaginaRequest" value="/potPermisoCotizacion.ctr">
                                                                <!--a href="invoice-print.html" target="_blank" class="btn btn-default"><i class="fas fa-print"></i> Print</a-->
                                                                <!--button type="button" class="btn btn-success float-right"><i class="far fa-credit-card"></i> Submit
                                                                    Payment
                                                                </button-->
                                                                <button type="submit" value="ImprimirPDF" name ="xoption" class="btn btn-outline-success  btn-sm " style="margin-right: 5px;">
                                                                    <i class="fa fa-file-pdf"></i> Generar PDF
                                                                </button>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /.invoice -->
                                                </div><!-- /.col -->
                                            </div><!-- /.row -->
                                        </div><!-- /.container-fluid -->
                                        </section>
                                        <!-- /.content -->
                                    </div>
                                    <!-- /.content-wrapper -->
                                    <footer class="main-footer no-print">
                                        <div class="float-right d-none d-sm-block">

                                        </div>
                                        <strong>&copy; 2021 <a href="http://www.plasticosunion.com">Plasticos Union</a></strong> 
                                        .
                                    </footer></div>
                        </form>

                        <div id="CLIENTES" class="container tab-pane   col-12">
                            <p>
                            <form method="POST" action="GralControladorServlet">
                                <input type="hidden" name="nombrePaginaRequest" value="">
                                <input hidden = "true" class="col-1 text-md-center col-md-2" style="font-weight:bold" readOnly="readOnly" id="campoNumeroOrden" name="campoNumeroOrden">

                                <div class="input-group mb-2 input-group-sm ">

                                    <div class="btn-group">
                                        <button type="button" data-toggle="modal" data-target="#modal-nuevo-cliente" id="botonNuevo" onclick="crear()" class="btn btn-success btn-sm">
                                            <span class="fa fa-plus-circle" ></span> Nuevo </button> 

                                    </div>
                                    <div class = "col-9">
                                    </div>
                                    <br>
                                </div>
                            </form>


                            <div class="card mb-2">
                                <div class="card-header">
                                    <i class="fas fa-user-friends"></i>
                                    Clientes Registrados 
                                </div>
                                <div class="card-body">
                                    <div id="detalleCliente" class="container-fluid">
                                    </div>

                                </div>

                                <div id="pieTablaGlobal" class="card-footer small text-muted"></div>
                            </div>



                            <br>
                        </div>  
                        <div id="REFERENCIAS" class="container tab-pane    col-12">



                            <form method="POST" action="GralControladorServlet">




                                <div class="input-group mb-2 input-group-sm mt-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" style="font-weight: bold">Cliente</span>
                                    </div>

                                    <div class="input-group-append">
                                        <select title="Cliente" class="custom-select-sm  " name="listaclientes" onchange="listRefs()" id="listaCliente2">
                                            <option class="text-md-center" value ="0" >Seleccione</option>    
                                            <%
                                                DAO_Cliente2 dao2 = new DAO_Cliente2();
                                                List<DTO_Cliente> listaClientes2 = dao2.getAll();
                                                for (DTO_Cliente cliente1 : listaClientes2) {
                                            %>
                                            <option class="text-md-center" value ="<%=cliente1.getNit() + "," + cliente1.getRazonSocial()%>" ><%= cliente1.getRazonSocial()%></option>    
                                            <%   }%>
                                        </select>
                                    </div>

                                    <div class="input-group-append">
                                        <button type="button" data-toggle="modal" data-toggle="modal" data-target="#modal-ref-cliente" id="botonNuevoRef" disabled = "true"  onclick="colocarCliente()" class="btn btn-success btn-sm">
                                            <span class="fa fa-plus-circle" ></span> Nuevo </button> 

                                    </div>
                                </div>                              
                            </form>

                            <div class="card mb-2 mt-3">
                                <div class="toast fade" data-autohide="true" data-delay="5000"  style="position: absolute; top: 0; right: 16px;">
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
                                <div class="card-header">
                                    <i class="fas fa-boxes"></i>
                                    Referencias Cliente 
                                </div>
                                <div class="card-body">
                                    <div id="detalleReferencia" class="container-fluid">
                                    </div>
                                </div>
                                <div id="pieTablaGlobal" class="card-footer small text-muted"></div>
                            </div>
                            <br>
                        </div>  
                        <div id="HISTORIAL" class="container tab-pane fade col-12">

                            <div class="panel panel-primary">
                                <div class="input-group mb-2 input-group-sm"> 
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" style="font-weight: bold">Cliente</span>
                                    </div>
                                    <select title="Cliente" class="custom-select-sm  " name="listaclientes" onchange="" id="listaclientes3">
                                        <option class="text-md-center" value ="0" >Todos</option>    
                                        <%

                                            for (DTO_Cliente cliente1 : listaClientes2) {
                                        %>
                                        <option class="text-md-center" value ="<%=cliente1.getNit() + "," + cliente1.getRazonSocial()%>" ><%= cliente1.getRazonSocial()%></option>    
                                        <%   }%>
                                    </select>

                                    <div class="input-group-prepend">
                                        <span class="input-group-text" style="font-weight: bold">Fecha Inicial</span>
                                    </div>

                                    <input  name = "fechaInicial" class="form-control input-group-append col-md-2 text-md-center form-control datetimepicker-input" data-date-format="dd/mm/yyyy"  id="datepicker" type="date" required autofocus="autofocus" onchange="consultarHistorico()" value ="<%= sdf2.format(new Date())%>" placeholder="Fecha Inicial" >

                                    <div class="input-group-prepend">
                                        <span class="input-group-text" style="font-weight:bold">Fecha Final</span>
                                    </div>
                                    <input class="form-control input-group-append col-md-2 text-md-center form-control datetimepicker-input" data-date-format="dd/mm/yyyy" type="date" required autofocus="autofocus"  value ="<%= sdf2.format(new Date())%>" onchange="consultarHistorico()" id="datepicker2"  title="" placeholder="Fecha Final">


                                    <div class="input-group-append">
                                        <button id ="botonBuscarHistorial" class="btn btn-success btn btn-primary btn-sm"  type="button" onclick="printHistorial()" title="Buscar historial"><span class="fa fa-search"></span> Buscar </button>  

                                    </div>
                                </div>

                                <div class="card">
                                    <div class="card-header">
                                        <i class="fas fa-history"></i>
                                        Historial

                                    </div>
                                    <form method="POST" action="GralControladorServlet">
                                        <input type="hidden" name="nombrePaginaRequest" value="/potPermisoCotizacion.ctr">
                                        <div class="card-body">
                                            <div class="container-fluid" id = "historialDctos"></div>
                                        </div>

                                    </form>
                                </div>

                            </div>
                        </div> 
                        <div id="REPORTE" class="container  tab-pane  active  col-12">
                            <form method="POST" action="GralControladorServlet">
                                <div class="panel panel-primary">
                                    <div class="input-group mb-2 input-group-sm"> 


                                        <div class="input-group-prepend">
                                            <span class="input-group-text" style="font-weight: bold">Cliente</span>
                                        </div>
                                        <select title="Cliente" class="custom-select-sm col-md-3" name="listaClientes4" onchange="" id="listaClientes4">
                                            <option class="text-md-center" value ="0" >Seleccione Cliente</option>    
                                            <%
                                                List<DTO_Cliente> listaClientes3 = dao2.getAllClientesHaveReferences();
                                                for (DTO_Cliente cliente1 : listaClientes3) {
                                            %>
                                            <option class="text-md-center" value ="<%=cliente1.getNit()%>" ><%= cliente1.getRazonSocial()%></option>    
                                            <%   }%>
                                        </select>

                                        <div class="input-group-prepend">
                                            <span class="input-group-text" >Consulta</span>
                                        </div>
                                        <select title="Cliente" class="form-control input-group-append col-2 custom-select-sm  " name="" onchange="desplegarMenus()" id="listaReportes">
                                            <option class="text-md-center" value ="0" >1 - Estandar</option>    
                                            <option class="text-md-center" value ="1" >2 - Variación de Materias Primas</option>    
                                            <option class="text-md-center" value ="2" >3 - Variacion de Divisas</option>    

                                        </select>


                                        <div hidden class="custom-control custom-checkbox col-md- ml-2 mr-2">
                                            <input type="checkbox" checked class="custom-control-input" id="checkOmitt"  name="checkAgrupar">
                                            <label class="custom-control-label" for="checkOmitt">Omitir Fichas Sin Pedidos</label>
                                        </div>
                                        <div class="input-group-append">
                                            <button id ="botonBuscarReporteMargenes" class="btn btn-outline-success  btn-sm"  type="button" onclick="evaluarOpcion()" title="Buscar margenes"><span class="fa fa-search"></span> Buscar </button>  
                                            <button  id ="btnSpinnerBusqueda" hidden class="btn btn-success btn btn-primary btn-sm"  type="button" title="Buscando"><span class="spinner-border spinner-border-sm" id="btnPesoSpinner" role="status" aria-hidden="true"></span></button>  

                                            <button  disabled type="submit" value="ImprimirPDFReporte" name ="xoption" class="btn btn-outline-success  btn-sm" style="margin-right: 5px;">
                                                <i  class="fa fa-file-pdf"></i> Generar PDF
                                            </button>
                                            <input type="hidden" name="nombrePaginaRequest" value="/potPermisoCotizacion.ctr">

                                        </div>

                                    </div>
                            </form>

                            <div class="card">
                                <div class="card-header">
                                    <i class="fas fa-clipboard-list"></i>
                                    Reporte Margenes

                                </div>
                                <!--form method="POST" action="GralControladorServlet">
                                    <input type="hidden" name="nombrePaginaRequest" value="/potPermisoCotizacion.ctr"-->
                                <div class="card-body">
                                    <div class="container-fluid" id = "reporteMargenes"></div>
                                </div>

                                <!--/form-->
                            </div>

                        </div>

                    </div>
                </div>
                <br>

            </div>
        </div>


        <!-- Modal -->
        <div class="modal fade bd-example-modal-lg" id="modal-nuevo-cliente" role="dialog"   aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-success">
                        <div class="modal-title " id="exampleModalLabel">
                            <h6 class="modal-title text-white text-md-center" >Crear Nuevo Cliente <i class="fa fa-user-friends"></i></h6>
                        </div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row border-0">
                                <div class="col-sm-4">
                                    <div class="custom-control custom-switch">
                                        <input type="checkbox" checked class="custom-control-input" id="customSwitch1" onchange="nuevoCliente()">
                                        <label class="custom-control-label" for="customSwitch1">Nuevo Cliente</label>
                                    </div>
                                </div>
                                <div class="col-sm-8">

                                </div>
                                <div class="col-sm-0">

                                </div>
                            </div>
                            <div class="row  m-2">

                                <div class="col-sm-12">
                                    <select title="Cliente" class="custom-select  col-md-12" name="listaclientes" onchange="seleccionarCliente()" id="listaCliente">
                                        <option class="text-md-center" value ="0" >Seleccione</option>    
                                        <%
                                            DAO_Cliente dao = new DAO_Cliente();
                                            List<DTO_Cliente> listaClientes = dao.getAllClients();
                                            for (DTO_Cliente cliente : listaClientes) {
                                        %>
                                        <option class="text-md-center" value ="<%=cliente.getNit() + "," + cliente.getRazonSocial() + "," + cliente.getContacto()%>" ><%= cliente.getRazonSocial()%></option>    
                                        <%   }%>
                                    </select>
                                </div>

                            </div>
                            <div class=" m-2">
                                <div class="form-group col-12">
                                    <label for="exampleInputEmail1">Nit/CC</label>
                                    <input type="text" class="form-control col-6" id="campoNit" placeholder="Ingrese el NIT/CC">
                                </div>

                                <div class="form-group col-md-12">
                                    <label for="exampleInputPassword1">Razón Social</label>
                                    <input type="text" class="form-control" id="campoRazonSocial" placeholder="Ingrese Razón Social">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="campoContacto">Contacto</label>
                                    <input type="text" class="form-control" id="campoContacto" placeholder="Ingrese Contacto Cliente">
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id = "botonRetirar" type="button" onclick="saveCliente()" class="btn btn-success btn-sm col-md-3" data-dismiss="modal"><i class="fa fa-save"></i> Guardar </button>

                        <button type="button" class="btn btn-danger btn-sm col-md-3" data-dismiss="modal"><i class="fa fa-window-close"></i> Cancelar </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal Edita Cliente-->
        <div class="modal fade bd-example-modal-lg" id="modal-act-cliente" role="dialog"   aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-success">
                        <div class="modal-title " id="exampleModalLabel">
                            <h6 class="modal-title text-white text-md-center" >Actualizar Cliente <i class="fa fa-user-friends"></i></h6>
                        </div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container">

                            <div class=" m-2">
                                <div class="form-group col-12">
                                    <label for="campoNit2">Nit/CC</label>
                                    <input disabled type="text" class="form-control col-6" id="campoNit2" placeholder="Ingrese el NIT/CC">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="campoRazonSocial2">Razón Social</label>
                                    <input type="text" class="form-control" id="campoRazonSocial2" placeholder="Ingrese Razón Social">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="campoContacto">Contacto</label>
                                    <input type="text" class="form-control" id="campoContacto2" placeholder="Ingrese Contacto Cliente">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id = "botonUpdate" type="button" onclick="updateCliente()" class="btn btn-success btn-sm col-md-3" data-dismiss="modal"><i class="fa fa-save"></i> Actualizar </button>

                        <button type="button" class="btn btn-danger btn-sm col-md-3" data-dismiss="modal"><i class="fa fa-window-close"></i>  Cancelar  </button>
                    </div>
                </div>          </div>
        </div>
        <!-- Modal -->
        <div class="modal fade bd-example-modal-lg" id="modal-ref-cliente" role="dialog"   aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header bg-success">
                        <div class="modal-title " id="exampleModalLabel">
                            <h6 class="modal-title text-white text-md-center" >Gestión Referencias <i class="fa fa-boxes"></i></h6>
                        </div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">


                        <div class="input-group mb-2 input-group-sm col-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text" >Cliente</span>
                            </div>   
                            <input class="form-control input-group-append col-12" type="text" disabled = "true" id ="nombreCliente2" title="" placeholder="">

                            <button  id ="btnBuscarSpinner" hidden ="true" class="btn btn-success btn btn-primary btn-sm"  type="button" title="Buscar"><span class="spinner-border spinner-border-sm" id="btnPesoSpinner" role="status" aria-hidden="true"></span></button> 
                            <input hidden ="true" class="form-control input-group-append col-3" type="text" disabled = "true" id ="idPlu" >

                        </div>
                        <div id ="listaRefHistorico"  class="input-group mb-2 input-group-sm col-12">

                        </div>
                        <div id ="listaRefHistoricoEspera" hidden ="true" class="input-group mb-2 input-group-sm col-12">


                            <div class="input-group-prepend">
                                <span class="input-group-text">Consultando Historico</span>
                            </div>


                            <div  class="input-group-append">
                                <button id ="btn" class="btn-success btn btn-primary btn-sm" aligment ="center" type="button" onclick="" title=""> <span class="spinner-border spinner-border-sm" id="spinner7" role="status" aria-hidden="true"></span></button>  
                            </div>
                        </div>
                        <div class="input-group mb-2 input-group-sm col-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Nombre</span>
                            </div>
                            <input class="form-control input-group-append col-12" type="text" id ="nombre" title="" placeholder="">
                        </div>
                        <div class="input-group mb-2 input-group-sm col-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Material</span>
                            </div>
                            <input class="form-control input-group-append col-12" type="text" id ="material" title="" placeholder="">
                        </div>

                        <div class="input-group mb-2 input-group-sm col-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Ancho</span>
                            </div>
                            <input class="form-control input-group-append col-2"  type="number" min="0" id ="ancho" style="text-align:center" title="" placeholder="">

                            <div class="input-group-prepend">
                                <span class="input-group-text">Largo</span>
                            </div>
                            <input class="form-control input-group-append col-2"  type="number" min="0" id ="largo" style="text-align:center" title="" placeholder="">

                            <div class="input-group-prepend">
                                <span class="input-group-text">Calibre</span>
                            </div>
                            <input class="form-control input-group-append col-4"  type="number" min="0"  step ="0.1" id ="calibre" style="text-align:center"  title="" placeholder="">


                            <div class="input-group-prepend">
                                <span class="input-group-text">P Millar</span>
                            </div>
                            <input class="form-control input-group-append col-4" type="number" min="0"id ="pesoMillar" onchange="calcularCantidad()" style="text-align:center" title="" placeholder="">
                            <button  id ="btnPesoSpinner2" hidden ="true" class="btn btn-success btn btn-primary btn-sm"  type="button" title="Buscando"><span class="spinner-border spinner-border-sm" id="btnPesoSpinner" role="status" aria-hidden="true"></span></button>  

                            <div class="input-group-prepend">
                                <span class="input-group-text">Ref</span>
                            </div>
                            <input class="form-control input-group-append col-4" type="text" id ="referencia" style="text-align:center" title="" placeholder="">


                        </div>
                        <div class="input-group mb-2 input-group-sm col-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Ficha</span>
                            </div>
                            <input class="form-control input-group-append col-2" min="0" style="text-align:center" type="text" id ="ficha" title="" placeholder="">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Batch (Kg)</span>
                            </div>
                            <input type ="number" class="form-control input-group-append col-2" onchange="colocarCantProceso()" style="text-align:center" value = "100" id ="bache" title="" placeholder="">
                            <div class="input-group-prepend">
                                <span class="input-group-text">% Retal</span>
                            </div>
                            <input type ="number" class="form-control input-group-append col-1" onchange="colocarCantProceso()" min="0" style="text-align:center" value = "3" max="100" min="0"  id ="retal" title="" placeholder="">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Cantidad Pedido</span>
                            </div>
                            <input type ="number" disabled class="form-control input-group-append col-3" min="0" value = "0" style="text-align:right"  id ="cantidadPedido" title="" placeholder="">

                        </div>
                        <div class="input-group mb-2 input-group-sm col-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Precio Ud/Kg</span>
                            </div>
                            <input type ="number" class="form-control input-group-append col-3"  onchange="calcularPrecioVentaMargen()" style="text-align:right" value = "100" id ="precio" title="" placeholder="">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Sub Precio Venta</span>
                            </div>
                            <input type ="text" class="form-control input-group-append col-3" disabled min="0" style="text-align:center" value = "0" id ="precioVenta" title="" placeholder="">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Costo Total</span>
                            </div>
                            <input type ="number" disabled class="form-control input-group-append col-2" min="0" value = "0" style="text-align:right"  id ="costoTotal" title="Costo Total = Costos Procesos + Costos Materias + Costos Flete" placeholder="">

                        </div>
                        <div class="input-group mb-2 input-group-sm col-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text">%Margen</span>
                            </div>
                            <input type ="number" class="form-control input-group-append col-2"  onchange="calcularPrecioVentaSinMargen()" value = "18" max="200" style="text-align:right" id ="margen" title="" placeholder="">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="font-weight: bold" >Precio Venta</span>
                            </div>
                            <input type ="number" class="form-control input-group-append col-2 text-dark text-right"  disabled   style="font-weight: bold"  value = "0" id ="precioVentaAcc" title="" placeholder="">
                        </div>

                        <div class="container">
                            <div class="row"> 
                                <div class="col-md-7">
                                    <div class="card col-md-12">
                                        <h6 class="card-header text-info text-center bg-transparent" ><i class="fas fa-server" ></i> Procesos</h6>
                                        <div class="input-group mb-2 input-group-sm col-12 mt-2 mb-1">

                                            <select title="Procesos" class="form-control input-group-append col-12 custom-select-sm  " onclick="colocarCosto()" name="listaProceso" onchange="colocarCosto()" id="listaProceso">
                                                <option class="text-md-center" value ="0" >Seleccione</option>    
                                                <%
                                                    dao2 = new DAO_Cliente2();
                                                    Operaciones_DAO operacionesDAO = new Operaciones_DAO();

                                                    for (Operaciones_DTO operacion : operacionesDAO.listaAllOrdered()) {
                                                %>
                                                <option class="text-md-center" value ="<%=operacion.getSk_operacion() + "," + operacion.getNombre() + "," + operacion.getCosto_retal()%>" ><%= operacion.getNombre()%></option>    
                                                <%   }%>
                                            </select>
                                            <input type ="number" disabled class="form-control input-group-append col-3"  style="text-align:right" value = "0"  min="0"  id ="costoProceso" title="" placeholder="">
                                            <input type ="number" class="form-control input-group-append col-2"  style="text-align:right" value = "100" max="" min="1"  id ="cantProceso" title="" placeholder="">
                                            <button id ="btnAgregarProceso" class="btn btn-success btn btn-primary btn-sm col-2"  disabled onclick="agregarProceso()" type="button" title="Agregar"><i class="fa fa-plus-circle"></i></button>  

                                        </div>
                                        <div id="detalleProcesos" class="container-fluid">
                                        </div>

                                        <div class="input-group mb-2 input-group-sm col-12">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">Costo</span>
                                            </div>
                                            <input type ="number"  disabled class="form-control input-group-append col-4" min="0" value = "0" style="text-align:right"  id ="costosProcesos" title="" placeholder="">

                                        </div>
                                    </div>
                                    <div class="card col-md-12 mt-2">
                                        <h6 class="card-header text-primary text-center bg-transparent" ><i class="fas fa-boxes" ></i> Materias Primas</h6>
                                        <div class="input-group mb-2 mt-2 input-group-sm col-12">

                                            <select title="Cliente" class="form-control input-group-append col-7 custom-select-sm  " name="listaMaterias" onchange="" id="listaMaterias">
                                                <option class="text-md-center" value ="0" >Seleccione</option>    
                                                <%
                                                    dao2 = new DAO_Cliente2();
                                                    Proceso_Materia_DAO daoMat = new Proceso_Materia_DAO();
                                                    List<Proceso_Materia_DTO> listDTOMat = daoMat.listAllMateriasOrdered();
                                                    for (Proceso_Materia_DTO materia : listDTOMat) {
                                                %>
                                                <option class="text-md-center" value ="<%=materia.getSk_operacion_materia() + "," + materia.getNombrePlu() + "," + materia.getCostoMateriaPrima()%>" ><%= materia.getNombrePlu()%></option>    
                                                <%   }%>
                                            </select>


                                            <input type ="number" class="form-control input-group-append col-3"  style="text-align:center" value = "1" max="1000"  min="0"  id ="cantMP" title="Ingrese la cantidad de la porción en Kg de MP">
                                            <button  id ="btnPesoSpinner3" hidden ="true" class="btn btn-success btn btn-primary btn-sm col-2"  type="button" title="Buscando"><span class="spinner-border spinner-border-sm" id="btnPesoSpinner" role="status" aria-hidden="true"></span></button>  
                                            <button  id ="btnAggMP"  class="btn btn-success btn btn-primary btn-sm col-2"  onclick="agregarMateria()" type="button" title="Agregar"><i class="fa fa-plus-circle"></i></button>  

                                        </div>

                                        <div id="detalleMaterias" class="container-fluid">
                                        </div>

                                        <!--/div-->
                                        <div class="input-group mb-2 input-group-sm col-12">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">Costo </span>
                                            </div>
                                            <input type ="text" disabled class="form-control input-group-append col-4" style="text-align:right" value="0" id ="costosMP" title="" placeholder="0">

                                        </div>
                                    </div>

                                </div>

                                <div class="col-md-5">
                                    <div class="card col-md-12 mb-2">
                                        <h6 class="card-header text-warning text-center bg-transparent" ><i class="fas fa-sticky-note" ></i> Accesorios</h6>

                                        <div class="input-group mb-2 input-group-sm col-12 mt-2">

                                            <select title="accesorios" class="form-control input-group-append col-6 custom-select-sm  " name="listaAccesorios" onchange="colocarCostoAcc()" id="listaAccesorios">
                                                <option class="text-md-center" value ="0" >Seleccione</option>    
                                                <%
                                                    Accesorios_DAO daoACC = new Accesorios_DAO();

                                                    for (Accesorios_DTO operacion : daoACC.listaAllOrdered()) {
                                                %>
                                                <option class="text-md-center" value ="<%=operacion.getSk_accesorio() + "," + operacion.getNombre() + "," + operacion.getPrecio()%>" ><%= operacion.getNombre()%></option>    
                                                <%   }%>
                                            </select>
                                            <input type ="number" class="form-control input-group-append col-4" style="text-align:right" value = "0" min="0"  id ="costoAccesorio" title="" placeholder="">

                                            <button  id ="btnAggAcc"  class="btn btn-success btn btn-primary btn-sm col-2"  onclick="agregarAccesorio()" type="button" title="Agregar"><i class="fa fa-plus-circle"></i></button>  

                                        </div>

                                        <div id="detalleAccesorios" class="container-fluid">
                                        </div>

                                        <div class="input-group mb-2 input-group-sm col-12">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">Costo</span>
                                            </div>
                                            <input type ="text" disabled class="form-control input-group-append col-5" style="text-align:right" id ="costosACC" value="0" title="" placeholder="0">
                                        </div>

                                    </div>
                                    <div class="card col-md-12 mb-2">
                                        <h6 class="card-header text-danger text-center bg-transparent" ><i class="fas fa-truck" ></i> Flete</h6>

                                        <div class="input-group mb-2 mt-2 input-group-sm col-12">

                                            <input type ="number" class="form-control input-group-append col-4" min="0" style="text-align:right" onchange="refrescarCostoFlete()" id ="flete" title="" placeholder="">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">Costo</span>
                                            </div>
                                            <input type ="text" disabled class="form-control input-group-append col-12" style="text-align:right" value="0" id ="costosFlete" title="" placeholder="0">
                                        </div>
                                    </div>
                                    <textarea class="form-control z-depth-1 " maxlength="280" id="observacion" rows="3" placeholder="Escriba alguna observacion aqui..."></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id = "botonUpdate" type="button" onclick="saveReferencia()" class="btn btn-success btn-sm col-md-2" data-dismiss="modal"><i class="fa fa-save"></i> Guardar </button-->

                            <button type="button" class="btn btn-danger btn-sm col-md-2" data-dismiss="modal"><i class="fa fa-window-close"></i>  Cancelar  </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade bd-example-modal-lg" id="modal-sel-cliente" role="dialog"   aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header bg-success">
                        <div class="modal-title " id="exampleModalLabel">
                            <h6 class="modal-title text-white text-md-center" >Seleccione Cliente <i class="fa fa-user-friends"></i></h6>
                        </div>

                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="input-group mb-2 input-group-sm col-12">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" >Cliente</span>
                                </div>   
                                <input class="form-control input-group-append col-12" type="text" onkeypress="printListaClientesModalEnter(event)"  id ="nombreEmpresa" title="Ingrese el nombre del cliente" placeholder="">
                                <button  hidden id ="btnBuscarSpinner" class="btn btn-success btn btn-primary btn-sm"  type="button" title="Buscar"><span class="spinner-border spinner-border-sm" id="btnPesoSpinner" role="status" aria-hidden="true"></span></button> 
                                <button  id ="btnbuscar" class="btn btn-success btn btn-primary btn-sm"  onclick="printListaClientesModal()" type="button" title="Buscar"><i class="fa fa-search"></i></button> 
                                <input hidden ="true" class="form-control input-group-append col-3" type="text" disabled = "true" id ="idPlu" >

                            </div>
                            <div class="container-fluid" id="tablaModalClientes"></div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id = "botonSelCliente" type="button" onclick="printSeleccion()" class="btn btn-success btn-sm col-md-2" data-dismiss="modal"><i class="far fa-check-circle text-white"></i> Aceptar </button>

                        <button type="button" class="btn btn-danger btn-sm col-md-2" data-dismiss="modal"><i class="fa fa-window-close"></i>  Cancelar  </button>
                    </div>
                </div>          </div>
        </div>

        <div class="modal fade bd-example-modal-lg" id="modal-sel-mat-prima" role="dialog"   aria-hidden="true">
            <div class="modal-dialog modal-lg">

                <div class="modal-content">
                    <div class="modal-header bg-success">
                        <div class="modal-title " id="exampleModalLabel">
                            <h6 class="modal-title text-white text-md-center" >Edite Materias Primas <i class="fa fa-user-friends"></i></h6>
                        </div>

                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div id="panelBusquedaMateria" class="input-group mb-2 input-group-sm col-12">

                            </div>
                            <form >

                                <div class="container-fluid" id="tablaModalMat">

                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id = "botonInsertarValores"  type="button" onclick="consultarReporteMargen()" class="btn btn-success btn-sm col-md-2" data-dismiss="modal"><i class="far fa-check-circle text-white"></i> Aceptar </button>
                    <button type="button" name ="xoption"  class="btn btn-danger btn-sm col-md-2" data-dismiss="modal"><i class="fa fa-window-close"></i>  Cancelar  </button>
                </div>
            </div>      
        </div>
    </div>



    <script src="resourcs/js/jquery.min.js"></script>
    <script src="resourcs/js/bootstrap.min.js"></script>
    <script src="resourcs/js/popper.min.js"></script>
    <script src="resourcs/js/cotizacion/cotizacion2.js"></script>
    <script src="resourcs/js/fontawesome-all.min.js"></script>  
    <script src="resourcs/js/bootstrap-datepicker.min.js"></script>



</script-->
</body>

</html>