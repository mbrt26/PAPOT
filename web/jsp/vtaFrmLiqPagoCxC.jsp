<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@page import="java.util.*"%>
<%@page import="com.solucionesweb.losbeans.fachada.FachadaPagoBean"%>

<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaCuentaDetalladoCliente" prefix="listaCxc" %>
<%@ taglib uri="/WEB-INF/tlds/listaCuentaConsolidadaCliente" prefix="listaCxcResumen" %>

<jsp:useBean id="fachadaTerceroBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

<jsp:useBean id="fachadaPagoBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaPagoBean" />

<jsp:useBean id="fachadaPagoTotalBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaPagoBean" />

<%
        //
        Vector vecPagos = (Vector) request.getAttribute("vecPagos");

        //
        Iterator iteratorBean = vecPagos.iterator();
%>

<head>
    <title>CxC Consolidada x Nit</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLiqPagoCxC.jsp">

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">PAGO CXC</td>
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
        <td width="10%" align="center" class="letraTitulo">#DCTO</td>
        <td width="10%" align="right" class="letraTitulo">SALDO</td>
        <td width="10%" align="right" class="letraTitulo">VR.RECIBIDO</td>
        <td width="10%" align="right" class="letraTitulo">VR.DSCTO</td>
        <td width="10%" align="right" class="letraTitulo">VR.RTEFTE</td>
        <td width="10%" align="right" class="letraTitulo">VR.RTEIVA</td>
        <td width="10%" align="right" class="letraTitulo">DIFERENCIA</td>
        <td width="10%" align="center" class="letraTitulo">FECHA VCTO</td>
        <td width="10%" align="right" class="letraTitulo">DIAS</td>
    </tr>

    <%
        while (iteratorBean.hasNext()) {

            fachadaPagoBean = (FachadaPagoBean) iteratorBean.next();%>

    <input type="hidden" name="xIdDcto" value=<%=fachadaPagoBean.getIdDctoStr()%>>
    <input type="hidden" name="xVrSaldo" value=<%=fachadaPagoBean.getVrSaldoStr()%>>
    <input type="hidden" name="xFechaVencimiento" value=<%=fachadaPagoBean.getFechaVencimiento()%>>
    <input type="hidden" name="xDiasMora" value=<%=fachadaPagoBean.getDiasMoraStr()%>>
    <input type="hidden" name="xIndicador" value=<%=fachadaPagoBean.getIndicadorStr()%>>
    <input type="hidden" name="xIdTipoOrden" value=<%=fachadaPagoBean.getIdTipoOrdenStr()%>>
    <input type="hidden" name="xIdLocal" value=<%=fachadaPagoBean.getIdLocalStr()%>>

    <tr>
        <td width="10%" align="center" class="letraTitulo"><%=fachadaPagoBean.getIdDctoStr()%></td>
        <td width="10%" align="right" class="letraTitulo"><%=fachadaPagoBean.getVrSaldoStrFormatDf0()%></td>
        <td width="10%" align="right" class="letraTitulo">
            <input type="text" value="<%=fachadaPagoBean.getVrPagoStrSF()%>" name="xVrPago" size="10" tabindex="1">
        </td>
        <td width="10%" align="right" class="letraTitulo">
            <input type="text" value="<%=fachadaPagoBean.getVrDescuentoStrSF()%>" name="xVrDescuento" size="10" tabindex="1">
        </td>
        <td width="10%" align="right" class="letraTitulo">
            <input type="text" value="<%=fachadaPagoBean.getVrRteFuenteStrSF()%>" name="xVrRteFuente" size="10">
        </td>
        <td width="10%" align="right" class="letraTitulo">
            <input type="text" value="<%=fachadaPagoBean.getVrRteIvaStrSF()%>" name="xVrRteIva" size="10">
        </td>
        <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
        <td width="10%" align="center" class="letraTitulo"><%=fachadaPagoBean.getFechaVencimiento()%></td>
        <td width="10%" align="right" class="letraTitulo"><%=fachadaPagoBean.getDiasMoraStr()%></td>
    </tr>

    <% }%>

    <tr>
        <td width="10%" align="center" class="letraTitulo">TOTAL</td>
        <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="10%" align="right" class="letraTitulo"><%=fachadaPagoTotalBean.getVrTotalPagoDf0()%></td>
        <td width="10%" align="right" class="letraTitulo"> <%=fachadaPagoTotalBean.getVrTotalDescuentoDf0()%></td>
        <td width="10%" align="right" class="letraTitulo"><%=fachadaPagoTotalBean.getVrTotalRteFuenteDf0()%></td>
        <td width="10%" align="right" class="letraTitulo"><%=fachadaPagoTotalBean.getVrTotalRteIvaDf0()%></td>
        <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
        <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
        <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
    </tr>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">
            <input type="submit" value="Salir" name="accionContenedor">
        </td>
        <td width="34%" align="center" class="letraTitulo">
            <input type="submit" value="Liquidar" name="accionContenedor">
        </td>
        <td width="33%" class="letraTitulo">
          <input type="submit" value="Pagar" name="accionContenedor">
            </td>
    </tr>
</table>
</form>
</body>
</html>