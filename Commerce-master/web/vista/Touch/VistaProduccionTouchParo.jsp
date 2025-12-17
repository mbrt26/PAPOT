<%-- 
    Document   : ControlInventarioConsulta
    Created on : 15-jul-2019, 10:18:03
    Author     : Desarrollador
--%>
<%@page import="co.linxsi.modelo.maestro.maquinas.Maquinas_DTO"%>
<%@page import="co.linxsi.modelo.maestro.maquinas.Maquinas_DAO"%>
<%@page import="java.util.List"%>
<%@page import="co.linxsi.modelo.maestro.retales.Retales_DAO"%>
<%@page import="co.linxsi.modelo.maestro.operaciones.Operaciones_DAO"%>
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
    <body onload="cargar()">
        <style type="text/css">
            .error {color: #f00;}
        </style>
        <form method="POST" id="formularioParo" action="GralControladorServlet">
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
                <div  class="p-1 h5 titulo bg-success " ><i class="fas fa-light fa-stopwatch"></i> REGISTRAR PARO </div> 
            </div>
            <div id ="tabs" class="container col-12">

                <input type="hidden" name="nombrePaginaRequest" value="vista/Touch/VistaProduccionTouchParo.jsp">
                <div id="alerta"  hidden="true" class="alert fade alert-success alert-dismissible fade show" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="alert-heading">¡Paro Registrado Exitosamente!  <i class="far fa-check-circle"></i></h5>
                    <h7> Haga click <a onclick="recargarPagina()" name="xoption" value="recargar" type="button" class="alert-link">AQUI</a> si desea realizar otro registro.</h7>
                </div>
                <div id="alertaError"  hidden="true" class="alert fade alert-danger alert-dismissible fade show" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="alert-heading">¡Algo Salio Mal!  <i class="fas fa-times"></i></h5>
                    <h6 id="mensajeError"></h6>
                    <h7> Haga click <a onclick="recargarPagina()" name="xoption" value="recargar" type="button" class="alert-link">AQUI</a> para intentar de nuevo.</h7>
                </div>

                <div class="card card-body text-white badge-light mb-3 card border-primary mb-2">
                    <div class="input-group mb-3 input-group-sm">
                        <div class="input-group-append">
                            <button id ="botonVolver" name="xoption" value="volver" class="btn btn-success btn btn-primary btn-sm" type="submit" title="Volver Atras"><span class="fa fa-arrow-alt-circle-left"></span> Volver</button>  
                        </div>
                        <div class="input-group-prepend">
                            <span class="input-group-text">PROCESO</span>
                        </div>
                        <select id ="listaProcesos"  class="custom-select col-md-3" onchange="cargar()">
                            <% ArrayList<Operaciones_DTO> lista = (ArrayList<Operaciones_DTO>) new Operaciones_DAO().listaProcesosPlanta();
                                for (Operaciones_DTO t : lista) {
                            %>
                            <option value="<%=t.getSk_operacion()%>" ><%=t.getNombre_operacion()%></option>
                            <% }%>
                        </select>
                        <div class="input-group-prepend">
                            <span class="input-group-text">MAQUINA</span>
                        </div>
                        <select id ="listaMaquinas"  class="custom-select col-md-3">
                        </select>
                        <div class="input-group-prepend">
                            <span class="input-group-text">ORDEN</span>
                        </div>
                        <input class="form-control input-group-append col-md-6 text-md-center col-md-12 required number" id ="orden" onkeyup= "focusObservacion(event)" onkeypress="return pulsar(event)"  placeholder="Orden">
                    </div>
                                      <div id="panelCamposSellado" class="input-group mb-3 input-group-sm">

                        
                        <div class="input-group-prepend">
                            <span class="input-group-text">TURNO</span>
                        </div>
                        <select id="listaTurnos" class="custom-select col-md-6" required>
                            <option value="<%=0%>" disabled selected >Seleccione un turno</option>
                                                                 
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
                         <div class="input-group-prepend">
                            <span class="input-group-text">FECHA PRODUCCIÓN</span>
                        </div>
                        <input type="datetime-local" class="form-control input-group-append col-md-6 text-md-center col-md-12 " required pattern="\d{4}-\d{2}-\d{2}T\d{2}:\d{2}"  id="fechaProduccion" name="fechaProduccion" placeholder="Fecha Producción">

                    </div>  
                    <div class="input-group mb-3 input-group-sm">
                        <div class="card col-md-6">
                            <h6 class="card-header text-success text-center bg-transparent"><i class="fas fa-user" ></i>  OPERADOR</h6>
                            <div class="row">
                     

                                <div id="listaOperarios" class="container-fluid col-md-12 p-2">                       
                                </div>
                                <div class="col-md-12"></div>
                            </div>
                        </div>
                        <div class="card col-md-6">
                            <h6 class="card-header text-warning text-center bg-transparent" ><i class="fas fa-light fa-stopwatch" ></i> PARO</h6>
                            <div class="row">
                      

                                <div id="listaRetales" class="container-fluid col-md-12 p-2">                       
                                </div>
                                <div class="col-md-12"></div>
                            </div>

                        </div>
                    </div>
                        
                        
                </div>
                <div class="form-group shadow-textarea">
                    <textarea class="form-control z-depth-1" maxlength="99" id="ControlTextarea" rows="1" placeholder="Escriba alguna observacion aqui..."></textarea>
                </div>
                <div class="input-group-append ">
                    <div class=" input-group-append col-md-5"></div>
                    <div class="input-group-append col-md-2">

                        <button id ="guardarRetal" disabled="true" class="btn btn-success btn btn-sm text-center col-md-12" type="button" onclick="focusCantRetal()" title="Guardar">Guardar <span id="botonBusqueda" class="fa fa-save"></span></button>  
                        <button id ="botonSpinner"  hidden="true" class="btn btn-success btn btn-sm text-center col-md-12" type="button" title="Guardar">Procesando... <span class="spinner-border spinner-border-sm" id="spinner5" role="status" aria-hidden="true"></span></button>  
                    </div>

                </div>
                <br>
            </div>
        </form>
        <div class="modal fade bd-example-modal-sm" id="bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header bg-success">
                        <div class="modal-title " id="exampleModalLabel">
                            <h6 class="modal-title text-white text-md-center" >Confirme Minutos Paro <i class="fas fa-light fa-stopwatch"></i></h6>
                        </div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="pro">Proceso:</label>
                            <input id="procesoModal" disabled="true" type="text" class="form-control" id="pro">
                        </div>
                        <div class="form-group">
                            <label for="mach">Maquina:</label>
                            <input id="maquinaModal" disabled="true" type="text" class="form-control" id="mach">
                        </div>
                        <div class="form-group">
                            <label for="ot">Orden Trabajo:</label>
                                <input id="ordenModal" disabled="true" type="text" class="form-control" id="ot">
                        </div>
                        <div class="form-group">
                            <label for="ope">Operario:</label>
                            <input  id="operarioModal"  disabled="true"   type="text" class="form-control" id="ope">
                        </div>
                        <div class="form-group">
                            <label for="ret">Causa Paro:</label>
                            <input  id="retalModal" disabled="true" type="text" class="form-control" id="ret">
                        </div>
                        <div class="row">
                            <div class="col-md-4"></div> 
                            <div class="col-md-4">
                                <input class="form-control form-control-sm input-group-append input-group-sm text-md-center col-12" onkeyup ="confirmarRetalT(event)" onclick="confirmarRetalT(event)" type="number" min="1" id ="cantidadRetal" step="1" placeholder="Tiempo Min">
                            </div> 
                            <div class="col-md-4"></div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <input id="idRetalSelected" value=" " hidden="true">
                        <input id="nombreRetalSelected" value=" " hidden="true">
                        <input id="idOperadorSelected" hidden="true">
                        <input id="nombreOperadorSelected" hidden="true">
                        <button type="button" class="btn btn-danger btn-sm col-md-3" data-dismiss="modal"><i class="fa fa-window-close"></i> Cancelar </button>
                        <button id = "botonInsertar" disabled="true" onclick="miFuncion()" type="button" title="Guardar Tiempo de Paro" class="btn btn-success btn-sm col-md-3"><i class="far fa-check-circle"></i> Confirmar </button>
                    </div>
                </div>
            </div>
        </div>

        <script src="resourcs/js/jquery.min.js"></script>
        <script src="resourcs/js/bootstrap.min.js"></script>
        <script src="resourcs/js/popper.min.js"></script>
        <script src="resourcs/js/touch/Controller_Touch_Paro.js"></script>
        <script src="resourcs/js/fontawesome-all.min.js"></script>  
        <script src="resourcs/js/jquery.validate.min.js"></script>  
        <script src="resourcs/js/bootstrap-datepicker.min.js"></script>
    </body>

</html>