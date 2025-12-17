<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaRemisionAll" prefix="lst" %>

    <jsp:useBean id="fachadaDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoBean" />

    <head>
        <title>Detalle Remision</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstRepVentaRemision.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">DETALLE REMISION</td>
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

                <tr>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">DEL <%=fachadaDctoBean.getFechaInicial()%>
                        AL <%=fachadaDctoBean.getFechaFinal()%></td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="right" class="letraTitulo"># REM</td>
                    <td width="5%" align="right" class="letraTitulo">O.T.</td>
                    <td width="35%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
                    <td width="10%" align="center" class="letraTitulo">FEC.REM</td>
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD</td>
                    <td width="5%" align="left" class="letraTitulo">REFERENCIA CLIENTE</td>
                </tr>
                <lst:listaRemisionAll
                idLocalTag="<%=fachadaDctoBean.getIdLocalStr()%>"
                idTipoOrdenTag="<%=fachadaDctoBean.getIdTipoOrdenStr()%>"
                fechaInicialTag="<%=fachadaDctoBean.getFechaInicial()%>"
                fechaFinalTag="<%=fachadaDctoBean.getFechaFinal()%>"
                idTerceroTag="<%=fachadaDctoBean.getIdCliente()%>">
                    <tr>
                        <td width="5%" align="right" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstRepVentaRemision.jsp&accionContenedor=lista&xIdLocal=<%=idLocalVar%>&xIdTipoOrden=<%=idTipoOrdenVar%>&xIdOrden=<%=idOrdenVar%>"><%=idDctoVar%></a>
                        </td>
                        <td width="5%" align="right" class="letraDetalle"><%=numeroOrdenVar%></td>
                        <td width="35%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                        <td width="10%" align="center" class="letraDetalle"><%=fechaDctoVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadVar%></td>
                        <td width="5%" align="left" class="letraDetalle"><%=referenciaClienteVar%></td>
                    </tr>
                </lst:listaRemisionAll>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
