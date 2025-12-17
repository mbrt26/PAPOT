<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaPrecioLista" prefix="lista" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <jsp:useBean id="fachadaColaboraHistoriaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean" />

    <head>
        <title>Adicionar Productos Movimiento</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
        
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstInventarioMovimiento.jsp">
            <input type="hidden" name="xIdTercero" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaDctoOrdenBean.getFechaOrdenCorta()%>">
            <input type="hidden" name="xIdBodegaOrigen" value="<%=fachadaDctoOrdenBean.getIdBodegaOrigenStr()%>">
            <input type="hidden" name="xIdBodegaDestino" value="<%=fachadaDctoOrdenBean.getIdBodegaDestinoStr()%>">
            <input type="hidden" name="xNumeroOrden" value="<%=fachadaDctoOrdenBean.getNumeroOrden()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenBean.getIdTipoOrden()%>">
            <input type="hidden" name="xIdOperario" value="<%=fachadaDctoOrdenBean.getIdOperario()%>">            

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">ADICIONAR PRODUCTOS MOVIMIENTO</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="34%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="33%" align="center" class="letraDetalle">
                    <jsp:include page="./comboCentroResurtido.jsp"/>
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="center" class="letraTitulo">REF</td>
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD</td>
                    <td width="45%" nowrap align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    <td width="15%" align="left" class="letraTitulo">NOMBRE MARCA</td>
                    <td width="5%" align="right" class="letraTitulo">V.VENTA</td>
                    <td width="5%" align="right" class="letraTitulo">EXIST</td>
                </tr>

                <lista:listaPrecioLista idLineaTag = "<%=fachadaColaboraHistoriaBean.getIdLinea()%>"
                                        nombrePluTag = "<%=fachadaColaboraHistoriaBean.getNombrePlu()%>"
                                        idListaPrecioTag = "<%=fachadaDctoOrdenBean.getIdListaPrecioStr()%>"
                                        idLocalTag = "<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                                        idBodegaTag = "<%=fachadaDctoOrdenBean.getIdBodegaOrigenStr()%>">

                    <input type="hidden" name="chkIdReferencia" value="<%=strIdReferenciaVar%>">
                    <input type="hidden" name="chkVrVentaUnitario" value="<%=vrVentaUnitarioVar%>">

                    <tr>
                        <td width="5%" align="center" class="letraDetalle">
                            <%=strIdReferenciaVar%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <input type="text" name="chkCantidad" id="chkCantidad" value="" size="6">
                        </td>
                        <td width="45%" nowrap align="left" class="letraDetalle">
                            <%=nombrePluVar.trim()%>
                        </td>

                        <td width="15%" align="left" class="letraDetalle">
                            <%=nombreMarcaVar.trim()%>
                        </td>

                        <td width="5%" align="right" class="letraDetalle">
                            <%=vrVentaUnitarioDf0Var%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle"><%=existenciaVar%></td>
                    </tr>

                </lista:listaPrecioLista>

            </table>
            <script type="text/javascript">
                document.getElementById('chkCantidad').focus();
            </script>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Confirmar" name="accionContenedor">
                    </td>
                    <td width="34%" align="left" class="letraTitulo">
                        <a href="javascript:window.history.back()"><img src="./img/ARW06LT.GIF" width="32" height="32"></a>
                    </td>
                    <td width="33%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>