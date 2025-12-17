<html>

<%@ taglib uri="/WEB-INF/tlds/listaClienteControlCargarAgenda" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaEstadosCargarAgenda" prefix="listaEstado" %>

<jsp:useBean id="fachadaAgendaControlBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaControlBean" />

<head>
<title>Reprogramar Agenda</title>
    <link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConAgendaReprogramar.jsp">

  <table border="0" width="269">
    <tr>
        <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>REPROGRAMAR AGENDA</th>
    </tr>
    <tr>
      <input type="submit" value="Salir" name="accionContenedor">
      <b><font face="Verdana">
      <input type="submit" value="Modificar" name="accionContenedor"></td>
    </tr>
  </table>

  <table border="1" width="100%">
    <lista:listaClienteControlCargarAgenda idClienteTag = "<%=fachadaAgendaControlBean.getIdCliente()%>"
                                          idSucursalTag = "<%=fachadaAgendaControlBean.getStrIdSucursal()%>"
                                         fechaVisitaTag = "<%=fachadaAgendaControlBean.getFechaVisitaStr()%>">
        <tr>
          <td width="100%"  class="letraTitulo">idCliente</td>
        </tr>
        <tr>
          <td width="100%"  class="letraDetalle"><%=idClienteVar%></td>
        </tr>
        <tr>
          <td width="100%"  class="letraTitulo">nombreCliente</font></td>
        </tr>
        <tr>
          <td width="100%"  class="letraDetalle"><%=nombreClienteVar%></td>
        </tr>
    </lista:listaClienteControlCargarAgenda>
  </table>

  <table border="1" width="100%">
    <tr>
        <td width="100%"   class="letraTitulo">MODIFICAR EL ESTADO A LA VISITA</td>
    </tr>
    <listaEstado:listaEstadosCargarAgenda>
    <tr>
       <td width="100%" class="letraDetalle"><input type="radio" name="estado" value="<%=estadoVar%>"> <%=nombreEstadoVar%> </td>
    </tr>
    </listaEstado:listaEstadosCargarAgenda>
  </table>

  <input type="hidden" name="fechaVisita" value="<%=fachadaAgendaControlBean.getFechaVisitaStr()%>">
  <input type="hidden" name="idCliente" value="<%=fachadaAgendaControlBean.getIdCliente()%>">
  <input type="hidden" name="idSucursal" value="<%=fachadaAgendaControlBean.getStrIdSucursal()%>">

</form>
</body>
</html>
</html>