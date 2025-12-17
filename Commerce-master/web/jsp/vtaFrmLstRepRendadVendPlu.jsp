<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaAudtoriaRepRentabilidadVendPlu" prefix="lst" %>

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
                <td width="45%" class="letraTitulo">
                    <jsp:include page="./comboLocal.jsp"/>
                </td>
                <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
                <td width="45%" class="letraTitulo">
                    <jsp:include page="./comboFechaHoy.jsp"/>
                </td>
            </tr>

            <tr>
                <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
                <td width="45%" align="center" class="letraTitulo">DEL <%=fachadaColaboraReporteDctoBean.getFechaInicial()%>
                AL <%=fachadaColaboraReporteDctoBean.getFechaFinal()%></td>
                <td width="45%" align="center" class="letraTitulo">&nbsp;</td>
            </tr>
            <td width="45%" align="center" class="letraTitulo">&nbsp;</td>
        </table>

        <table border="2" width="90%" id="tablaTitulo">
            <tr bgcolor="green">
            <b><td width="5%" align="left" class="letraTitulo">ALIAS</td>
                <td width="35%" align="left" class="letraTitulo">NOM.PLU</td>
                <td width="35%" align="leftt" class="letraTitulo">NOM.MARCA</td>
                <td width="5%" align="right" class="letraTitulo">CANTIDAD</td>
                <td width="5%" align="right" class="letraTitulo">VR.BASE</td>
                <td width="5%" align="right" class="letraTitulo">COSTO</td>
                <td width="5%" align="right" class="letraTitulo">COSTO.IND</td>
                <td width="5%" align="right" class="letraTitulo">%MARGEN</td>
                </b>
            </tr>

            <lst:listaAudtoriaRepRentabilidadVendPlu
                            idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                            fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"
		            fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>"
                            indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
                            idTipoOrdenInicialTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenINIStr()%>"
                            idTipoOrdenFinalTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenFINStr()%>">

                <tr>
                    <td width="5%" align="left" class="letraDetalle"><%=aliasUsuarioVar%></td>
                    <td width="35%" align="left" class="letraDetalle"><%=nombrePluVar%></td>
                    <td width="35%" align="right" class="letraDetalle"><%=nombreMarcaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=cantidadVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrBaseVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrIvaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoMvVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoIndVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=margenIndVar%></td>
               </tr>

            </lst:listaAudtoriaRepRentabilidadVendPlu>
        </table>
    </body>
</html>
