<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaAudtoriaRepPluRotacion" prefix="lst" %>

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
                <td width="30%" align="left" class="letraTitulo">NOMBRE PLU</td>
                <td width="5%" align="center" class="letraTitulo">PLU</td>
                <td width="5%" align="right" class="letraTitulo">VR.COSTO</td>
                <td width="5%" align="right" class="letraTitulo">FD</td>
                <td width="5%" align="right" class="letraTitulo">CANT</td>
                <td width="5%" align="right" class="letraTitulo">VR.VENTA</td>
                <td width="5%" align="right" class="letraTitulo">ROTACION</td>
            </tr>

            <lst:listaAudtoriaRepPluRotacion
                            idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                            idTipoOrdenTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenStr()%>"
                            fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicialSqlServer()%>"
		            fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinalSqlServer()%>"
                            idBodegaTag="<%=fachadaColaboraReporteDctoBean.getIdBodegaStr()%>">
                <tr>
                    <td width="30%" align="right" class="letraDetalle"><%=nombrePluVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idPluVar%></td>
                    <td width="5%" align="left" class="letraDetalle"><%=vrCostoVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=factorDespachoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=cantidadVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrVentaUnitarioVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=rotacionVar%></td>
                 </tr>

            </lst:listaAudtoriaRepPluRotacion>
        </table>
    </body>
</html>
</html>