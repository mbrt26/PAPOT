<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
<head>
    <title>Reporte Pedido x Estado</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

<jsp:useBean id="fachadaColaboraReporteDctoBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

<jsp:useBean id="fachadaDctoOrdenBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

<body>
    <form method="POST" action="GralControladorServlet">
        <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorAdmOTEstado.jsp">
        <input type="hidden" name="xFechaInicial" value="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>">
        <input type="hidden" name="xFechaFinal" value="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>">
        <input type="hidden" name="xIdLocal" value="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>">
        <input type="hidden" name="xIdTipoOrden" value="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenStr()%>">
        <input type="hidden" name="xIdOrden" value="<%=fachadaColaboraReporteDctoBean.getIdOrdenStr()%>">
        <input type="hidden" name="xItem" value="<%=fachadaColaboraReporteDctoBean.getItem()%>">

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">REPORTE PEDIDO X ESTADO</td>
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
                <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                <td width="25%" align="center" class="letraTitulo">CLIENTE</td>
                <td width="25%" align="center" class="letraTitulo">ESTADO</td>
                <td width="25%" align="center" class="letraTitulo">DESTINACION</td>
            </tr>
            <tr>
                <td width="25%" align="center" class="letraDetalle">
                    <input type="text" value="0" name="xNumeroOrden"  size="6" maxlength="6">
                </td>
                <td width="25%" align="center" class="letraDetalle">
                    <jsp:include page="./comboTerceroOpcion.jsp"/>
                </td>
                <td width="25%" align="center" class="letraDetalle">
                    <jsp:include page="./comboOrdenEstado.jsp"/>
                </td>
                <td width="25%" align="center" class="letraDetalle">
                    <jsp:include page="./comboDestinacion.jsp"/>
                </td>
            </tr>
            <tr>
                <td width="25%" align="center" class="letraTitulo">
                    <input type="submit" value="UnPedido" name="accionContenedor">
                </td>
                <td width="25%" align="center" class="letraTitulo">
                    <input type="submit" value="Consultar" name="accionContenedor">
                </td>
                <td width="25%" align="center" class="letraTitulo">
                    <input type="submit" value="Salir" name="accionContenedor">
                </td>
                <td width="25%" align="center" class="letraTitulo">
                    &nbsp;
                </td>
            </tr>
        </table>
    </form>
</body>
</html>