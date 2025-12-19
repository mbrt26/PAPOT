<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
  <head>
    <title>Historico Pedidos</title>
    <link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
  </head>

<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

<jsp:useBean id="fachadaColaboraReporteDctoBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

  <body>
    <form method="POST" action="GralControladorServlet">

  <table border="0" width="100%">
    <tr>
      <th width="100%" nowrap  class="letraTitulo">HISTORICO PEDIDOS</th>
    </tr>
  </table>
  <table border="1" width="100%" cellspacing="1">
    <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                           idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">
        <tr>
          <td width="25%" nowrap class="letraTitulo"><%=idClienteVar%> - <%=nombreClienteVar%> </td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraTitulo">EstadoCliente <%=nombreEstadoClienteVar%></td>
        </tr>
    </tr>
</lista:listaClienteControlAgendaNit>
  </table>

      <table border="0" width="68%">
        <tr>
          <td width="1%" nowrap>
            <b><font face="Verdana"><input type="submit" value="Consultar" name="accionContenedor"></font></b></td>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
            <b><font face="Verdana"><input type="submit" value="Regresar" name="accionContenedor"></font></b></td>
        </tr>
       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorClienteAdministraPedido.jsp">
      </b>

        <tr>
          <td width="1%" nowrap><font face="Verdana" size="1" color="#0000A0"><b>Fecha
            Final</b></font></td>
        <b>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
          <font face="Verdana"><input type="text" name="fechaFinal" size="10" value="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>"></font></td>
        </b>
      </tr>

        <tr>
          <td width="1%" nowrap><font face="Verdana" size="1" color="#0000A0"><b>Fecha
            Inicial</b></font></td>
        <b>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
          <font face="Verdana"><input type="text" name="fechaInicial" size="10" value="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"></font></td>
        </tr>
      </b>
    </table>
  </form>
</b>
  </body>
</html>