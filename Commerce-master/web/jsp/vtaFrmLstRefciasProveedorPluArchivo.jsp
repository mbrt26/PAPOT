<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaMargenPlu" prefix="lst" %>

<jsp:useBean id="fachadaColaboraReporteDctoBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

<head>
    <title>Reporte Margen Venta Plu</title>
</head>
<body>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">REPORTE MARGEN VENTA PLU</td>
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
            <td width="5%" align="right" class="letraTitulo">PLU</td>
            <td width="30%" align="left" class="letraTitulo">NOMBRE PLU</td>
            <td width="15%" align="left" class="letraTitulo">MARCA</td>
            <td width="5%" align="right" class="letraTitulo">%IVA</td>
            <td width="5%" align="right" class="letraTitulo">CANT</td>
            <td width="5%" align="right" class="letraTitulo">VTA.CON.IVA</td>
            <td width="5%" align="right" class="letraTitulo">VTA.SIN.IVA</td>
            <td width="5%" align="right" class="letraTitulo">IVA.VTA.</td>
            <td width="5%" align="right" class="letraTitulo">CTO.IND</td>
            
            </tr>

    <lst:listaMargenPlu idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
               idTipoOrdenCadenaTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenCadena()%>"
                      idVendedorTag="<%=fachadaColaboraReporteDctoBean.getIdVendedorStr()%>"
                    fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicialSqlServer()%>"
                      fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinalSqlServer()%>"
                indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                  indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>">
            <tr>
            <td width="5%" align="right" class="letraDetalle"><%=idPluVar%></td>
            <td width="30%" align="left" class="letraDetalle"><%=nombrePluVar%></td>
            <td width="15%" align="left" class="letraDetalle"><%=nombreMarcaVar%></td>
            <td width="5%" align="right" class="letraDetalle"><%=porcentajeIvaVar%></td>
            <td width="5%" align="right" class="letraDetalle"><%=cantidadVar%></td>
            <td width="5%" align="right" class="letraDetalle"><%=vrVentaConIvaVar%></td>
            <td width="5%" align="right" class="letraDetalle"><%=vrVentaSinIvaVar%></td>
            <td width="5%" align="right" class="letraDetalle"><%=vrIvaVentaVar%></td>
            <td width="5%" align="right" class="letraDetalle"><%=vrCostoINDVar%></td>
            
            </tr>
    </lst:listaMargenPlu>
</table>


</body>
</html>