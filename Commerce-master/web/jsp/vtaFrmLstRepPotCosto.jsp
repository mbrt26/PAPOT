<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaOTCosto" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

    <head>
        <title>Costos P.O.T.</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstRepPotCosto.jsp">

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">COSTOS P.O.T.</td>
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
                <td width="5%" align="left" class="letraTitulo">CLIENTE</td>
                <td width="5%" align="center" class="letraTitulo">FEC.PED</td>
                <td width="5%" align="left" class="letraTitulo">OPERACION</td>
                <td width="5%" align="right" class="letraTitulo">COSTO MAT</td>
                <td width="5%" align="right" class="letraTitulo">COSTO CIF</td>
                <td width="5%" align="right" class="letraTitulo">COSTO MOD</td>
                <td width="5%" align="right" class="letraTitulo">KG.TER</td>
            </tr>

            <lst:listaOTCosto idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                              idTipoOrdenTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenStr()%>"
                              fechaInicioTag="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"
                              fechaFinTag="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>">

                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=numeroOrdenVar%>-<%=itemPadreVar%></td>
                    <td width="5%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=fechaOrdenVar%></td>
                    <td width="5%" align="left" class="letraDetalle"><%=nombreOperacionVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoBaseMATVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoBaseCIFVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoMODVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=pesoTerminadoVar%></td>
                </tr>

            </lst:listaOTCosto>
        </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
              </form>
    </body>
</html>
</html>