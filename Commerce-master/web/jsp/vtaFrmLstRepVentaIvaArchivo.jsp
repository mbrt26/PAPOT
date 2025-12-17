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
                <td width="5%" align="right" class="letraTitulo"><strong>DCTO</strong></td>
                <td width="5%" align="right" class="letraTitulo"><b>IDCLIENTE</b></td>
                <td width="30%" align="left" class="letraTitulo"><b>NOMBRE CLIENTE</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>F.DCTO</b></td>
                <td width="5%" align="right" class="letraTitulo"><b>VR.BASE</b></td>
                <td width="5%" align="right" class="letraTitulo"><b>VR.IVA</b></td>
                <td width="5%" align="right" class="letraTitulo"><b>VR.RTEIVA</b></td>
                <!--td width="5%" align="right" class="letraTitulo"><b>VR.RTECREE</b></td-->                
                <td width="5%" align="right" class="letraTitulo"><b>VR.FRA.</b></td>
                <td width="5%" align="right" class="letraTitulo"><b>V.DSCTO</b></td>
                <td width="10%" align="right" class="letraTitulo"><b>V.RTEFTE</b></td>
                <td width="10%" align="left" class="letraTitulo"><b>NEG</b></td>
                <td width="5%" align="left" class="letraTitulo"><b>USR</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>HORA</b></td>

            </tr>

            <lst:listaVenta
                            idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
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
                    <td width="5%" align="right" class="letraDetalle"><%=vrRteIvaVar%></td>
                    <!--td width="5%" align="right" class="letraDetalle"><></td-->                    
                    <td width="5%" align="right" class="letraDetalle"><%=vrFacturaVentaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrDescuentoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrRteFuenteVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=nombreTipoNegocioVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=aliasUsuarioVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=horaTxVar%></td>

                </tr>

            </lst:listaVenta>
        </table>
    </body>
</html>
</html>