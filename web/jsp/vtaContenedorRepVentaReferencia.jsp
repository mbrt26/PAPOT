<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
<head>
    <title>Reporte Ventas Referencias</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">

    <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
    <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
    <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
    <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
</head>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

<jsp:useBean id="fachadaColaboraReporteDctoBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

<body>
    <form method="POST" action="GralControladorServlet">
        <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorRepVentaReferencia.jsp">

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">REPORTE VENTAS X REFERENCIA</td>
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
                <td width="25%" align="center" class="letraTitulo">FECHA INICIAL</td>
                <td width="25%" align="center" class="letraTitulo">FECHA FINAL</td>
                <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                <!--td width="25%" align="center" class="letraTitulo">ALCANCE</td-->
                <td width="25%" align="center" class="letraTitulo">DESTINACION</td>
            </tr>
            <tr>
                <td width="25%" align="center" class="letraDetalle">
                    <input type="text" name="xFechaInicial" id="xFechaInicial" readonly="readonly" 
                            value="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"/>
                    <img src="./img/img.gif" id="selectorinicial" />
                </td>
                <td width="25%" align="center" class="letraDetalle">
                    <input type="text" name="xFechaFinal" id="xFechaFinal" readonly="readonly"
                           value="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>"/>
                    <img src="./img/img.gif" id="selectorfinal" />
                    </td>

                <td width="25%" align="center" class="letraDetalle">&nbsp;
                    <jsp:include page="./comboUsuarioAdmintradorAll.jsp"/>
                </td>
                <td width="25%" align="center" class="letraDetalle">
                    <jsp:include page="./comboDestinacion.jsp"/>
                </td>
            </tr>
            <tr>
                <td width="25%" align="center" class="letraTitulo">
                    <input type="submit" value="Consultar" name="accionContenedor">
                </td>
                <td width="25%" align="center" class="letraTitulo">
                    <input type="submit" value="Salir" name="accionContenedor">
                </td>
                <td width="25%" align="center" class="letraTitulo">
                    &nbsp;
                </td>
                <td width="25%" align="center" class="letraTitulo">
                    &nbsp;
                </td>
            </tr>
        </table>
    </form>
</body>
</html>

    <script type="text/javascript">


  Calendar.setup(
  {
    inputField: "xFechaInicial",
    ifFormat:   "%Y/%m/%d",
    button:     "selectorinicial",
    date: new Date()
 }
);

  Calendar.setup(
  {
    inputField: "xFechaFinal",
    ifFormat:   "%Y/%m/%d",
    button:     "selectorfinal",
    date: new Date()
  }
);

</script>