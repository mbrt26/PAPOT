<html>
<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>
<jsp:useBean id="fachadaAgendaControlBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaControlBean" />


<head>
<title>Confirmar Ingreso Cliente</title>
  <link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
  <link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelAgendaIngresarCliente.jsp">
  <input type="hidden" name="idCliente" value="<%=fachadaAgendaControlBean.getIdCliente()%>">
  <input type="hidden" name="idSucursal" value="<%=fachadaAgendaControlBean.getStrIdSucursal()%>">
  <input type="hidden" name="fechaVisita" value="<%=fachadaAgendaControlBean.getFechaVisitaStr()%>">

  <table border="0" width="100%">
    <tr>
        <td class="letraTitulo">CONFIRMAR INGRESO CLIENTE</td>
    </tr>
    <tr>
      <input type="submit" value="Salir" name="accionContenedor"></td>
      <input type="submit" value="Confirmar" name="accionContenedor"></td>
    </tr>
  </table>

  <table border="1" width="100%">
    <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaAgendaControlBean.getIdCliente()%>"
                                        idSucursalTag="<%=fachadaAgendaControlBean.getStrIdSucursal()%>">
        <tr>
          <td width="100%"  class="letraDetalle">idCliente</td>
        </tr>
        <tr>
          <td width="100%"  class="letraDetalle"><%=idClienteVar%></td>
        </tr>
        <tr>
          <td width="100%"  class="letraDetalle">nombreCliente</td>
        </tr>
        <tr>
          <td width="100%"  class="letraDetalle"><%=nombreClienteVar%></td>
        </tr>
        <tr>
          <td width="100%"  class="letraDetalle">idSucursal</td>
        </tr>
        <tr>
          <td width="100%"  class="letraDetalle"><%=idSucursalVar%></td>
        </tr>
        <tr>
          <td width="100%"  class="letraDetalle">FechaVisita</font></td>
        </tr>
        <tr>
          <td width="100%"  class="letraDetalle"><%=fachadaAgendaControlBean.getFechaVisitaStr()%></td>
        </tr>

      </td>
    </tr>
</lista:listaClienteControlAgendaNit>
  </table>

</form>
</body>
</html>
</html>