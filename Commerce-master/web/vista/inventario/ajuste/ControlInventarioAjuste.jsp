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
    </head>
    <body onload="cargarBodegas()">
        <div> <a class="ir-arriba"   title="Volver arriba">
                <span class="fa-stack">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-arrow-up fa-stack-1x fa-inverse"></i>
                </span>
            </a></div>
        <form method="post" action="CargarInventario?action=subir" enctype="multipart/form-data">
            <input type="hidden" name="nombrePaginaRequest" value="">   
            <div  class="h5 titulo" >AJUSTE DE INVENTARIO</div> 
            <div class="container col-12">
                <hr>
                <div class="panel panel-primary">
                    <div class="header"><div class="panel-title" style="font-weight: bold"> </div>
                        <div class="panel-body">
                            <div class="input-group mb-3 input-group-sm">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" style="font-weight: bold">BODEGA</span>
                                </div>
                                <select class="custom-select  col-3" name="xBodega" id="xBodega" col="6" >
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
                                <select class="custom-select  col-2" name="xBodega" id="xBodega" col="6" >
                                    <option selected value="1">POSITIVO</option>    
                                    <option selected value="2">NEGATIVO</option>    
                                </select>
                                <div class="input-group-prepend">
                                    <span class="input-group-text" style="font-weight:bold">TERCERO</span>
                                </div>
                                <select id ="listaProveedores" name = "listaProveedores" >
                                    <option selected>SELECCIONE PROVEEDOR</option>
                                    <%  Vector listaProveedor = (Vector) request.getAttribute("listaProveedores");
                                        Iterator iterator = listaProveedor.iterator();
                                        while (iterator.hasNext()) {
                                            FachadaTerceroBean fachadaBean = (FachadaTerceroBean) iterator.next();
                                            {
                                    %>
                                    <option value="<%=fachadaBean.getIdTercero()%>"><%=fachadaBean.getNombreTercero()%></option>
                                    <%
                                            }
                                                                    }%>
                                </select>

                                <div class="input-group-prepend">
                                    <span class="input-group-text"  style="font-weight:bold">Nº</span>
                                </div>
                                <input class="col-1" readOnly="readOnly" id="idAjuste">
                            </div>
                            <hr>
                        </div>
                    </div>
                </div>
                <table border="0" width="100%" class="table table-sm table-striped">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">PLU</th>
                            <th scope="col">NOMBRE</th>
                            <th scope="col">EXISTENCIA</th>
                            <th scope="col">AJUSTAR</th>
                            <th scope="col">MONTO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row"></th>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <div class="input-group mb-3 input-group-sm">
                    <div class="input-group-prepend">
                        <button type="button" class="btn btn-success btn-sm">
                            <span class="fa fa-plus-circle" onclick="crearAjuste()"></span> Nuevo </button> 
                        <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#edit" >
                            <span class="fa fa-plus"></span> Agregar </button> 
                        <button type="button" class="btn btn-success btn-sm" >
                            <span class="fa fa-save"></span> Guardar </button> 
                        <button type="button" class="btn btn-success btn-sm" >
                            <span class="fas fa-print"></span> Imprimir </button>
                        <span class="input-group-text" style="font-weight: bold">SUB TOTAL</span>
                    </div>
                    <input class="col-2" name="xBodegaOrigen" id="xBodegaOrigen" col="6" >
                    <div class="input-group-prepend">
                        <span class="input-group-text" style="font-weight:bold">IVA</span>
                    </div>
                    <input class="col-1" name="xBodegaOrigen" id="xBodegaOrigen">
                    <div class="input-group-prepend">
                        <span class="input-group-text" style="font-weight:bold">TOTAL</span>
                    </div>
                    <input class="col-2" name="xBodegaOrigen" id="xBodegaOrigen">
                </div>
                <div id="detalle" ></div>
            </div>
            <div class="modal fade " id="edit"   tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document" >
                    <div class="modal-content">
                        <div class="modal-header tituloModal">
                            <h6 class="modal-title text-md-center ">AGREGAR PRODUCTO</h6>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>                                
                        </div>
                        <div id="detalleMateria"></div>
                        <div class="modal-body container">
                            <div class="input-group mb-3 input-group-sm">
                                <input class="form-control input-group-append col-md-6" type="text" autofocus="autofocus" id ="campoProductoModal" onkeypress="buscarProductoDentroModalTecla(event);" title="Escriba nombre del producto para buscar" placeholder="Busqueda" onkeypress="">
                                <div class="input-group-append">
                                    <button id ="botonCrearMateria" class="btn btn-success btn btn-primary btn-sm" onclick="buscarProductoDentroModal()" type="button" title="Buscar producto"><i class="fas fa-search"></i></button>                                  
                                </div>
                            </div>
                            <div id="detalleModal"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script src="resourcs/js/jquery.min.js"></script>
        <script src="resourcs/js/bootstrap.min.js"></script>
        <script src="resourcs/js/popper.min.js"></script>
        <script src="resourcs/js/inventario/ajusteInventario.js"></script>
        <script src="resourcs/js/fontawesome-all.min.js"></script>  
    </body>

</html>