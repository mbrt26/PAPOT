<html>
<%@ taglib uri="/WEB-INF/tlds/listaControlAgendaEstado" prefix="lista" %>

<jsp:useBean id="fachadaAgendaControlBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaControlBean" />
  <head>
    <title>Seleccionar Cliente</title>
  </head>
  <body>

    <% int nroLinea         = 0;
       String colorAmarillo = "#FFFF00";
       String colorVerde    = "#00FF00";
       String colorFila     = colorAmarillo;
    %>

    <form method="POST" action="GralControladorServlet">
      <table border="0" width="100%">
    <tr>
        <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>SELECCIONAR CLIENTE</strong></font></th>
    </tr>
        <tr>
            <b><font face="Verdana"><input type="submit" value="Salir" name="accionContenedor"></font></b></td>
        </tr>
        <tr>
            <b><font face="Verdana"><input type="submit" value="Seleccionar" name="accionContenedor"></font></b></td>
        </tr>
       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelControlCargarAgenda.jsp">
    </table>

    <table border="1" width="100%">
       <tr>
       <td width="100%">&nbsp;</td>
       </tr>

    <lista:listaControlAgendaEstado idUsuarioTag="<%=fachadaAgendaControlBean.getIdUsuarioStr()%>"
                                    fechaVisitaTag="<%=fachadaAgendaControlBean.getFechaVisitaStr()%>">

   <%
     colorFila    = colorAmarillo;
     nroLinea     = nroLinea + 1;
     if (nroLinea % 2 == 0) {
        colorFila = colorVerde;
     }
   %>

  <tr>
    <td width="100%" nowrap bgcolor="<%=colorFila%>"><strong><small><small><font face="Verdana"><input type="radio" name="idClienteSucursal"
    value="<%= idClienteVar + '~' + idSucursalVar %>"> <%=nombreEstadoVar%>  ->  <%=idClienteVar.trim() + '-' + nombreClienteVar.trim() %></font></small></small></strong></td>
  </tr>

    </lista:listaControlAgendaEstado>

    </table>
  </form>
    </b>
  </body>
</html>