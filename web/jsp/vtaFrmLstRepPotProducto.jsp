<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaOTProductivo" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

    <head>
        <title>Productividad P.O.T.</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstRepPotProducto.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">PRODUCTIVIDAD P.O.T.</td>
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
                    <td width="34%" align="center" class="letraTitulo">DEL <%=fachadaColaboraReporteDctoBean.getFechaInicial()%>
                        AL <%=fachadaColaboraReporteDctoBean.getFechaFinal()%></td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">ORDEN</td>
                    <td width="5%" align="left" class="letraTitulo">OPERACION</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.TER</td>
                    <td width="5%" align="right" class="letraTitulo">PES.TER</td>
                    <td width="5%" align="left" class="letraTitulo">OPERARIO</td>
                    <td width="5%" align="right" class="letraTitulo">#MINUTOS</td>
                    <td width="5%" align="left" class="letraTitulo">MAQUINA</td>
                    <td width="5%" align="left" class="letraTitulo">PES.RETAL</td>
                    <td width="5%" align="left" class="letraTitulo">CAUSA RETAL</td>
                </tr>

                <lst:listaOTProductivo idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                idTipoOrdenTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenStr()%>"
                fechaInicioTag="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"
                fechaFinTag="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>">
                    <tr>
                        <td width="5%" align="right" class="letraDetalle"><%=numeroOrdenVar%>-<%=itemPadreVar%></td>
                        <td width="5%" align="left" class="letraDetalle"><%=nombreOperacionVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=pesoTerminadoVar%></td>
                        <td width="5%" align="left" class="letraDetalle"><%=nombreOperarioVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadMinutoVar%></td>
                        <td width="5%" align="left" class="letraDetalle"><%=nombreItemVar%></td>
                        <td width="5%" align="left" class="letraDetalle"><%=pesoPerdidoVar%></td>
                        <td width="5%" align="left" class="letraDetalle"><%=nombreCausaVar%></td>
                    </tr>
                </lst:listaOTProductivo>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraDetalle">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>