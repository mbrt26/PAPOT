<html>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<%@ taglib uri="/WEB-INF/tlds/listaClienteCotizacionArticulos" prefix="listaArticulos" %>
<%@ taglib uri="/WEB-INF/tlds/listaClienteCotizacion" prefix="listaCotizacion" %>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

<jsp:useBean id="fachadaDctoOrdenBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <%
       String idTipoOrdenStr     = "9";
    %>

<head>
<title>Detalle Pedido</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<body>
<form method="POST" action="GralControladorServlet">

  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConClientePedido.jsp">
  <input type="hidden" name="idCliente" value="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>">
  <input type="hidden" name="idLog" value="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">
  <input type="hidden" name="idSucursal" value="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">
  <input type="hidden" name="idUsuario" value="<%=fachadaAgendaLogVisitaBean.getIdUsuarioStr()%>">

  <table border="0" width="100%">
    <tr>
      <th width="100%" nowrap class="letraTitulo">DETALLE PEDIDO</th>
    </tr>
  </table>


  <table border="1" width="100%" cellspacing="1">

    <listaCotizacion:listaClienteCotizacion idLogTag="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                        idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                       idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>"
                                        idUsuarioTag="<%=fachadaAgendaLogVisitaBean.getIdUsuarioStr()%>">

    <tr>

    <td width="100%" nowrap class="letraTitulo">#ARTÍCULOS <%=cantidadArticulosVar%> VR.BASE$ <%=vrVentaSinIvaVar%>
        <br> VR.TOTAL$ <%=vrVentaConIvaVar%>
        <br> ESTADO <%=nombreEstadoTxVar%>
        </font></strong></small></small></td>
    </tr>
    </listaCotizacion:listaClienteCotizacion>

</table>

<table border="1" width="100%" cellspacing="1">


    <listaArticulos:listaClienteCotizacionArticulos idLogTag="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                                idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                               idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>"
                                                idUsuarioTag="<%=fachadaAgendaLogVisitaBean.getIdUsuarioStr()%>"
                                              idTipoOrdenTag="<%=idTipoOrdenStr%>">

    <tr>
    <td width="100%" nowrap  class="letraDetalle">RF: <%=strIdReferenciaVar%>
                                                 <br><%=nombrePluVar%>
                                                 <br>CANTIDAD# <%=cantidadVar%>
                                                 <br>VR.UNIT.VENTA$ <%=vrVentaUnitarioVar%>
    </td>
    </tr>

    </listaArticulos:listaClienteCotizacionArticulos>

</table>

  <table border="0" width="100%" cellspacing="1">
  <td width="66" align="left"><b><font face="Verdana"><small><small><small><small>
  <a href="javascript:window.history.back()"><img src="./img/ARW06LT.GIF" width="32" height="32"></a>
  </td>
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