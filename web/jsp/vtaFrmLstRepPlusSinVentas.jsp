<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaAudtoriaRepSinVentas" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />

    <head>
        <title>Ventas x Detalle</title>
    </head>
    <body>

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">VENTAS DETALLE</td>
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
                <td width="5%" align="center" class="letraTitulo">PLU</td>
                <td width="30%" align="left" class="letraTitulo">NOMBRE PLU</td>
                <td width="5%" align="right" class="letraTitulo">VR.GENERAL</td>
                <td width="5%" align="right" class="letraTitulo">VR.MAYORISTA</td>
                <td width="5%" align="right" class="letraTitulo">VR.COSTO</td>
                <td width="5%" align="right" class="letraTitulo">VR.COSTO IND</td>
                <td width="5%" align="right" class="letraTitulo">F.DESPACHO</td>
            </tr>

            <lst:listaAudtoriaRepSinVentas
                           idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                            fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicialSqlServer()%>"
		            fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinalSqlServer()%>"
                            indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
                            idTipoOrdenInicialTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenINIStr()%>"
                            idTipoOrdenFinalTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenFINStr()%>">
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=idPluVar%></td>
                    <td width="30%" align="right" class="letraDetalle"><%=nombrePluVar%></td>
                    <td width="5%" align="left" class="letraDetalle"><%=vrGeneralVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=vrMayoristaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoIndVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=factorDespachoVar%></td>
                 </tr>

            </lst:listaAudtoriaRepSinVentas>
        </table>
    </body>
</html>
