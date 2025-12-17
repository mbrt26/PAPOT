<%-- 
    Document   : VistaReporteRetal
    Created on : 15-jul-2019, 10:18:03
    Author     : Edgar J. García L.
--%>
<%@page import="com.solucionesweb.losbeans.fachada.FachadaRegimenBean"%>
<%@page import="co.linxsi.modelo.maestro.local.Local_DTO"%>
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

    <body onload="getDatos()">
        <% Local_DTO datosEmpresa = (Local_DTO) request.getAttribute("datosSeleccion");
            ArrayList<FachadaTerceroBean> lista = (ArrayList<FachadaTerceroBean>) request.getAttribute("listaResponsabilidades");
System.out.println("datos"+datosEmpresa.getNombreLocal());       

        %>
        <div class="page-header">
            <div class="p-1 h5 titulo bg-success "><i class="fas fa-industry"></i> DATOS EMPRESA </div>
        </div>
        <form method="GET" enctype="multipart/form-data">
            <input type="hidden" name="nombrePaginaRequest" value="/vista/maestros/VistaMaestroLocal.jsp">

            <div>
                <a id="ir-arriba" class="ir-arriba" title="Volver arriba">
                    <span class="fa-stack">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-arrow-up fa-stack-1x fa-inverse"></i>
                    </span>
                </a>
            </div>

            <div>
                <a id="ir-abajo" class="ir-abajo" title="Ir Abajo">
                    <span class="fa-stack">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-arrow-down fa-stack-1x fa-inverse"></i>
                    </span>
                </a>
            </div>

            <ul class="nav nav-tabs ml-3 ">

                <li class="nav-item">
                    <a class="nav-link active " data-toggle="tab" href="#menu1">  <i class="fas fa-chart-pie"></i> GENERAL</a>
                </li>
                <!--li class="nav-item">
                    <a class="nav-link " data-toggle="tab" href="#home"> <i class="fas fa-list"></i> DETALLE</a>
                </li-->
            </ul>

            <div class="tab-pane container col-md-12  active" id="menu1"> 
                <div id="content-wrapper">
                    <div class="card card-primary card border-success">
                        <!--div class="card-header">
                          <h3 class="card-title">Quick Example</h3>
                        </div-->
                        <!-- /.card-header -->
                        <!-- form start -->
                       
                            <div class="card-body bg-light">
                                <div class="row">


                                    <div class="col-3">

                                        <div class="input-group">

                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-briefcase"></i></span>
                                            </div>

                                            <input id = "razonSocial"  name= "razonSocial" type="text" class="form-control form-control-sm"  value ="<%=datosEmpresa.getRazonSocial()%>" placeholder="Razon Social">
                                        </div>

                                    </div>
                                    <div class="col-3">

                                        <div class="input-group">

                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-building"></i></span>
                                            </div>

                                            <input id = "nombreLocal" name = "nombreLocal"   type="text" class="form-control form-control-sm" value ="<%=datosEmpresa.getNombreLocal()%>" placeholder="Nombre Local">
                                        </div>

                                    </div>
                                    <div class="col-2">

                                        <div class="input-group">

                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-id-card"></i></span>
                                            </div>

                                            <input id = "nit" name = "nit" type="text" class="form-control form-control-sm"  value ="<%=datosEmpresa.getNIT()%>" placeholder="NIT">
                                        </div>

                                    </div>
                                    <div class="col-4">

                                        <div class="input-group">

                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                            </div>

                                            <input id = "email" name = "email" type="email" class="form-control form-control-sm"  value ="<%=datosEmpresa.getEmail()%>" placeholder="Email">
                                        </div>

                                    </div>
                                </div>
                                <div class="row mt-2">

                                    <div class="col-5">

                                        <div class="input-group">

                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-map-marker"></i></span>
                                            </div>

                                            <input id="direccion" name="direccion" type="text" class="form-control form-control-sm"  value ="<%=datosEmpresa.getDireccion()%>" placeholder="Dirección Fiscal">
                                        </div>

                                    </div>

                                    <div class="col-3">

                                        <div class="input-group">

                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-map"></i></span>
                                            </div>

                                            <select id="idCiudad" name="idCiudad" title="Seleccione Ciudad - Departamento " class="custom-select custom-select-sm">
                                                <option value="0" selected>Seleccione Ciudad - Departamento </option>
                                                <% ArrayList<Local_DTO> listaCiudades = (ArrayList<Local_DTO>) request.getAttribute("listaCiudades");
                                                    for (Local_DTO ciudad : listaCiudades) {
                                                        if (Integer.parseInt(ciudad.getIdCiudad()) != 0) {
                                                            
                                                            if(ciudad.getIdCiudad().equals(datosEmpresa.getIdCiudad()) ){
                                                                
                                                            %>
                                                <option selected value="<%= ciudad.getIdCiudad()%>" ><%= ciudad.getCiudad() + " - " + ciudad.getDepartamento()%></option>
                                                <%      
                                                            }else{
                                                       
                                                %>
                                                <option value="<%= ciudad.getIdCiudad()%>" ><%= ciudad.getCiudad() + " - " + ciudad.getDepartamento()%></option>
                                                <% 
                                                    
                                                    }
                                                        }
                                                    }
                                                %>
                                            </select>
                                        </div>

                                    </div>
                                    <div class="col-2">

                                        <div class="input-group">

                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-phone"></i></span>
                                            </div>

                                            <input id="telefono" name="telefono" type="text" class="form-control form-control-sm"  value ="<%=datosEmpresa.getTelefono()%>" placeholder="Télefono">
                                        </div>

                                    </div>
                                    <div class="col-2">

                                        <div class="input-group ">

                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-fax"></i></span>
                                            </div>

                                            <input id="fax" name="fax" type="text" class="form-control form-control-sm" value ="<%=datosEmpresa.getFax()%>" placeholder="Fax">
                                        </div>

                                    </div>


                                </div>
                                <div class="row mt-2">

                                    <div class="col-6">

                                        <div class="input-group ">
                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-handshake"></i></span>
                                            </div>

                                            <select id="idRes1" name="idRes1" title="Seleccione la responsabilidad fiscal de su empresa" class="custom-select custom-select-sm">
                                              <option value="0" selected>Seleccione Responsabilidad Fiscal</option>
                                                <%
                                                    for (FachadaTerceroBean res : lista) {
                                                        if (res.getIdResponsabilidad() != null) {
                                                            if (datosEmpresa.getResFiscal1().equals(res.getIdResponsabilidad())) {
                                                %>
                                                <option selected value="<%= res.getIdResponsabilidad()%>" ><%= res.getIdResponsabilidad() + " - " + res.getNombreResponsabilidad()%></option>
                                                <%
                                                } else {

                                                %>
                                                <option value="<%= res.getIdResponsabilidad()%>" ><%= res.getIdResponsabilidad() + " - " + res.getNombreResponsabilidad()%></option>
                                                <% }
                                                        }
                                                    }
                                                %>
                                            </select>

                                        </div>

                                    </div>
                                    <div class="col-6">

                                        <div class="input-group ">
                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-handshake"></i></span>
                                            </div>

                                            <select id="idRes2"  name="idRes2" title="Seleccione la responsabilidad fiscal de su empresa" class="custom-select custom-select-sm">
                                                <option value="0" selected>Seleccione Responsabilidad Fiscal</option>
                                                <%
                                                    for (FachadaTerceroBean res : lista) {
                                                        if (res.getIdResponsabilidad() != null) {
                                                           if (datosEmpresa.getResFiscal2().equals(res.getIdResponsabilidad())) {
                                                %>
                                                <option selected value="<%= res.getIdResponsabilidad()%>" ><%= res.getIdResponsabilidad() + " - " + res.getNombreResponsabilidad()%></option>
                                                <%
                                                } else {

                                                %>
                                                <option value="<%= res.getIdResponsabilidad()%>" ><%= res.getIdResponsabilidad() + " - " + res.getNombreResponsabilidad()%></option>
                                                <% }
                                                        }
                                                    }
                                                %>
                                            </select>

                                        </div>

                                    </div>

                                </div>
                                <div class="row mt-2">

                                    <div class="col-6">

                                        <div class="input-group ">
                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-handshake"></i></span>
                                            </div>

                                            <select id="idRes3"  name="idRes3" title="Seleccione la responsabilidad fiscal de su empresa" class="custom-select custom-select-sm">
                                                <option value="0" selected>Seleccione Responsabilidad Fiscal</option>
                                                <%
                                                    for (FachadaTerceroBean res : lista) {
                                                        if (res.getIdResponsabilidad() != null) {
                                                       if (datosEmpresa.getResFiscal3().equals(res.getIdResponsabilidad())) {
                                                %>
                                                <option selected value="<%= res.getIdResponsabilidad()%>" ><%= res.getIdResponsabilidad() + " - " + res.getNombreResponsabilidad()%></option>
                                                <%
                                                } else {

                                                %>
                                                <option value="<%= res.getIdResponsabilidad()%>" ><%= res.getIdResponsabilidad() + " - " + res.getNombreResponsabilidad()%></option>
                                                <% }
                                                        }
                                                    }
                                                %>
                                            </select>

                                        </div>

                                    </div>
                                    <div class="col-6">

                                        <div class="input-group ">
                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-handshake"></i></span>
                                            </div>

                                            <select id="idRes4"  name="idRes4" title="Seleccione la responsabilidad fiscal de su empresa" class="custom-select custom-select-sm">
                                                <option value="0" selected>Seleccione Responsabilidad Fiscal</option>
                                                <%
                                                    for (FachadaTerceroBean res : lista) {
                                                        if (res.getIdResponsabilidad() != null) {
                                                           if (datosEmpresa.getResFiscal4().equals(res.getIdResponsabilidad())) {
                                                %>
                                                <option selected value="<%= res.getIdResponsabilidad()%>" ><%= res.getIdResponsabilidad() + " - " + res.getNombreResponsabilidad()%></option>
                                                <%
                                                } else {

                                                %>
                                                <option value="<%= res.getIdResponsabilidad()%>" ><%= res.getIdResponsabilidad() + " - " + res.getNombreResponsabilidad()%></option>
                                                <% }
                                                        }
                                                    }
                                                %>
                                            </select>

                                        </div>

                                    </div>

                                </div>

                                <div class="row mt-2">
                                    <div class="col-6">
                                        <div class="input-group ">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fas fa-table"></i></span>
                                            </div>
                                            <select id="idOperacion" name="idOperacion" title="Seleccione Tipo Operación" class="custom-select custom-select-sm">
                                                <option value="0" selected>Seleccione Tipo Operación</option>
                                                <%
                                                    ArrayList<FachadaTerceroBean> listaOperacion = (ArrayList<FachadaTerceroBean>) request.getAttribute("listaOperaciones");
                                                    for (FachadaTerceroBean op : listaOperacion) {
                                                        if (op.getIdOperacionFactura() > 0) {
                                                            if (Integer.parseInt(datosEmpresa.getTipoOperacion()) == (op.getIdOperacionFactura())) {
                                                %>
                                                <option selected value="<%= op.getIdOperacionFactura()%>"><%= op.getIdOperacionFactura() + " - " + op.getNombreOperacionFactura()%></option>
                                                <%
                                                } else {
                                                %>
                                                <option value="<%= op.getIdOperacionFactura()%>" ><%= op.getIdOperacionFactura() + " - " + op.getNombreOperacionFactura()%></option>
                                                <%   }
                                                        }
                                                    }
                                                %>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-6">

                                        <div class="input-group ">
                                            <div class="input-group-prepend">

                                                <span class="input-group-text"><i class="fas fa-hand-holding-usd"></i></span>
                                            </div>

                                            <select id="idRegimen" name="idRegimen" title="Seleccione el regimén de su empresa" class="custom-select custom-select-sm">
                                                <option value="0" selected>Seleccione Tipo Regimen</option>
                                                <%
                                                    List<Local_DTO> listaRegimen = (List<Local_DTO>) request.getAttribute("listaRegimenes");
                                                    for (Local_DTO regimen : listaRegimen) {
                                                        if (datosEmpresa.getIdRegimen() == regimen.getIdRegimen()) {
                                                %>

                                                <option selected value="<%= regimen.getIdRegimen()%>" ><%= regimen.getIdRegimen() + " - " + regimen.getNombreRegimen()%></option>
                                                <%
                                                } else {%>

                                                <option value="<%= regimen.getIdRegimen()%>" ><%= regimen.getIdRegimen() + " - " + regimen.getNombreRegimen()%></option>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-3">
                                </div>
                                <div class="col-3">
                                </div>
                            </div>
                            <!div class="row mt-2"-->    
                            <div class="form-group shadow-textarea mt-2 ml-3 mr-3">
                                <textarea class="form-control z-depth-1 " id="resolucion" name="resolucion" rows="2"  title="Escriba Resolucion Fiscal" placeholder="Resolución Fiscal"><%=datosEmpresa.getResolucion()%></textarea>
                            </div>
                            <div class="form-group shadow-textarea mt-2 ml-3 mr-3">
                                <textarea class="form-control z-depth-1 " id="resolucion2" name="resolucion2" rows="10" title="Escriba Resolucion Fiscal" placeholder="Resolución Fiscal"><%=datosEmpresa.getResolucion2()%></textarea>
                            </div>
                    </div>
                    <div class="card-footer bg-transparent mt-1 text-center">
                        <button type="submit" name ="xoption"  id="btnGuardar" value ="guardar" onclick="guardarDatos()" class="btn btn-success btn-sm col-2 text-center"> Guardar <span class="fa fa-save"></span></button>
                        <button hidden type="button"  id="btnSpinner" class="btn btn-success btn-sm col-2 text-center"><span class="spinner-border spinner-border-sm" id="spinner7" role="status" aria-hidden="true"></span> Procesando </button>
                    </div>
                   
                </div>
            </div>
            <!-- Sticky Footer -->
            <footer class="sticky-footer mb-3 footer"  >
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">

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
            <script src="resourcs/js/maestros/local/local.js"></script>
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


 </form>
   
    </body>

</html>