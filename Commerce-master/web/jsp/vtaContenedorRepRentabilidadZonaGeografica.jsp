<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
<head>
    <title>Reporte Rentabilidad Por Zona Geografica</title>
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
        <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorRepRentabilidadZonaGeografica.jsp">

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">REPORTE RENTABILIDAD POR ZONA GEOGRAFICA</td>
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
                <td width="25%" align="center" class="letraTitulo">DESTINACION</td>
                <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
            </tr>

            <tr>
                <!--td width="25%" align="center" class="letraDetalle">
                    <input type="text" name="xFechaInicial" size="10" value="<!%=fachadaColaboraReporteDctoBean.getFechaInicial()%>">
                </td-->

                <td width="25%" align="center" class="letraDetalle">
                    <input type="text" name="xFechaInicial" id="fechainicial" readonly="readonly"
                            value="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"/>
                    <img src="./img/img.gif" id="selectorinicial" />
                </td>


                <!--td width="25%" align="center" class="letraDetalle">
                    <input type="text" name="xFechaFinal" size="10" value="<!%=fachadaColaboraReporteDctoBean.getFechaFinal()%>">
                </td-->

                <td width="25%" align="center" class="letraDetalle">
                    <input type="text" name="xFechaFinal" id="fechafinal" readonly="readonly"
                           value="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>"/>
                    <img src="./img/img.gif" id="selectorfinal" />
                    </td>

                <td width="25%" align="center" class="letraDetalle">
                    <jsp:include page="./comboDestinacion.jsp"/>
                </td>
                <td width="25%" align="center" class="letraDetalle">&nbsp;</td>
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
    </b>
</body>
</html>

    <script type="text/javascript">


  Calendar.setup(
  {
    inputField: "fechainicial",
    ifFormat:   "%Y/%m/%d",
    button:     "selectorinicial",
    date: new Date()
 }
);

  Calendar.setup(
  {
    inputField: "fechafinal",
    ifFormat:   "%Y/%m/%d",
    button:     "selectorfinal",
    date: new Date()
  }
);

</script>