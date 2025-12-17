<%-- 
    Document   : ControlInventarioConsulta
    Created on : 15-jul-2019, 10:18:03
    Author     : Desarrollador
--%>
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
    </head>
    <body onload="cargarBodegas()">
        <!-- Boton hacia arriba -->
        <div> <a class="ir-arriba"   title="Volver arriba">
                <span class="fa-stack">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-arrow-up fa-stack-1x fa-inverse"></i>
                </span>
            </a></div>
        <form method="post" action="CargarInventario?action=subir" enctype="multipart/form-data">
            <input type="hidden" name="nombrePaginaRequest" value="/vistas/inventario/carga/ControlCargaInventario.jsp">   
            <div  class="h5 titulo" >CARGA DE INVENTARIO</div> 
            <div class="container col-12">
                <div class="input-group mb-3 input-group-sm">
                    <div class="input-group-prepend">
                        <span class="input-group-text">BODEGA</span>
                    </div>
                    <select class="custom-select  col-3" name="xBodega" id="xBodega" col="6" >
                        <option selected value="0">TODAS</option>    
                        <%
                            ArrayList<BodegaDTO> lista2 = (ArrayList<BodegaDTO>) request.getAttribute("listaBodega");
                            if (lista2 != null) {
                                for (BodegaDTO bdto : lista2) {
                        %>                                    
                        <option value="<%=bdto.getSk_bodega()%>"><%=bdto.getNombre()%></option>                                    
                        <%}
                            }%>
                    </select>
                    <div class="input-group-append">
                        <button type="button" class="btn btn-dark btn-sm" onclick="crearFoto()">
                            <span class="fas fa-file"></span> Crear </button> 
                    </div>

                    <div class="input-group-append">
                        <a class="btn btn-primary btn-sm" href="CargarInventario?action=bajar">
                            <span class="fas fa-download"></span>Bajar</a> 
                    </div>
                    <div class="input-group-append">
                        <button type="button" class="btn btn-success btn-sm" onclick="subir()">
                            <span class="fas fa-search"></span>Buscar</button> 
                        <button type="submit" class="btn btn-light">
                            <span class="fas fa-upload"></span> Subir </button> 
                        <input  hidden="true" name="file" type="file" id="botonSubir">
                    </div>

                </div>
            </div>
            <hr>
            <div id="detalle" ></div>
        </form>
        <script src="resourcs/js/jquery.min.js"></script>
        <script src="resourcs/js/bootstrap.min.js"></script>
        <script src="resourcs/js/popper.min.js"></script>
        <script src="resourcs/js/inventario/cargaInventario.js"></script>
        <script src="resourcs/js/fontawesome-all.min.js"></script>  
    </body>

</html>