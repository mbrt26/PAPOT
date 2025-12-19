<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
<head>
    <title>Reporte Cuadre Caja</title>
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

<jsp:useBean id="fachadaDctoCuadre"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoCuadre" />

<body>
    <form method="POST" action="GralControladorServlet">
        <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorRepVentaCuadreCaja.jsp">

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">REPORTE CUADRE CAJA</td>
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
                <td width="33%" align="right" class="letraTitulo">SALDO INICIAL</td>
                <td width="34%" align="center" class="letraTitulo">FECHA CUADRE</td>
                <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
            </tr>
            <tr>
                <td width="33%" align="right" class="letraDetalle">
                    <input type="text" name="xSaldoInicial" value ="<%=fachadaDctoCuadre.getSaldoInicial()%>" size="10" maxlength="10">
                </td>
                <td width="34%" align="center" class="letraDetalle">
                    <input type="text" name="xFechaInicial" id="xFechaInicial" readonly="readonly"
                            value="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"/>
                    <img src="./img/img.gif" id="selectorinicial" />
                </td>
                <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
            </tr>
            <tr>
                <td width="33%" align="center" class="letraTitulo">
                    <input type="submit" value="Salir" name="accionContenedor">
                </td>
                <td width="34%" align="center" class="letraTitulo">
                    <input type="submit" value="Consultar" name="accionContenedor">
                </td>
                <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
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
</script>