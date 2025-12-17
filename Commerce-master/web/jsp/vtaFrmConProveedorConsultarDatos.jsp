<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaFormaPago" prefix="listaForma" %>
<%@ taglib uri="/WEB-INF/tlds/listaClienteAdicional" prefix="listaAdicional" %>
<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaCuentaConsolidadaCliente" prefix="listaCxc" %>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

<head>
<title>Datos Proveedor x NIT</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConProveedorConsultarDatos.jsp">

  <table border="0" width="100%" cellspacing="1">
    <tr>
        <td width="10%" nowrap class="letraTitulo"><strong>DATOS PROVEEDOR</td>
    </tr>
  </table>

  <table border="1" width="100%" cellspacing="1">
    <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                           idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">
        <tr>
          <td width="25%" nowrap class="letraDetalle">EstadoCliente <%=nombreEstadoClienteVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle"><%=idClienteVar%> - <%=nombreClienteVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle"> Nit <%=nitCCVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle"><%=direccionVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle">Teléfono <%=telefonoVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle">TeléfonoFax <%=telefonoFaxVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle">TeléfonoCelular <%=telefonoCelularVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle">Ciudad <%=ciudadDireccionVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle">Departamento <%=departamentoVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle">CupoCredito <%=cupoCreditoVar%></td>
        </tr>
        <!--tr>
          <td width="25%" nowrap class="letraDetalle">TotalCredito <%=totalCxCVar%></td>
        </tr-->
        <!--tr>
          <td width="25%" nowrap class="letraDetalle">Disponible <%=disponibleVar%></td>
        </tr-->
        <tr>
          <td width="25%" nowrap class="letraDetalle">ListaPrecio <%=listaPrecioVar%></td>
        </tr>
        <tr>
          <td width="25%" nowrap class="letraDetalle">Contacto <%=contactoVar%></td>
        </tr>
      </td>
    </tr>
</lista:listaClienteControlAgendaNit>
  </table>

  <table border="1" width="100%" cellspacing="1">
    <listaForma:listaFormaPago idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                           idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">
        <tr>
          <td width="25%" nowrap class="letraDetalle">FormaPago <%=nombreCodeVar%></td>
        </tr>
</listaForma:listaFormaPago>
  </table>

  <table border="1" width="100%" cellspacing="1">
    <listaAdicional:listaClienteAdicional idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                           idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">
        <tr>
          <td width="25%" nowrap class="letraDetalle">Email <%=emailVar%></td>
        </tr>
</listaAdicional:listaClienteAdicional>
  </table>

  <table border="0" width="100%" cellspacing="1">
    <tr>
        <td width="10%" nowrap class="letraTitulo"><strong>DATOS CXP</strong></font></td>
    </tr>
  </table>

  <table border="1" width="100%" cellspacing="1">
        <tr>
          <td width="40%" nowrap class="letraTitulo">Cartera</font></strong></td>
          <td width="30%" nowrap class="letraTitulo">#Dctos</font></strong></td>
          <td width="30%" nowrap class="letraTitulo">vrSaldo</font></strong></td>
        </tr>
    <listaCxc:listaCuentaConsolidadaCliente idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                           idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">

        <tr>
          <td width="40%" nowrap class="letraDetalle"><%=tipoCarteraVar%></font></strong></td>
          <td width="30%" nowrap class="letraDetalle"><%=numeroDctosVar%></font></strong></td>
          <td width="30%" nowrap class="letraDetalle"><%=vrSaldoVar%></font></strong></td>
        </tr>
     </listaCxc:listaCuentaConsolidadaCliente>

  </table>



  <table border="0" width="100%" cellspacing="1">
    <tr>
      <b><font face="Verdana"><td width="26%"><small><small><small><small><input type="submit"
      value="Salir" name="accionContenedor"></small></small></small></small></font></b></td>
    </tr>
  </table>
</form>
</body>
</html>
</html>