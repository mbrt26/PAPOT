<html>

<head>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head

<%@ page import="com.solucionesweb.losbeans.negocio.UsuarioBean" %>
<%@ taglib uri="/WEB-INF/tlds/listaPedidoAdministradorUnCO" prefix="listaCotizacion" %>

<jsp:useBean id="day" scope="page" class="com.solucionesweb.losbeans.utilidades.Day">
<jsp:setProperty name="day" property="*" />
</jsp:useBean>

    <%
       String idTipoOrden   = "9";

       //
       HttpSession sesion = request.getSession();
       UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

    %>

<head>
<title>Actualizar Pedido x CO</title>
</head>

<body>
<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorClienteAutorizaPedidoUnCOVariosPedidos.jsp">

  <table border="0" width="368">
    <tr>
      <th width="100%" nowrap valign="top"><div align="left"><p><font face="Verdana" size="2"
      color="#0000A0"><strong>ACTUALIZAR PEDIDO X CO</strong></font></th>
    </tr>
  </table>

<table border="0" width="100%">
  <tr>
  <td width="25%" align="left"><b><font face="Verdana"><small><small><small><small><input
    type="submit" value="Salir" name="accionContenedor"></td>
  </tr>
  </tr>
</table>

<table border="1" class="separado" width="100%" height="30">
  <tr>
    <td width="8%" align="center" class="letraTitulo">ESTADO</td>
    <td width="8%" align="right" class="letraTitulo">IDCLIENTE</td>
    <td width="33%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
    <td width="11%" align="right" class="letraTitulo">#PEDIDO</td>
    <td width="9%" align="center" class="letraTitulo">FECHA PEDIDO</td>
    <td width="7%" align="left" class="letraTitulo">#REFERENCIA</td>
    <td width="7%" align="right" class="letraTitulo">VR.VENTA</td>
    <td width="13%" align="left" class="letraTitulo">VENDEDOR</td>
  </tr>

    <listaCotizacion:listaPedidoAdministradorUnCO idTipoOrdenTag = "<%=idTipoOrden%>"
                                                   fechaOrdenTag = "<%=day.getFechaFormateada()%>"
                                                    idUsuarioTag = "<%=usuarioBean.getIdUsuarioStr()%>">

    <tr>
    <td width="8%" align="center" class="letraDetalle"><%=nombreEstadoVar%></td>
    <td width="8%" align="right" class="letraDetalle"><%=idClienteVar%></td>
    <td width="33%" align="left" class="letraDetalle"><%=nombreClienteVar%></td>
    <td width="11%" align="right" class="letraDetalle">
    <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorClienteAutorizaPedidoUnCOVariosPedidos.jsp&accionContenedor=detallarCotizacion&idLog=<%=idLogVar%>&idOrden=<%=idOrdenVar%>"><%=strNumeroCotizacionVar%></a>
    </td>
    <td width="9%" align="center" class="letraDetalle"><%=fechaOrdenVar%></td>
    <td width="7%" align="left" class="letraDetalle"><%=cantidadArticulosVar%></td>
    <td width="7%" align="right" class="letraDetalle"><%=vrVentaConIvaVar%></td>
    <td width="13%" align="left" class="letraDetalle"><%=nombreUsuarioVar%></td>
    </tr>

    </listaCotizacion:listaPedidoAdministradorUnCO>

</table>


</form>
</body>

</html>