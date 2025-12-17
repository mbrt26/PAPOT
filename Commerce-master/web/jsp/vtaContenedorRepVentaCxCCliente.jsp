<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
<head>
    <title>Reporte Ventas CxC Cliente</title>
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
        <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorRepVentaCxCCliente.jsp">

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">REPORTE VENTAS CXC CLIENTE</td>
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

        </table>

        <table border="0" width="90%" id="tablaTitulo">

            <tr>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">FECHA CORTE</td>
                    <td width="33%" align="center" class="letraTitulo">DESTINACION</td>
            </tr>

            <tr>
                <td width="33%" align="center" class="letraDetalle">&nbsp;
                     <!--jsp:include page="./comboUsuarioLocal.jsp"/-->
                    </td>
                <!--td width="34%" align="center" class="letraDetalle">
                    <input type="text" value="<!%=fachadaColaboraReporteDctoBean.getFechaFinal()%>" name="xFechaFinal" maxlength="10" size="10">
                </td-->

                <td width="25%" align="center" class="letraDetalle">
                    <input type="text" name="xFechaFinal" id="fechafinal" readonly="readonly"
                           value="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>"/>
                    <img src="./img/img.gif" id="selectorfinal" />
                    </td>

                <td width="33%" align="center" class="letraDetalle">
                    <jsp:include page="./comboDestinacion.jsp"/>
                    </td>
            </tr>

            <tr>
                <td width="33%" align="center" class="letraTitulo">
                    <input type="submit" value="Consultar" name="accionContenedor">
                </td>
                <td width="34%" align="center" class="letraTitulo">
                    <input type="submit" value="Salir" name="accionContenedor">
                </td>
                <td width="33%" align="center" class="letraTitulo">
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
    inputField: "fechafinal",
    ifFormat:   "%Y/%m/%d",
    button:     "selectorfinal",
    date: new Date()
  }
);

</script>