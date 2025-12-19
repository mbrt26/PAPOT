<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoDetalle" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean" />

    <head>
        <title>Validar Conteo</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
        <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConInventarioConteo.jsp">
        <input type="hidden" name="xIdLocal" value="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>">
        <input type="hidden" name="xIdTipoOrden" value="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>">
        <input type="hidden" name="xIdOrden" value="<%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%>">
        <input type="hidden" name="xIdLog" value="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>">

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">VALIDAR CONTEO</td>
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
            <table border="0" width="90%" id="tablaTitulo">

                <tr>
                    <td width="33%" align="center" class="letraTitulo">#ORDEN</td>
                    <td width="34%" align="center" class="letraTitulo">FECHA ORDEN</td>
                    <td width="33%" align="right" class="letraTitulo">#ARTICULOS</td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%></td>
                    <td width="34%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getFechaOrdenCorta()%></td>
                    <td width="33%" align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getCantidadArticulosStr()%></td>
                </tr>

            </table>
        </table>
        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="10%" align="right" class="letraTitulo">PLU</td>
                <td width="40%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                <td width="10%" align="left" class="letraTitulo">MARCA</td>
                <td width="10%" align="right" class="letraTitulo">C.CONTEO</td>
                <td width="10%" align="right" class="letraTitulo">C.FOTO</td>
                <td width="10%" align="right" class="letraTitulo">AJUSTE</td>
            </tr>
            <lst:listaResurtidoDetalle idLocalTag="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>"
                                       idTipoOrdenTag="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>"
                                       idLogTag="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>">

                <input type="hidden" name="xIdPlu" value="<%=idPluVar%>">
                <input type="hidden" name="xCantidad" value="<%=cantidadVar%>">
                <tr>
                    <td width="10%" align="right" class="letraDetalle"><%=idPluVar%></td>
                    <td width="40%" align="left" class="letraDetalle">
                        <%=nombreCategoriaVar%>
                        <%=nombrePluVar%>
                    </td>
                    <td width="10%" align="left" class="letraDetalle">
                        <%=nombreMarcaVar%>
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <%=cantidadDf2Var%>
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <%=cantidadPedidoVar%>
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <%=cantidadAjusteVar%>
                    </td>
                </tr>
            </lst:listaResurtidoDetalle>
        </table>
        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" align="center" class="letraTitulo">
                    <input type="submit" value="Regresar" name="accionContenedor">
                </td>
                <td width="34%" align="left" class="letraTitulo">
                    <input type="submit" value="Legalizar" name="accionContenedor">
                </td>
                <td width="33%" align="right" class="letraTitulo">&nbsp;</td>
            </tr>
        </table>
    </body>
    </form>
</html>
</html>
