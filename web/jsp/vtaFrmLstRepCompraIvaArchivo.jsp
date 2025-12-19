<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaCompra" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

    <head>
        <title>Compras x Detalle</title>
    </head>
    <body>

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">COMPRAS DETALLE</td>
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
                <td width="5%" align="right" class="letraTitulo">IDPROVEEDOR</td>
                <td width="30%" align="left" class="letraTitulo">NOMBRE PROVEEDOR</td>
                <td width="5%" align="center" class="letraTitulo">F.DCTO</td>
                <td width="5%" align="right" class="letraTitulo">VR.BASE</td>
                <td width="5%" align="right" class="letraTitulo">VR.IVA</td>
                <td width="5%" align="right" class="letraTitulo">VR.IMP</td>
                <td width="5%" align="right" class="letraTitulo">VR.FRA.</td>
                <td width="5%" align="right" class="letraTitulo">V.DSCTO</td>
                <td width="10%" align="right" class="letraTitulo">V.RTEFTE</td>
                <td width="10%" align="right" class="letraTitulo">V.RTEIVA</td>
                <td width="10%" align="right" class="letraTitulo">V.RTECREE</td>                
                <td width="10%" align="left" class="letraTitulo">NEG</td>
                <td width="5%" align="left" class="letraTitulo">USR</td>
                

            </tr>

            <lst:listaCompra idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                            idTipoOrdenCadenaTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenCadena()%>"
                            indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
                            idVendedorTag="<%=fachadaColaboraReporteDctoBean.getIdVendedorStr()%>"
                            fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicialSqlServer()%>"
                            fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinalSqlServer()%>">
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=idDctoNitCCVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idClienteVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=fechaDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrBaseVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrIvaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrImpoconsumoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrFacturaVentaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrDescuentoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrRteFuenteVar%></td>                    
                    <td width="5%" align="right" class="letraDetalle"><%=vrRteIvaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrRteCreeVar%></td>                                        
                    <td width="10%" align="left" class="letraDetalle"><%=nombreTipoNegocioVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=aliasUsuarioVar%></td>
                </tr>

            </lst:listaCompra>
        </table>
    </body>
</html>
</html>