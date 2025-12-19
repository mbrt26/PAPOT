<html>
    <%@ page import="co.linxsi.modelo.maestro.unidades.Unidades_DAO" %>
    <%@ page import="co.linxsi.modelo.maestro.unidades.Unidades_DTO" %>
    <%@ page import="java.util.ArrayList" %>


    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%
                //
               final String xEstadoActivo = "1";
               final String xIdNivelVendedor = "5";
               final String xIdOperacionPedido = "1";
               String xOpcionSelectedSI = "";
               String xOpcionSelectedNO = "";
               final String xIdLineaFabricacion = "1";
               final String idplu ="0";
               final String xIdCategoriaProcesosExternos = "22";
               final String xIdCategoriaAccesorios = "23";
    %>
    <%@ taglib uri="/WEB-INF/tlds/listaUsuarioLocalOpcion" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacionFicha" prefix="lsb" %>
    <%@ taglib uri="/WEB-INF/tlds/listaEscala" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaPluCategoriaOpcion" prefix="lsa" %>

    <jsp:useBean id="fachadaDctoOrdenDetalleBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />

    <jsp:useBean id="fachadaAgendaLogVisitaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />



    <head>
        <title>Modificar Cantidad</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
    </head>
    <%
                String xIdLog = request.getParameter("idLog");
                String xItem = request.getParameter("item");
              
            Unidades_DAO unidades = new Unidades_DAO(); 
    ArrayList<Unidades_DTO> listadoUnidadesDian = unidades.listadoUnidadesDian();

    %>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModOrdenTrabajo.jsp">
            <input type="hidden" name="item" value="<%=xItem%>">
            <input type="hidden" name="idLog" value="<%=xIdLog%>"> 

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICAR REFERENCIA</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>

                <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                                  idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle">ESTADO CLIENTE</td>
                        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
                        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                    </tr>

                    <tr>
                        <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
                        <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
                        <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
                    </tr>
                </lst:listaClienteControlAgendaNit>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="center" class="letraTitulo">REFERENCIA</td>
                    <td width="50%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                </tr>

                <tr>
                    <td width="50%" align="center" class="letraDetalle">
                        <%=fachadaDctoOrdenDetalleBean.getIdPluStr()%>
                    </td>
                    <td width="50%" align="left" class="letraDetalle">
                        <%=fachadaDctoOrdenDetalleBean.getNombrePlu()%>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="20%" align="left" class="letraResaltadaGrande">PEDIDO#</td>
                    <td width="20%" align="left" class="letraResaltadaGrande">
                        <%=fachadaDctoOrdenBean.getNumeroOrden()%>-<%=xItem%>
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">CANTIDAD PEDIDA</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <input name="cantidad" size="20"  value="<%=fachadaDctoOrdenDetalleBean.getCantidadStr()%>">
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">VR.UNITARIO.VENTA</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <input name="vrVentaUnitarioSinIva" size="20"  value="<%=fachadaDctoOrdenDetalleBean.getVrVentaUnitarioSinIvaStr()%>">
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">UNIDAD VENTA</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <input name="xUnidadVenta" size="20"  value="<%=fachadaDctoOrdenDetalleBean.getUnidadVentaDf0()%>">
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">UNIDAD MEDIDA</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <select  name="xUnidadVenta2" id="xUnidadVenta2" >
                            <%  
                        
                        
             for (Unidades_DTO listaUnidad : listadoUnidadesDian){
                  int selectedUND = fachadaDctoOrdenDetalleBean.getUnidadDian();
                                     if(selectedUND == listaUnidad.getSk_unidad()){
                            %>
                            <option selected value="<%= listaUnidad.getSk_unidad() %>" ><%= listaUnidad.getNombre_unidades()%></option>
                            <%  
                                                       }else{
                            %>
                            <option value="<%= listaUnidad.getSk_unidad() %>" ><%= listaUnidad.getNombre_unidades()%></option>
                            <% }
                                
                                }%>
                        </select>
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">DIAS PAGO</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <input name="xIdFormaPago" size="20"  value="<%=fachadaDctoOrdenBean.getFormaPago()%>">
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">RESPONSABLE IVA</td>
                    <td width="20%" align="left" class="letraDetalle">

                        <% if (fachadaDctoOrdenBean.getImpuestoVenta() == 0) {

                                        xOpcionSelectedSI = "selected";
                                        xOpcionSelectedNO = "";

                                    } else {

                                        xOpcionSelectedSI = "";
                                        xOpcionSelectedNO = "selected";
                                    }%>

                        <select  name="xImpuestoVenta" id="xImpuestoVenta" >
                            <option value="0" <%=xOpcionSelectedSI%> >SI
                            <option value="1" <%=xOpcionSelectedNO%> >NO
                        </select>

                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">VENDEDOR</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <select name=xIdVendedor>
                            <lsu:listaUsuarioLocalOpcion idUsuarioTag="<%=fachadaDctoOrdenBean.getIdVendedorStr()%>"
                                                         idNivelTag="<%=xIdNivelVendedor%>"
                                                         estadoTag="<%=xEstadoActivo%>"
                                                         idLocalUsuarioTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
                                <option value="<%=idUsuarioVar%>">
                                    <%=nombreUsuarioVar%>
                                </option>
                            </lsu:listaUsuarioLocalOpcion>
                        </select>
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">FECHA ENTREGA</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <input type="text" name="xFechaEntrega" id="xFechaEntrega" readonly="readonly"
                               value="<%=fachadaDctoOrdenDetalleBean.getFechaEntregaCorta()%>"/>
                        <img src="./img/img.gif" id="selectorFechaEntrega" />
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">ORDEN COMPRA</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <input name="xOrdenCompra" size="20" maxlength="20"  value="<%=fachadaDctoOrdenBean.getOrdenCompra()%>">
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">DIRECCION DESPACHO</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <input name="xDireccionDespacho" size="50" maxlength="50"  value="<%=fachadaDctoOrdenBean.getDireccionDespacho()%>">
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">CONTACTO</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <input name="xContacto" size="50" maxlength="50"  value="<%=fachadaDctoOrdenBean.getContacto()%>">
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraDetalle">OBSERVACION PEDIDO</td>
                    <td width="20%" align="left" class="letraDetalle">
                        <textarea name="xObservacion" rows="4" cols="50"><%=fachadaDctoOrdenBean.getObservacionMayuscula()%></textarea>
                    </td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="10%" align="left" class="letraTitulo">PARAMETO</td>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="10%" align="left" class="letraTitulo">VALOR</td>
                    <td width="20%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>
                <lsb:listaOperacionFicha idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                         idFichaTag="<%=fachadaDctoOrdenBean.getIdFichaStr()%>"
                                         idOperacionTag="<%=xIdOperacionPedido%>">
                    <tr>
                        <td width="10%" align="left" class="letraDetalle"><%=nombreEscalaVar%></td>
                        <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
                        <td width="10%" align="left" class="letraDetalle">


                            <% if (idTipoEscalaVar.compareTo("2") == 0) {%>
                            <select name=<%=idEscalaIndexVar%>>
                                <lsv:listaEscala idEscalaTag="<%=idEscalaVar%>"
                                                 itemTag="<%=vrEscalaVar%>">
                                    <option value="<%=itemVar%>">
                                        <%=nombreItemVar%>
                                    </option>
                                </lsv:listaEscala>
                            </select>
                            <% }%>

                            <% if (idTipoEscalaVar.compareTo("5") == 0) {%>
                            <select name=<%=idEscalaIndexVar%>>
                                <lsv:listaEscala idEscalaTag="<%=idEscalaVar%>"
                                                 itemTag="<%=vrEscalaVar%>">
                                    <option value="<%=itemVar%>">
                                        <%=nombreItemVar%>
                                    </option>
                                </lsv:listaEscala>
                            </select>
                            PANTONE <input name="<%=idTextoEscalaIndexVar%>" size="14" maxlength="14" value="<%=textoEscalaVar%>">
                            <% }%>

                            <% if (idTipoEscalaVar.compareTo("3") == 0) {%>
                            <textarea name="<%=idEscalaIndexVar%>" rows="4" cols="50"><%=textoEscalaVar%></textarea>
                            <% }%>

                            <% if (idTipoEscalaVar.compareTo("1") == 0) {%>
                            <input name="<%=idEscalaIndexVar%>" size="14" maxlength="14" value="<%=vrEscalaVar%>">
                            <% }%>

                            <% if (idTipoEscalaVar.compareTo("4") == 0) {%>
                            <input name="<%=idEscalaIndexVar%>" size="14" maxlength="14" value="<%=vrEscalaVar%>">
                            REF <input name="<%=idTextoEscalaIndexVar%>" size="14" maxlength="14" value="<%=textoEscalaVar%>">
                            <% }%>

                            <% if (idTipoEscalaVar.compareTo("8") == 0) {%>
                            <select name=<%=idEscalaIndexVar%>>
                                <lsa:listaPluCategoriaOpcion idLineaTag="<%=xIdLineaFabricacion%>"
                                                             idCategoriaTag="<%=xIdCategoriaProcesosExternos%>"
                                                             idPluTag="<%=idplu%>">
                                    <option value="<%=idPluVar%>">
                                        <%=nombrePluVar%>
                                    </option>
                                </lsa:listaPluCategoriaOpcion>
                            </select>
                            <input hidden name="<%=idTextoEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=0%>">
                            <% }%>
                            <% if (idTipoEscalaVar.compareTo("10") == 0) {%>
                            <select name=<%=idEscalaIndexVar%>>
                                <lsa:listaPluCategoriaOpcion idLineaTag="<%=xIdLineaFabricacion%>"
                                                             idCategoriaTag="<%=xIdCategoriaAccesorios%>"
                                                             idPluTag="<%=idplu%>">
                                    <option value="<%=idPluVar%>">
                                        <%=nombrePluVar%>
                                    </option>
                                </lsa:listaPluCategoriaOpcion>
                            </select>
                            <input hidden name="<%=idTextoEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=0%>">
                            <% }%>
                        </td>
                        <td width="20%" align="right" class="letraDetalle">&nbsp;</td>
                    </tr>
                </lsb:listaOperacionFicha>
            </table>


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="right" class="letraTitulo">
                        <input type="submit" value="Confirmar Cantidad" name="accionContenedor">
                    </td>
                    <td width="50%" align="left" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>

<script type="text/javascript">


    Calendar.setup(
            {
                inputField: "xFechaEntrega",
                ifFormat: "%Y/%m/%d",
                button: "selectorFechaEntrega",
                date: new Date()
            }
    );
</script>