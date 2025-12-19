<html>

<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
<%@ taglib uri="/WEB-INF/tlds/listaClienteCotizacionArticulosRetirar" prefix="lsr" %>
<jsp:useBean id="fachadaAgendaLogVisitaBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

<jsp:useBean id="fachadaTerceroBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

<%
        String xIdLog = request.getParameter("idLog");
        String xItem = request.getParameter("item");
%>

<head>
    <title>Confirmar Retiro</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<body>

<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConEmpresaConfirmaRetiraPlu.jsp">
<input type="hidden" name="item" value="<%=xItem%>">
<input type="hidden" name="idLog" value="<%=xIdLog%>">

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">CONFIRMAR RETIRO</td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">
            <jsp:include page="./comboFechaHoy.jsp"/>
        </td>
    </tr>

    <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                      idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">

        <td width="33%" align="center" class="letraDetalle">ESTADO CLIENTE</td>
        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
        </tr>

        <tr>
            <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
            <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
            <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
        </tr>
    </lst:listaClienteControlAgendaNit>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="25%" align="center" class="letraTitulo">REFERENCIA</td>
        <td width="25%" align="left" class="letraTitulo">NOMBRE RFCIA</td>
        <td width="25%" align="right" class="letraTitulo">#CANTIDAD</td>
        <td width="25%" align="right" class="letraTitulo">$VR.VENTA</td>
    </tr>
    <lsr:listaClienteCotizacionArticulosRetirar idLocalTag="<%=fachadaAgendaLogVisitaBean.getIdLocalStr()%>"
                                                 idLogTag= "<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">
        <tr>
            <td width="25%" align="center" class="letraDetalle"><%=strIdReferenciaVar%></td>
            <td width="25%" align="left" class="letraDetalle"><%=nombrePluVar%></td>
            <td width="25%" align="right" class="letraDetalle"><%=cantidadVar%></td>
            <td width="25%" align="right" class="letraDetalle"><%=vrVentaUnitarioVar%></td>
        </tr>

    </lsr:listaClienteCotizacionArticulosRetirar>

</table>

<table border="0" width="90%" id="tablaTitulo">
<tr>
    <td width="33%" class="letraTitulo">
        <input type="submit" value="Confirmar Retiro" name="accionContenedor">
    </td>
    <td width="34%" align="center" class="letraTitulo">
        <input type="submit" value="Regresar" name="accionContenedor">
    </td><td width="33%" class="letraTitulo">&nbsp;</td>
</tr>
</table>

</form>
</body>

<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

</html>