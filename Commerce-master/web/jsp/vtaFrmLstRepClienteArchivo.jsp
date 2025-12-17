<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaVentaCliente" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

    <head>
        <title>Ventas x Detalle</title>
    </head>
    <body>

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">REPORTE DETALLE CLIENTE</td>
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
                <td width="5%" align="right" class="letraTitulo">NIT/CC</td>
                <td width="25%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
                <td width="5%" align="center" class="letraTitulo">FEC.FRA</td>
                <td width="5%" align="right" class="letraTitulo">O.T.</td>
                <td width="30%" align="left" class="letraTitulo">REFERENCIA CLIENTE</td>
                <td width="5%" align="right" class="letraTitulo">CANTIDAD</td>
          <td width="5%" align="right" class="letraTitulo">KG</td>
                <td width="5%" align="right" class="letraTitulo">VR.FRA.</td>

            </tr>

            <lst:listaVentaCliente 
                            idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                            idTipoOrdenInicialTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenINIStr()%>"
                            idTipoOrdenFinalTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenFINStr()%>"
                            indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
                            fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"
                            fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>">
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=idDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idClienteVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=fechaDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=numeroOrdenVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=referenciaClienteVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=cantidadVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=kgVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrFacturaVar%></td>

                </tr>

            </lst:listaVentaCliente>
        </table>
    </body>
</html>
</html>