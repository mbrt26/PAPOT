<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoDetalle" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean" />

    <head>
        <title>RESURTIDO TRASLADO</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLegResurtidoTraslado.jsp">

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
                    <td width="34%" align="center" class="letraTitulo">Resurtido Traslado</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
                <table border="0" width="90%" id="tablaTitulo">

                    <tr>
                        <td width="25%" align="left" class="letraTitulo">&nbsp;</td>
                        <td width="25%" align="center" class="letraTitulo">#ORDEN</td>
                        <td width="25%" align="center" class="letraTitulo">FECHA ORDEN</td>
                        <td width="25%" align="right" class="letraTitulo">#ARTICULOS</td>
                        <td width="25%" align="right" class="letraTitulo">VR.COMPRA</td>
                    </tr>

                    <tr>
                        <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                        <td width="10%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%></td>
                        <td width="10%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getFechaOrdenCorta()%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getCantidadArticulosStr()%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getVrCostoBaseDf0()%></td>
                    </tr>

                </table>
            </table>
                <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="10%" align="right" class="letraTitulo">CAN.PED</td>
                    <td width="10%" align="right" class="letraTitulo">CANT</td>
                    <td width="10%" align="right" class="letraTitulo">SUBTOTAL</td>
                    <td width="10%" align="right" class="letraTitulo">PLU</td>
                    <td width="40%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    <td width="10%" align="left" class="letraTitulo">MARCA</td>
                </tr>
                <lst:listaResurtidoDetalle idLocalTag="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>"
                                       idTipoOrdenTag="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>"
                                             idLogTag="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>">
                     <input type="hidden" name="xIdPlu" value="<%=idPluVar%>">
                    <tr>
                        <td width="10%" align="right" class="letraDetalle">
                            <%=cantidadPedidoVar%>
                        </td>
                        <td width="10%" align="right" class="letraDetalle">
                            <input type="text" name="xCantidad" value="0" size="6" maxlength="6">
                        </td>
                        <td width="10%" align="right" class="letraDetalle">
                            <input type="text" name="xVrCostoResurtido" value="0" size="10" maxlength="10">
                        </td>
                        <td width="10%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        <td width="40%" align="left" class="letraDetalle">
                            <%=nombreCategoriaVar%>
                            <%=nombrePluVar%>
                        </td>
                        <td width="10%" align="left" class="letraDetalle">
                            <%=nombreMarcaVar%>
                        </td>
                    </tr>
                </lst:listaResurtidoDetalle>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="20%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="20%" align="center" class="letraTitulo">
                        <input type="submit" value="Elaborar" name="accionContenedor">
                    </td>
                    <td width="20%" align="center" class="letraTitulo">
                        <input type="submit" value="Imprimir" name="accionContenedor">
                    </td>
                    <td width="20%" align="center" class="letraTitulo">
                        <input type="submit" value="Legalizar" name="accionContenedor">
                        </td>
                    <td width="20%" align="center" class="letraTitulo">
                        <input type="submit" value="Eliminar" name="accionContenedor">
                        </td>
                </tr>
            </table>

        </form>
    </body>
</html>
</html>
