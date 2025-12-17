<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaCuentaLocalDetalle" prefix="lsu" %>
<%@ taglib uri="/WEB-INF/tlds/listaCuentaLocalTotal" prefix="lst" %>

<jsp:useBean id="fachadaTerceroBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

<head>
    <title>CxC Detallada</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConClienteConsultarCxCConsolidada.jsp">

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">CXC DETALLADA</td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
    <tr>
        <td width="33%" class="letraResaltadaTitulo">
            <jsp:include page="./comboLocal.jsp"/>
        </td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">
            <jsp:include page="./comboFechaHoy.jsp"/>
        </td>
    </tr>

    <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                        idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
        <tr>
        <td width="33%" align="center" class="letraDetalle">ESTADO CLIENTE</td>
        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
        </tr>

        <tr>
            <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
            <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
            <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
        </tr>
    </lista:listaClienteControlAgendaNit>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="25%" align="center" class="letraTitulo">#DCTO</td>
        <td width="25%" align="center" class="letraTitulo">FECHA VCTO</td>
        <td width="25%" align="right" class="letraTitulo">DIAS</td>
        <td width="25%" align="right" class="letraTitulo">SALDO</td>
    </tr>

    <lsu:listaCuentaLocalDetalle idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                 idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                                 idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                 idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>">

        <tr>
            <td width="25%" align="center" class="<%=letraEstiloVar%>"><%=idDctoVar%></td>
            <td width="25%" align="center" class="<%=letraEstiloVar%>"><%=fechaVencimientoVar%></td>
            <td width="25%" align="right" class="<%=letraEstiloVar%>"><%=diasMoraVar%></td>
            <td width="25%" align="right" class="<%=letraEstiloVar%>"><%=vrSaldoVar%></td>
        </tr>

    </lsu:listaCuentaLocalDetalle>


    <lst:listaCuentaLocalTotal idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                               idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                               idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                               idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>">
        <tr>
            <td width="25%" align="left" class="letraDetalle">TOTALES </td>
            <td width="25%" align="left" class="letraDetalle"><%=tipoCarteraVar%></td>
            <td width="25%" align="right" class="letraDetalle"><%=numeroDctosVar%></td>
            <td width="25%" align="right" class="letraDetalle"><%=vrSaldoVar%></td>
        </tr>
    </lst:listaCuentaLocalTotal>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">
            <input type="submit" value="Regresar" name="accionContenedor">
        </td>
        <td width="34%" align="center" class="letraTitulo">
            <input type="submit" value="Listar" name="accionContenedor">
        </td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
</table>
</form>
</body>
</html>