<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<%@ taglib uri="/WEB-INF/tlds/listaPedidoPeriodoLocal" prefix="lsu" %>
<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>

<jsp:useBean id="fachadaColaboraLogisticaBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean" />

<jsp:useBean id="fachadaAgendaLogVisitaBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />
<jsp:useBean id="fachadaTerceroBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

<head>
    <title>Historico Pedidos</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<body>
<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstEmpresaHRemesa.jsp">

<input type="hidden" name="xIdTipoOrden" value="<%=fachadaColaboraLogisticaBean.getIdTipoOrdenStr()%>">
<input type="hidden" name="xFechaInicial" value="<%=fachadaColaboraLogisticaBean.getFechaInicial()%>">
<input type="hidden" name="xFechaFinal" value="<%=fachadaColaboraLogisticaBean.getFechaFinal()%>">
<input type="hidden" name="xIdCliente" value="<%=fachadaColaboraLogisticaBean.getIdCliente()%>">
<input type="hidden" name="xIdLocal" value="<%=fachadaTerceroBean.getIdLocalStr()%>">

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">HISTORICO PEDIDOS</td>
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
    </lst:listaClienteControlAgendaNit>
</table>

<table border="0" width="90%" id="tablaTitulo">

    <tr>
        <td width="20%" align="center" class="letraTitulo">#DCTO</td>
        <td width="20%" align="center" class="letraTitulo">TIPO DCTO</td>
        <td width="20%" align="center" class="letraTitulo">FEC.DOC</td>
        <td width="20%" align="right" class="letraTitulo">#ARTICULOS</td>
        <td width="20%" align="right" class="letraTitulo">VR.FACTURA</td>
    </tr>
    <lsu:listaPedidoPeriodoLocal idTipoOrdenCadenaTag = "<%=fachadaColaboraLogisticaBean.getIdTipoOrdenCadena()%>"
                                      fechaInicialTag = "<%=fachadaColaboraLogisticaBean.getFechaInicial()%>"
                                        fechaFinalTag = "<%=fachadaColaboraLogisticaBean.getFechaFinal()%>"
                                         idClienteTag = "<%=fachadaColaboraLogisticaBean.getIdCliente()%>"
                                           idLocalTag = "<%=fachadaTerceroBean.getIdLocalStr()%>"
                                  indicadorInicialTag = "<%=fachadaColaboraLogisticaBean.getIndicadorInicialStr()%>"
                                    indicadorFinalTag = "<%=fachadaColaboraLogisticaBean.getIndicadorFinalStr()%>">

            <tr>
                <td width="20%" align="center" class="letraDetalle">
                    <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstEmpresaHRemesa.jsp&accionContenedor=detallarCotizacion&idLocal=<%=idLocalVar%>&idTipoOrden=<%=idTipoOrdenVar%>&idOrden=<%=idOrdenVar%>"><%=idDctoVar%></a>
                </td>
                <td width="20%" align="center" class="letraDetalle"><%=nombreTipoOrdenVar%></td>
                <td width="20%" align="center" class="letraDetalle"><%=fechaDctoFormatoVar%></td>
                <td width="20%" align="right" class="letraDetalle"><%=numeroArticuloVar%></td>
                <td width="20%" align="right" class="letraDetalle"><%=vrFacturaDf0Var%></td>
            </tr>

    </lsu:listaPedidoPeriodoLocal>

</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">
            <input type="submit" value="Salir" name="accionContenedor">
        </td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
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