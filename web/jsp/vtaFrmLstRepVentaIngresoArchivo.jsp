<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaRecaudo" prefix="lst" %>

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
                <td width="5%" align="right" class="letraTitulo">IDCLIENTE</td>
                <td width="30%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
                <td width="5%" align="right" class="letraTitulo">DCTO</td>
                <td width="5%" align="right" class="letraTitulo">#RECIBO</td>
                <td width="5%" align="center" class="letraTitulo">FEC.DCTO</td>
                <td width="5%" align="right" class="letraTitulo">V.PAGO</td>
                <td width="5%" align="right" class="letraTitulo">V.RTEFTE</td>
                <td width="5%" align="right" class="letraTitulo">V.DSCTO</td>
                <td width="5%" align="right" class="letraTitulo">V.RTEICA</td>
                <td width="5%" align="right" class="letraTitulo">V.RTEIVA</td>
                <td width="5%" align="right" class="letraTitulo">V.RTECREE</td>                
                <td width="5%" align="left" class="letraTitulo">USR</td>
                <td width="5%" align="center" class="letraTitulo">HORA</td>

            </tr>

            <lst:listaRecaudo idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                            idTipoOrdenCadenaTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenCadena()%>"
                            indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
                            idVendedorTag="<%=fachadaColaboraReporteDctoBean.getIdVendedorStr()%>"
                            fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"
                            fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>">
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=nitCCVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=idDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idReciboVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=fechaPagoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrPagoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrRteFuenteVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrDescuentoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrRteIcaVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=vrRteIvaVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=vrRteCreeVar%></td>                    
                    <td width="10%" align="left" class="letraDetalle"><%=aliasUsuarioVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=horaPagoVar%></td>
                </tr>

            </lst:listaRecaudo>
        </table>
    </body>
</html>
</html>