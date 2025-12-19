<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ page import="java.text.DecimalFormat" %>
    <%@ page import="com.solucionesweb.lasayudas.ProcesoCupoDisponible" %>
    <%@ page import="com.solucionesweb.lasayudas.ProcesoValidaPlazo" %>

    <%@ taglib uri="/WEB-INF/tlds/listaPrecio" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaCuentaLocalTotal" prefix="lsj" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOrden" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaOrden" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <%
                String xIdTipoOrdenFactura = "9";
                String xIdBodega = "1";
                String xIdOperacionPedidoStr = "1";
    %>


    <head>
        <title>Elaborar Remision</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest"
                   value="/jsp/vtaFrmLiqRemisionFacturaRemision.jsp">
            <input type="hidden" name="xIdLocal"
                   value="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden"
                   value="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog"
                   value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdOperacion"
                   value="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">ELABORAR REMISION</td>
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
                <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaDctoOrdenBean.getIdCliente()%>"
                idTipoTerceroTag="<%=fachadaDctoOrdenBean.getIdTipoTerceroStr()%>">
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
                    <td width="33%" align="right" class="letraTitulo">#ARTICULOS</td>
                    <td width="34%" align="right" class="letraTitulo">$VR.BASE</td>
                    <td width="33%" align="right" class="letraTitulo">$VR.TOTAL</td>
                </tr>
                <lsu:liquidaOrden idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                idLogTag = "<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
                    <tr>
                        <td width="33%" align="right" class="letraDetalle"><%=cantidadArticulosVar%></td>
                        <td width="34%" align="right" class="letraDetalle"><%=vrVentaSinDsctoDf0Var%></td>
                        <td width="33%" align="right" class="letraResaltadaGrande"><%=vrVentaConIvaVar%></td>
                    </tr>

                </lsu:liquidaOrden>

            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" class="letraTitulo">&nbsp;</td>
                    <td width="25%" nowrap align="left" class="letraTitulo">REF.CLIENTE</td>
                    <td width="5%" nowrap align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="10%" nowrap align="right" class="letraTitulo">CAN.TER</td>
                    <td width="20%" nowrap align="left" class="letraTitulo">EMPAQUE/KG</td>
                    <td width="10%" nowrap align="right" class="letraTitulo">KG</td>
                    <td width="10%" nowrap align="right" class="letraTitulo">CANTIDAD</td>


                </tr>
                <lsv:listaOrden idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                idLogTag="<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                idBodegaTag="<%=xIdBodega%>">
                   <input type="hidden" name="xIdLocal" value="<%=idLocalVar%>">
                   <input type="hidden" name="xIdTipoOrden" value="<%=idTipoOrdenVar%>">
                   <input type="hidden"  name="xIdOrdenOrigen" value="<%=idOrdenOrigenVar%>">
                   <input type="hidden"  name="xItem" value="<%=itemVar%>">

                    <tr>
                        <td width="5%"  nowrap align="center" class="<%=letraDetalleInventarioVar%>">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLiqRemisionFacturaRemision.jsp&accionContenedor=Retirar&xIdLocal=<%=fachadaDctoOrdenBean.getIdLocalStr()%>&xIdLog=<%=fachadaDctoOrdenBean.getIdLogStr()%>&xIdBodega=<%=xIdBodega%>&xItem=<%=itemVar%>&xIdOperacion=<%=fachadaDctoOrdenBean.getIdOperacionStr()%>">R</a>
                        </td>
                        <td width="25%" nowrap align="left" class="<%=letraDetalleInventarioVar%>"><%=referenciaClienteVar%></td>
                        <td width="5%" nowrap align="left" class="letraResaltadaGrande"><%=referenciaVar%></td>
                        <td width="10%" nowrap align="right" class="letraResaltadaGrande"><%=cantidadVar%></td>
                        <td width="20%" nowrap align="left" class="<%=letraDetalleInventarioVar%>">
                            <input type="text" value="" name="xComentario" size="30" maxlength="30">
                        </td>
                        <td width="10%" nowrap align="right" class="<%=letraDetalleInventarioVar%>">
                            <input type="text" value="<%=pesoPendienteVar%>" name="xPesoPendiente" size="6" maxlength="6">
                        </td>
                        <td width="10%" nowrap align="right" class="letraResaltadaGrande">
                            <input type="text" name="xCantidad" value="<%=cantidadStrVar%>" size="6" maxlength="6">
                        </td>
                    </tr>

                </lsv:listaOrden>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="100%" align="left" class="letraDetalle">
                        OBSERVACIONES <input type="text" value="" name="xObservacion" size="100" maxlength="100">
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Finaliza Remision" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                     <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>

    </body>

</html>