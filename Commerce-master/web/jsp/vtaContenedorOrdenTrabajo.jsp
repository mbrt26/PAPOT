<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ page import="java.text.DecimalFormat" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="java.util.List" %>
    <%@ page import="com.solucionesweb.lasayudas.ProcesoCupoDisponible" %>
    <%@ page import="com.solucionesweb.lasayudas.ProcesoValidaPlazo" %>
    <%@ page import="co.linxsi.modelo.retencion.retencion_contable.Retencion_Contable_DAO" %>
    <%@ page import="co.linxsi.modelo.retencion.retencion_contable.Retencion_Contable_DTO" %>

    <%@ taglib uri="/WEB-INF/tlds/listaPrecio" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOrden" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaOrden" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>

    <jsp:useBean id="fachadaAgendaLogVisitaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <!--jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" /-->

    <%
                String xIdTipoOrdenFactura = "9";
                String xIdBodega = "1";
                String xIdOperacionPedidoStr = "1";
    
    %>

    <head>
        <title>Elaborar Pedido</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest"
                   value="/jsp/vtaContenedorOrdenTrabajo.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">ELABORAR PEDIDO</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraResaltadaTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        &nbsp;
                    </td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>

                <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
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
                </lista:listaClienteControlAgendaNit>

                <tr>
                    <td width="33%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraResaltadaTitulo">PEDIDO
                        <br> <%=fachadaDctoOrdenBean.getNumeroOrden()%>
                        <input name ="numeroOrden" hidden="true" value =<%=fachadaDctoOrdenBean.getNumeroOrden()%>>
                    </td>
                    <td width="33%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="25%" align="right" class="letraTitulo">#ARTICULOS</td>
                    <td width="25%" align="right" class="letraTitulo">$VR.BASE</td>
                    <td width="25%" align="right" class="letraTitulo">$VR.TOTAL</td>

                </tr>
                <lsu:liquidaOrden idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                  idLogTag = "<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                  idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>">
                    <tr>
                        <td width="25%" align="right" class="letraDetalle"><%=cantidadArticulosVar%></td>
                        <td width="25%" align="right" class="letraDetalle"><%=vrVentaSinDsctoDf0Var%></td>
                        <td width="25%" align="right" class="letraResaltadaGrande"><%=vrVentaConIvaVar%></td>
                        <td width="25%" align="right" class="letraResaltadaGrande"></td>
                    </tr>

                </lsu:liquidaOrden>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="32%" align="left"  class="letraTitulo">
                        RETEFUENTE FACTURA: <select id ="listaRetencion" name="listaRetencion">

                            <%  Retencion_Contable_DAO dao = new Retencion_Contable_DAO();
                               List<Retencion_Contable_DTO> lista = dao.listaRetFuente(); 
                               
                               int idTipoAlcance = fachadaDctoOrdenBean.getIdTipoTx(); 
                            for (Retencion_Contable_DTO dto : lista) {
                              String elementoLista = " "+dto.getNombreConcepto();
                    if(idTipoAlcance == dto.getIdTipoOrdenAlcance() ){                          
                            %>
                            <option selected value="<%=dto.getIdTipoOrdenAlcance()%>" > <%=elementoLista%></option>


                            <% }else{

                            %>
                            <option value="<%=dto.getIdTipoOrdenAlcance()%>" ><%=elementoLista%></option>

                            <%
                           }}
                            %>
                        </select> 
                    </td>

                    <td width="33%" align="left"  class="letraTitulo">
                        <input type="submit" value="+Productos" name="accionContenedor">
                        <input type="text" name="idLinea" id="idLinea" size="30" tabindex="1" maxlength="30">

                    </td>
                    <td width="33%" align="right" class="letraTitulo">
                    </td>

                </tr>
            </table>

            <script type="text/javascript">
                document.getElementById('idLinea').focus();
            </script>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" class="letraTitulo">&nbsp;</td>
                    <td width="25%" nowrap align="left" class="letraTitulo">REF.CLIENTE</td>
                    <td width="5%" nowrap align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="10%" nowrap align="center" class="letraTitulo">FEC.ENT</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">VR.UNI</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">CAN.PED</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">UND.VTA.</td>
                    <!--td width="5%" nowrap align="right" class="letraTitulo">&nbsp;</td-->
                    <td width="10%" nowrap align="right" class="letraTitulo">SUBTOTAL</td>
                </tr>

                <lsv:listaOrden idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>"
                                idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                idLogTag="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                idBodegaTag="<%=xIdBodega%>">
                    <tr>
                        <td width="5%"  nowrap align="center" class="<%=letraDetalleInventarioVar%>">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorOrdenTrabajo.jsp&accionContenedor=Retirar&item=<%=itemVar%>&idLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">&nbsp;R&nbsp;</a>
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorOrdenTrabajo.jsp&accionContenedor=ModificarItem&item=<%=itemVar%>&idLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">&nbsp;M&nbsp;</a>
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorOrdenTrabajo.jsp&accionContenedor=Imprimir&xNumeroOrden=<%=fachadaDctoOrdenBean.getNumeroOrden()%>&item=<%=itemVar%>&idLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">&nbsp;I&nbsp;</a>
                        </td>
                        <td width="25%" nowrap align="left" class="<%=letraDetalleInventarioVar%>"><%=referenciaClienteVar%></td>
                        <td width="5%" nowrap align="left" class="letraResaltadaGrande"><%=referenciaVar%></td>
                        <td width="10%" nowrap align="center" class="<%=letraDetalleInventarioVar%>"><%=fechaEntregaVar%></td>
                        <td width="5%" nowrap align="right" class="<%=letraDetalleInventarioVar%>"><%=vrVentaUnitarioDf2Var%></td>
                        <td width="5%" nowrap align="right" class="letraResaltadaGrande"><%=cantidadVar%></td>
                        <td width="5%" nowrap align="right" class="<%=letraDetalleInventarioVar%>"><%=unidadVentaVar%></td>
                        <!--td width="5%" nowrap align="right" class="letraResaltadaGrande">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorOrdenTrabajo.jsp&accionContenedor=cumplir&item=<%=itemVar%>&idLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">Cumplido</a>
                        </td-->
                        <td width="10%" nowrap align="right" class="letraResaltadaGrande"><%=vrVentaConIvaVar%></td>
                    </tr>

                </lsv:listaOrden>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Finalizar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Crear" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>

    </body>

</html>