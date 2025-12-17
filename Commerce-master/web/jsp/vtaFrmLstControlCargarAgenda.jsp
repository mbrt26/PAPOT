<html>

<%@ taglib uri="/WEB-INF/tlds/listaVisita" prefix="lista" %>

<jsp:useBean id="fachadaAgendaControlBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaControlBean" />

  <head>
    <title>Agenda x Vendedor</title>
    <link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
  </head>
  <body>

    <form method="POST" action="GralControladorServlet">
      <table border="0" width="100%">
    <tr>
        <th width="100%"  class="letraTitulo">AGENDA X VENDEDOR</th>
    </tr>

        <tr>
            <b><font face="Verdana"><input type="submit" value="Salir" name="accionContenedor"></td>
        </tr>
       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstControlCargarAgenda.jsp">
    </table>
    <table border="1" width="100%">
    <lista:listaVisita idUsuarioTag = "<%=fachadaAgendaControlBean.getIdUsuarioStr()%>"
              fechaInicialVisitaTag = "<%=fachadaAgendaControlBean.getFechaInicialVisitaSqlServer()%>"
                fechaFinalVisitaTag = "<%=fachadaAgendaControlBean.getFechaFinalVisitaSqlServer()%>">

  <tr>
    <td width="100%" class="letraDetalle"><%=nombreClienteVar%></font></td>
  </tr>
  <tr>
    <td width="100%" class="letraDetalle">Estado Visita <%=nombreEstadoVar%></td>
  </tr>
  </lista:listaVisita>

  </table>
  </form>
    </b>
  </body>
</html>