<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaHistoria" prefix="listaHistoria" %>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />


<head>
<title>Historico Pendientes</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConClienteConsultarHistoricoInventario.jsp">

<table border="0" width="100%" cellspacing="1">
    <tr>
        <td width="10%" nowrap class="letraTitulo">HISTORICO PENDIENTES</td>
    </tr>
</table>

  <table border="1" width="100%" cellspacing="1">
    <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                           idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">
        <tr>
          <td width="25%" nowrap  class="letraTitulo">EstadoCliente <%=nombreEstadoClienteVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap  class="letraTitulo"><%=idClienteVar%> - <%=nombreClienteVar%></td>
        </tr>
    </tr>
</lista:listaClienteControlAgendaNit>

    <table border="1" width="100%">
    <listaHistoria:listaHistoria idClienteTag = "<%=fachadaAgendaLogVisitaBean.getIdCliente()%>">

      <tr>
        <td width="100%" nowrap class="letraDetalle">
        <b>RF: <%=strIdReferenciaVar.trim()%>
        <br><%=nombrePluVar.trim()%>
        <br>#ORDEN VENTA <%=numeroOrdenVar%>
        <br>FECHA ORDEN <%=fechaOrdenVar%>
        <br>#ORDEN COMPRA <%=ordenCompraVar%>
        <br>CANTIDAD PEDIDA = <%=cantidadVar%>
        </font></td>
      </tr>

    </listaHistoria:listaHistoria>
    </table>

<table border="0" width="100%" cellspacing="1">
    <tr>
      <b><font face="Verdana"><td width="41"><small><small><small><small><input type="submit"
      value="Salir" name="accionContenedor"></small></small></small></small></font></b></td>
    </tr>
</table>

</form>
</body>
</html>