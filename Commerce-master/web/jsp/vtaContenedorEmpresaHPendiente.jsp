<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaHistoria" prefix="listaHistoria" %>

<jsp:useBean id="fachadaTerceroBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />


<head>
    <title>Historico Pendientes</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorEmpresaHPendiente.jsp">

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">HISTORICO PENDIENTES</td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">
            <jsp:include page="./comboFechaHoy.jsp"/>
        </td>
    </tr>

    <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
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
    </lista:listaClienteControlAgendaNit>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="14%" align="center" class="letraTitulo">#RFCIA</td>
        <td width="16%" align="left" class="letraTitulo">NOMBRE RFCIA</td>
        <td width="14%" align="left" class="letraTitulo">#ORDEN VENTA</td>
        <td width="14%" align="center" class="letraTitulo">FECHA ORDEN </td>
        <td width="14%" align="left" class="letraTitulo">#ORDEN COMPRA</td>
        <td width="14%" align="right" class="letraTitulo">CANTIDAD PEDIDA</td>
        <td width="14%" align="right" class="letraTitulo">UND.MEDIDA</td>
    </tr>

    <listaHistoria:listaHistoria idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>">
        <tr>
            <td width="14%" align="center" class="letraDetalle"><%=strIdReferenciaVar.trim()%></td>
            <td width="16%" align="left" class="letraDetalle"><%=nombrePluVar.trim()%></td>
            <td width="14%" align="left" class="letraDetalle"><%=numeroOrdenVar%></td>
            <td width="14%" align="center" class="letraDetalle"><%=fechaOrdenVar%></td>
            <td width="14%" align="left" align="center" class="letraDetalle"><%=ordenCompraVar%></td>
            <td width="14%" align="right" class="letraDetalle"><%=cantidadVar%></td>
            <td width="14%" align="right" class="letraDetalle"><%=strUnidadMedidaVar%></td>
        </tr>
    </listaHistoria:listaHistoria>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo"><input type="submit" value="Salir" name="accionContenedor"></td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
</table>

</form>
</body>
</html>