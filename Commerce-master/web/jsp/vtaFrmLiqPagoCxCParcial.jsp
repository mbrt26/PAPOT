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
             
<jsp:useBean id="fachadaDctoBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoBean" />

<jsp:useBean id="fachadaDctoPagoBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoBean" />

<head>
    <title>Pago CxC</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLiqPagoCxCParcial.jsp">
<input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoBean.getIdTipoOrdenStr()%>">
<input type="hidden" name="xIndicador" value="<%=fachadaDctoBean.getIndicadorStr()%>">
<input type="hidden" name="xIdDcto" value="<%=fachadaDctoBean.getIdDctoStr()%>">
<input type="hidden" name="xIdLocal" value="<%=fachadaDctoBean.getIdLocalStr()%>">

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">PAGO CXC</td>
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
        <td width="10%" align="center" class="letraTitulo">#DCTO</td>
        <td width="10%" align="right" class="letraTitulo">SALDO</td>
        <td width="10%" align="right" class="letraTitulo">VR.RECIBIDO</td>
        <td width="10%" align="right" class="letraTitulo">VR.DSCTO</td>
        <td width="10%" align="right" class="letraTitulo">VR.RTEFTE</td>
        <td width="10%" align="right" class="letraTitulo">VR.RTEIVA</td>
        <td width="10%" align="right" class="letraTitulo">VR.RTEICA</td>
        <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
    </tr>

        <tr>
            <td width="10%" align="center" class="letraTitulo"><%=fachadaDctoPagoBean.getIdDcto()%></td>
            <td width="10%" align="right" class="letraTitulo"><%=fachadaDctoPagoBean.getVrSaldoDf0()%></td>
            <td width="10%" align="right" class="letraTitulo">
                <input type="text" value="0" name="xVrPago" size="10" tabindex="2">
            </td>
            <td width="10%" align="right" class="letraTitulo">
                <input type="text" value="0" name="xVrDescuento" size="10">
            </td>
            <td width="10%" align="right" class="letraTitulo">
                <input type="text" value="0" name="xVrRteFuente" size="10">
            </td>
            <td width="10%" align="right" class="letraTitulo">
                <input type="text" value="0" name="xVrRteIva" size="10">
            </td>
            <td width="10%" align="right" class="letraTitulo">
                <input type="text" value="0" name="xVrRteIca" size="10">
            </td>
            <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
            <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
        </tr>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">
            <input type="submit" value="Salir" name="accionContenedor">
        </td>
        <td width="34%" align="center" class="letraTitulo">
            <input type="submit" value="Guardar" name="accionContenedor">
        </td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
</table>
</form>
</body>
</html>