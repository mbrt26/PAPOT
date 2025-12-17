<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaCxCDetalle" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

<head>
    <title>CxP Detallada</title>
</head>
<body>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">CXP DETALLADA</td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
    <tr>
        <td width="33%" class="letraResaltadaTitulo">
            <jsp:include page="./comboLocal.jsp"/>
        </td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">FECHA CORTE
            <%=fachadaColaboraReporteDctoBean.getFechaCorte()%>
        </td>
    </tr>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="5%" align="left" class="letraTitulo">NIT/CC</td>
        <td width="30%" align="left" class="letraTitulo">NOMBRE PROVEEDOR</td>
        <td width="10%" align="center" class="letraTitulo">#DCTO</td>
        <td width="10%" align="center" class="letraTitulo">FECHA DCTO</td>
        <td width="10%" align="center" class="letraTitulo">FECHA VCTO</td>
        <td width="10%" align="right" class="letraTitulo">EDAD</td>
        <td width="10%" align="right" class="letraTitulo">DIAS</td>
        <td width="10%" align="right" class="letraTitulo">SALDO</td>
        <td width="5%" align="left" class="letraTitulo">VEN</td>
    </tr>

    <lst:listaCxCDetalle idLocalTag = "<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
              idTipoOrdenTag = "<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenStr()%>"
         indicadorInicialTag = "<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
           indicadorFinalTag = "<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
           idVendedorTag  =  "<%=fachadaColaboraReporteDctoBean.getIdVendedorStr()%>"
           fechaCorteTag  = "<%=fachadaColaboraReporteDctoBean.getFechaCorte()%>">

    <tr>
        <td width="5%" align="left" class="letraTitulo"><%=idClienteVar%></td>
        <td width="40%" align="left" class="letraTitulo"><%=nombreTerceroVar%></td>
        <td width="10%" align="center" class="letraTitulo"><%=idDctoNitCCVar%></td>
        <td width="10%" align="center" class="letraTitulo"><%=fechaDctoVar%></td>
        <td width="10%" align="right" class="letraTitulo"><%=fechaVencimientoVar%></td>
        <td width="10%" align="right" class="letraTitulo"><%=edadFraVar%></td>
        <td width="10%" align="right" class="letraTitulo"><%=diasMoraVar%></td>
        <td width="10%" align="right" class="letraTitulo"><%=vrSaldoVar%></td>
        <td width="5%" align="left" class="letraTitulo"><%=nombreVendedorVar%></td>
    </tr>

    </lst:listaCxCDetalle>

</table>
</body>
</html>