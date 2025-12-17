<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaInventarioConteo" prefix="lst" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />
    <jsp:useBean id="fachadaPluBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluBean" />

    <head>
        <title>Crea Foto Inventario</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngInventarioConteo.jsp">
            <input type="hidden" name="xDiasHistoria" value="<%=fachadaDctoOrdenBean.getDiasHistoria()%>">
            <input type="hidden" name="xDiasInventario" value="<%=fachadaDctoOrdenBean.getDiasInventario()%>">
            <input type="hidden" name="xIdTercero" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaDctoOrdenBean.getFechaOrden()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CREA FOTO INVENTARIO</td>
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
                        <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                        <td width="25%" align="center" class="letraTitulo">FECHA CORTE</td>
                        <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                        <td width="25%" align="center" class="letraTitulo">BODEGA ORIGEN</td>
                    </tr>

                    <tr>
                        <td width="25%" align="center" class="letraDetalle">&nbsp;</td>
                        <td width="25%" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getFechaOrdenCorta()%></td>
                        <td width="25%" align="center" class="letraDetalle">&nbsp;</td>
                        <td width="25%" align="center" class="letraDetalle">
                            <jsp:include page="./comboCentroResurtido.jsp"/>
                        </td>
                    </tr>

                </table>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="20%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraTitulo">PLU</td>
                    <td width="5%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="40%" align="left" class="letraTitulo">ARTICULO</td>
                    <td width="5%" align="right" class="letraTitulo">I.ACTUAL</td>
                    <td width="20%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>

                <lst:listaInventarioConteo idLocalTag = "<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                                           idBodegaTag="<%=fachadaPluBean.getIdBodegaStr()%>"
                                           idTipoTag="<%=fachadaPluBean.getIdTipoStr()%>">
                    <input type="hidden" name="xIdPlu" value="<%=idPluVar%>">
                    <input type="hidden" name="xCantidadPedida" value="<%=existenciaVar%>">
                    <tr>
                        <td width="20%" align="right" class="letraDetalle">&nbsp;</td>
                        <td width="5%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                        <td width="40%" align="left" class="letraDetalle">
                            <%=nombreCategoriaVar%>
                            <%=nombrePluVar%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle"><%=existenciaDf2Var%></td>
                        <td width="20%" align="right" class="letraDetalle">&nbsp;</td>
                    </tr>
                </lst:listaInventarioConteo>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="GuardarFoto" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>
</html>