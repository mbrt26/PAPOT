<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaVenta" prefix="lst" %>

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
                <td width="5%" align="right" class="letraTitulo">CAJ#</td>
                <td width="5%" align="right" class="letraTitulo">#FRA</td>
                <td width="5%" align="center" class="letraTitulo">NIT</td>
                <td width="30%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
                <td width="5%" align="center" class="letraTitulo">F.FRA</td>
                <td width="5%" align="right" class="letraTitulo">VR.BASE</td>
                <td width="5%" align="right" class="letraTitulo">VR.IVA</td>
                <td width="5%" align="right" class="letraTitulo">VR.IMP</td>
                <td width="5%" align="right" class="letraTitulo">VR.FRA.</td>
                <td width="5%" align="right" class="letraTitulo">V.DSCTO</td>
                <td width="10%" align="right" class="letraTitulo">V.RTEFTE</td>
                <td width="10%" align="left" class="letraTitulo">NEG</td>
                <td width="10%" align="left" class="letraTitulo">USR</td>
                <td width="5%" align="left" class="letraTitulo">PREFIJO</td>
                <td width="5%" align="center" class="letraTitulo">HORA</td>

            </tr>

            <lst:listaVenta idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                            idTipoOrdenCadenaTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenCadena()%>"
                            indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
                            idVendedorTag="<%=fachadaColaboraReporteDctoBean.getIdVendedorStr()%>"
                            fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicialSqlServer()%>"
                            fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinalSqlServer()%>">
                <tr>

                    <td width="5%" align="right" class="letraDetalle"><%=idCajaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idClienteVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=fechaDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrBaseVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrIvaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrImpoconsumoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrFacturaVentaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrDescuentoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrRteFuenteVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=nombreTipoNegocioVar%></td>
                    <td width="5%" align="left" class="letraDetalle"><%=aliasUsuarioVar%></td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;<%=prefijoVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=horaTxVar%></td>

                </tr>

            </lst:listaVenta>
        </table>
    </body>
</html>
</html>