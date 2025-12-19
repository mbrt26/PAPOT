<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="listaDatos" %>
<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaEstadosAgendaVisita" prefix="listaEstado" %>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

<head>
<title>Reportar Visita</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConClienteReportarVisita.jsp">

<table border="0" width="100%" cellspacing="1">
    <tr>
        <td width="10%" nowrap class="letraTitulo">REPORTAR VISITA</td>
    </tr>
</table>


  <table border="1" width="100%" cellspacing="1">
    <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                           idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">
        <tr>
          <td width="25%" nowrap  class="letraDetalle">EstadoCliente <%=nombreEstadoClienteVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle"><%=idClienteVar%> - <%=nombreClienteVar%> </td>
        </tr>
    </tr>
</lista:listaClienteControlAgendaNit>
  </table>


  <table border="1" width="100%" cellspacing="1">
    <tr>
        <td width="25%" nowrap class="letraTitulo">CONFIRMAR ACTIVIDADES REALIZADAS</font></strong></td>
    </tr>
    <listaEstado:listaEstadosAgendaVisita>
    <tr>
       <td width="25%" nowrap  class="letraDetalle"><input type="checkbox" name="idEstadoVisita" value="<%=idEstadoVisitaVar%>"> <%=nombreEstadoVar%> </font></strong></td>
    </tr>
    </listaEstado:listaEstadosAgendaVisita>
  </table>

  <table border="1" width="100%" cellspacing="1">
    <listaDatos:listaClienteControlAgendaNit idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                           idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">
  <tr>
    <td width="308" nowrap class="letraDetalle">NOMBRE CONTACTO</font></b></td>
  </tr>
  <tr>
  <td width="25%" nowrap class="letraDetalle"><input type="text" name="nombreCliente" value="<%=contactoVar%>" size="30" maxlength="50"></td>
  </tr>
  <tr>
    <td width="25%" nowrap class="letraDetalle">TELEFONO CONTACTO</font></b></td>
  </tr>
  <tr>
    <td width="25%" nowrap class="letraDetalle"><input type="text" name="telefono" value="<%=telefonoVar%>" size="12" maxlength="12"></td>
  </tr>
</listaDatos:listaClienteControlAgendaNit>
</table>

  <table border="0" width="100%" cellspacing="1">
    <tr>
      <b><font face="Verdana"><td width="253">
      <input type="submit" value="Confirmar" name="accionContenedor"><b><font face="Verdana"><input type="submit" value="Salir" name="accionContenedor1"></td>
    </tr>
    <tr>
      <b><font face="Verdana"><td width="253">
      &nbsp;</td>
    </tr>
  </table>

  <input type="hidden" name="fechaVisita" value="<%=fachadaAgendaLogVisitaBean.getFechaVisitaStr()%>">
  <input type="hidden" name="idCliente" value="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>">
  <input type="hidden" name="idSucursal" value="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">

</form>
</body>
</html>