<html>
<%@ taglib uri="/WEB-INF/tlds/listaControlAgendaEstado" prefix="lista" %>
<jsp:useBean id="fachadaAgendaControlBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaControlBean" />

  <head>
    <title>Seleccionar Cliente</title>
    <link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
  </head>
  <body>

    <% int nroLinea         = 0; %>

    <form method="POST" action="GralControladorServlet">
      <table border="0" width="100%">
    <tr>
        <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>SELECCIONAR CLIENTE</strong></font></th>
    </tr>
        <tr>
            <b><font face="Verdana"><input type="submit" value="Salir" name="accionContenedor"></font></b></td>
            <b><font face="Verdana"><input type="submit" value="Seleccionar" name="accionContenedor"></font></b></td>
        </tr>
       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelAgendaReprogramar.jsp">
       <input type="hidden" name="fechaVisita" value="<%=fachadaAgendaControlBean.getFechaVisitaStr()%>">
    </table>
    <table border="1" width="100%">
      <tr>
        <td width="100%" class="letraTitulo">NOMBRE CLIENTE</td>
      </tr>

    <lista:listaControlAgendaEstado idUsuarioTag="<%=fachadaAgendaControlBean.getIdUsuarioStr()%>"
                                    fechaVisitaTag="<%=fachadaAgendaControlBean.getFechaVisitaStr()%>">

  <tr>
    <td width="100%" class="letraDetalle"><input type="radio" name="idClienteSucursal" value="<%= idClienteVar + '~' + idSucursalVar %>"> <%=nombreEstadoVar%>  ->  <%=idClienteVar.trim() + '-' + nombreClienteVar.trim() %></td>
  </tr>
    </lista:listaControlAgendaEstado>

    </table>
  </form>
    </b>
  </body>
</html>