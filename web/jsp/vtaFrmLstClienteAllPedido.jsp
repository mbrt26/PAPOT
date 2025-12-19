<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<%@ taglib uri="/WEB-INF/tlds/listaEstadoPedidoVendedor" prefix="lista" %>

<jsp:useBean id="fachadaColaboraLogisticaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean" />

<head>
<title>Estado Pedido</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<body>
<form method="POST" action="GralControladorServlet">
  <font face="Verdana">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstClienteAllPedido.jsp">

  </font>

  <table border="0" width="100">
    <tr>
      <th width="100%" nowrap valign="top"><div align="left"><p><font face="Verdana" size="2"
      color="#0000A0"><strong>ESTADO PEDIDO</strong></font></th>
    </tr>
  </table>

<lista:fechaPedido idUsuarioTag = "<%=fachadaColaboraLogisticaBean.getIdUsuarioStr()%>"
                 idTipoOrdenTag = "<%=fachadaColaboraLogisticaBean.getIdTipoOrdenStr()%>"
                fechaInicialTag = "<%=fachadaColaboraLogisticaBean.getFechaInicial()%>"
                  fechaFinalTag = "<%=fachadaColaboraLogisticaBean.getFechaFinal()%>">

<table border="1" width="100%" cellspacing="1">
    <tr><td nowrap align="center" class="letraTitulo">FECHA COTIZACIÓN <%=fechaOrdenVar%></td></tr>

    <lista:estadoPedido idUsuarioTag = "<%=fachadaColaboraLogisticaBean.getIdUsuarioStr()%>"
                      idTipoOrdenTag = "<%=fachadaColaboraLogisticaBean.getIdTipoOrdenStr()%>"
                       fechaOrdenTag = "<%=fechaOrdenVar%>">

    <tr>
    <td width="100%" nowrap class="letraDetalle">
          <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstClienteAllPedido.jsp&accionContenedor=Seleccionar&idLocal=<%=idLocalVar%>&idTipoOrden=<%=fachadaColaboraLogisticaBean.getIdTipoOrdenStr()%>&idOrden=<%=idOrdenVar%>">Cotización# <%=numeroCotizacionVar%></a>
          <br><%=idClienteVar%> - <%=nombreClienteVar%>
          <br> Artículos<%=cantidadArticulosVar%> Valor$ <%=vrVentaConIvaVar%>
          <br> PedidoMAX# <%=strNumeroOrdenVar%>
          <br> Estado <%=nombreEstadoTxVar%></td>
    </tr>

    </lista:estadoPedido>

</table>

</lista:fechaPedido>


<table border="0" width="100%" cellspacing="1">
  <tr>
  <td width="66" align="left"><b><font face="Verdana"><small><small><small><small><input
    type="submit" value="Salir" name="accionContenedor"></td>
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