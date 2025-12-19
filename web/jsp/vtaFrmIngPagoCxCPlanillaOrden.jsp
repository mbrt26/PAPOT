<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaCuentaLocalDetalleOrden" prefix="lsu" %>
<%@ taglib uri="/WEB-INF/tlds/listaCuentaLocalTotal" prefix="lst" %>

<jsp:useBean id="fachadaTerceroBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

<jsp:useBean id="fachadaPagoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPagoBean" />

<head>
    <title>CxC Consolidada x Nit</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngPagoCxCPlanillaOrden.jsp">
<input type="hidden" name="xIdLocal" value="<%=fachadaTerceroBean.getIdLocalStr()%>">
<input type="hidden" name="xIdVendedor" value="<%=fachadaPagoBean.getIdVendedorDi0()%>">
<input type="hidden" name="xFechaPago" value="<%=fachadaPagoBean.getFechaPagoCorta()%>">

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
        <td width="5%" align="center" class="letraTitulo">#DCTO</td>
        <td width="5%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="5%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="10%" align="right" class="letraTitulo">SALDO</td>
        <td width="10%" align="right" class="letraTitulo">VR.RECIBIDO</td>
        <td width="10%" align="right" class="letraTitulo">VR.DSCTO</td>
        <td width="10%" align="right" class="letraTitulo">VR.RTEFTE</td>
        <td width="10%" align="right" class="letraTitulo">VR.RTEIVA</td>
        <td width="10%" align="right" class="letraTitulo">VR.RTEICA</td>
        <td width="10%" align="right" class="letraTitulo">DFCIA</td>
        <td width="10%" align="center" class="letraTitulo">FECHA VCTO</td>
        <td width="5%" align="right" class="letraTitulo">DIAS</td>
    </tr>

    <lsu:listaCuentaLocalDetalleOrden idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                 idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                                 idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                 idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>"
                                 idOrdenTag="<%=fachadaPagoBean.getIdOrdenStr()%>">

    
        <input type="hidden" name="xIdDcto" value=<%=idDctoVar%>>
        <input type="hidden" name="xVrSaldo" value=<%=vrSaldoSFVar%>>
        <input type="hidden" name="xFechaVencimiento" value=<%=fechaVencimientoVar%>>
        <input type="hidden" name="xDiasMora" value=<%=diasMoraVar%>>
        <input type="hidden" name="xIndicador" value=<%=indicadorVar%>>
        <input type="hidden" name="xIdTipoOrden" value=<%=idTipoOrdenVar%>>
        <input type="hidden" name="xIdLocal" value=<%=idLocalVar%>>
        <input type="hidden" name="xIdOrden" value=<%=idOrdenVar%>>

        <tr>
            <td width="5%" align="center" class="letraDetalle"><%=idDctoVar%></td>
            <td width="5%" align="center" class="letraDetalle">
               <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmIngPagoCxCPlanillaOrden.jsp&accionContenedor=PagoParcial&xIdLocal=<%=fachadaTerceroBean.getIdLocalStr()%>&xIdTipoOrden=<%=idTipoOrdenVar%>&xIndicador=<%=indicadorVar%>&xIdCliente=<%=fachadaTerceroBean.getIdCliente()%>&xIdDcto=<%=idDctoVar%>&xIdOrden=<%=idOrdenVar%>&xIdVendedor=<%=fachadaPagoBean.getIdVendedorDi0()%>&xFechaPago=<%=fachadaPagoBean.getFechaPagoCorta()%>">Parcial</a>
            </td>
            <td width="5%" align="center" class="letraDetalle">
               <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmIngPagoCxCPlanillaOrden.jsp&accionContenedor=PagoTotal&xIdLocal=<%=fachadaTerceroBean.getIdLocalStr()%>&xIdTipoOrden=<%=idTipoOrdenVar%>&xIndicador=<%=indicadorVar%>&xIdCliente=<%=fachadaTerceroBean.getIdCliente()%>&xIdDcto=<%=idDctoVar%>&xIdOrden=<%=idOrdenVar%>&xIdVendedor=<%=fachadaPagoBean.getIdVendedorDi0()%>&xFechaPago=<%=fachadaPagoBean.getFechaPagoCorta()%>">Total</a>
            </td>
            <td width="10%" align="right" class="letraDetalle"><%=vrSaldoVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrPagoVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrDescuentoVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrRteFuenteVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrRteIvaVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrRteIcaVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrDiferenciaDf0Var%></td>
            <td width="10%" align="center" class="letraDetalle"><%=fechaVencimientoVar%></td>
            <td width="5%" align="right" class="letraDetalle"><%=diasMoraVar%></td>
        </tr>

    </lsu:listaCuentaLocalDetalleOrden>

    <lst:listaCuentaLocalTotal idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                               idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                               idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                               idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>">

        <tr>
            <td width="5%" align="center" class="letraDetalle">TOTAL</td>
            <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
            <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
            <td width="10%" align="center" class="letraDetalle"><%=numeroDctosVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrSaldoVar%></td>
            <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
            <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
            <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
            <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
            <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
            <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
            <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
        </tr>

    </lst:listaCuentaLocalTotal>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">
            <input type="submit" value="Salir" name="accionContenedor">
        </td>
        <td width="34%" align="center" class="letraTitulo">
            <input type="submit" value="Cambiar Cliente" name="accionContenedor">
        </td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
</table>
</form>
</body>
</html>
