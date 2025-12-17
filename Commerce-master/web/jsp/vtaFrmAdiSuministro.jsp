<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaPrecioLista" prefix="lista" %>

    <jsp:useBean id="fachadaColaboraDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean" />

    <% String xIdListaPrecio = "1";%>

    <head>
        <title>ADICIONAR SUMINISTRO</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmAdiSuministro.jsp">
            <input type="hidden" name="xDiasHistoria" value="<%=fachadaColaboraDctoOrdenBean.getDiasHistoria()%>">
            <input type="hidden" name="xDiasInventario" value="<%=fachadaColaboraDctoOrdenBean.getDiasInventario()%>">
            <input type="hidden" name="xIdTercero" value="<%=fachadaColaboraDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaColaboraDctoOrdenBean.getFechaOrden()%>">
            <input type="hidden" name="xIdLocal" value="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdOrden" value="<%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">ADICIONAR SUMINISTRO</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>

                    <td width="33%" class="letraResaltadaTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="center" class="letraTitulo">REF</td>
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD</td>
                    <td width="45%" nowrap align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    <td width="5%" align="right" class="letraTitulo">V.COSTO</td>
                    <td width="5%" align="right" class="letraTitulo">EXIST</td>
                    <td width="15%" align="left" class="letraTitulo">NOMBRE MARCA</td>
                </tr>

                <lista:listaPrecioLista idLineaTag = "<%=fachadaColaboraDctoOrdenBean.getIdLinea()%>"
                                        nombrePluTag = "<%=fachadaColaboraDctoOrdenBean.getNombrePlu()%>"
                                        idListaPrecioTag = "<%=xIdListaPrecio%>"
                                        idLocalTag = "<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>">

                    <input type="hidden" name="xIdPlu" value="<%=strIdReferenciaVar%>">
                    <input type="hidden" name="xCostoPedido" value="<%=vrCostoVar%>">

                    <tr>
                        <td width="5%" align="center" class="letraDetalle">
                            <%=strIdReferenciaVar%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <input type="text" name="xCantidadPedido" id="xCantidadPedido" value="0" size="6">
                        </td>
                        <td width="45%" nowrap align="left" class="letraDetalle">
                            <%=nombrePluVar.trim()%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=vrCostoDf2Var%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle"><%=existenciaVar%></td>
                        <td width="15%" align="left" class="letraDetalle">
                            <%=nombreMarcaVar.trim()%>
                        </td>
                    </tr>

                </lista:listaPrecioLista>

            </table>
            <script type="text/javascript">
                document.getElementById('xCantidadPedido').focus();
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