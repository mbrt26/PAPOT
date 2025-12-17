<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoAjuste" prefix="lst" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <head>
        <title>LISTA TRASLADO</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConResurtidoTraslado.jsp">
            <input type="hidden" name="xDiasHistoria" value="<%=fachadaDctoOrdenBean.getDiasHistoria()%>">
            <input type="hidden" name="xDiasInventario" value="<%=fachadaDctoOrdenBean.getDiasInventario()%>">
            <input type="hidden" name="xIdTercero" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaDctoOrdenBean.getFechaOrden()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">Lista Traslado</td>
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
                        <td width="100%" align="center" class="letraTitulo">BODEGA ORIGEN</td>
                    </tr>

                    <tr>
                        <td width="10025%" align="center" class="letraDetalle">
                            <jsp:include page="./comboCentroResurtido.jsp"/>
                        </td>
                    </tr>

                </table>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">#DCTO</td>
                    <td width="10%" align="center" class="letraTitulo">NIT/CC</td>
                    <td width="10%" align="center" class="letraTitulo">F.ORDEN</td>
                    <td width="10%" align="left" class="letraTitulo">C.O.</td>
                    <td width="10%" align="right" class="letraTitulo">V.COSTO</td>
                    <td width="10%" align="right" class="letraTitulo">#ARTIC</td>
                    <td width="15%" align="left" class="letraTitulo">USUARIO</td>
                </tr>

                <lst:listaResurtidoAjuste idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                                          idTerceroTag="<%=fachadaDctoOrdenBean.getIdCliente()%>"
                                          idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
                    <tr>
                        <td width="5%" align="right" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmConResurtidoTraslado.jsp&accionContenedor=tomar&xIdLocal=<%=fachadaDctoOrdenBean.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>&xIdLog=<%=idLogVar%>&xIdOrden=<%=idOrdenVar%>"><%=idDctoVar%></a>
                        </td>
                        <td width="10%" align="center" class="letraDetalle"><%=idTerceroVar%></td>
                        <td width="10%" align="center" class="letraDetalle"><%=fechaOrdenVar%></td>
                        <td width="10%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=vrCostoMVVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=cantidadArticulosVar%></td>
                        <td width="15%" align="left" class="letraDetalle"><%=nombreUsuarioVar%></td>
                    </tr>
                </lst:listaResurtidoAjuste>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>
</html>