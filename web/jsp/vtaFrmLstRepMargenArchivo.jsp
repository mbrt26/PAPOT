<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaMargen" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

    <head>
        <title>Ventas Detalle Margen</title>
    </head>
    <body>

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">VENTAS DETALLE MARGEN</td>
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
                <td width="5%" align="right" class="letraTitulo">DCTO</td>
                <td width="5%" align="right" class="letraTitulo">IDCLIENTE</td>
                <td width="30%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
                <td width="5%" align="center" class="letraTitulo">F.DCTO</td>
                <td width="5%" align="right" class="letraTitulo">VR.BASE</td>
                <td width="5%" align="right" class="letraTitulo">VR.IVA</td>
                <td width="5%" align="right" class="letraTitulo">VR.FRA.</td>
                <td width="5%" align="right" class="letraTitulo">COSTO</td>
                <td width="5%" align="right" class="letraTitulo">CTO.IND</td>
                <td width="5%" align="right" class="letraTitulo">%MARGEN</td>
            </tr>

            <lst:listaMargen idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                            idTipoOrdenCadenaTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenCadena()%>"
                            indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
                            idVendedorTag="<%=fachadaColaboraReporteDctoBean.getIdVendedorStr()%>"
                            fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicialSqlServer()%>"
                            fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinalSqlServer()%>">
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=idDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idClienteVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=fechaDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrBaseVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrIvaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrFacturaVentaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoMVDf0Var%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoINDDf0Var%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=margenINDPorcentajeVar%></td>
                </tr>
            </lst:listaMargen>
        </table>
    </body>
</html>
</html>