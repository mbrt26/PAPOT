<html>

<head>
<meta http-equiv="Refresh" content="30;URL="http://localhost:8000/Comercial/vtaContenedorClienteAutorizaProspectoUnCO.jsp">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head

<%@ page import="com.solucionesweb.losbeans.negocio.UsuarioBean" %>
<%@ taglib uri="/WEB-INF/tlds/listaProspecto" prefix="lst" %>

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
<title>Prospecto Cliente</title>
</head>

<body>
<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorClienteAutorizaProspectoUnCO.jsp">

  <table border="0" width="368">
    <tr>
      <th width="100%" nowrap valign="top"><div align="left"><p><font face="Verdana" size="2"
      color="#0000A0"><strong>PROSPECTO CLIENTE</strong></font></th>
    </tr>
  </table>

<table border="1" class="separado" width="100%" height="30">
  <tr>
    <td width="35%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
    <td width="10%" align="right" class="letraTitulo">IDCLIENTE</td>
    <td width="10%" align="center" class="letraTitulo">TELEFONO</td>
    <td width="10%" align="left" class="letraTitulo">CONTACTO</td>
    <td width="35%" align="right" class="letraTitulo">EMAIL</td>
  </tr>

  <lst:listaProspecto idUsuarioTag = "<%=usuarioBean.getIdUsuarioStr()%>">

  <tr>
    <td width="35%" align="left" class="letraDetalle">
    <input type="radio" name="radIdLog" value="<%=idLogVar%>"><%=nombreClienteVar%></td>
    <td width="10%" align="right" class="letraDetalle"><%=idClienteVar%></td>
    <td width="10%" align="center" class="letraDetalle"><%=telefonoVar%></td>
    <td width="10%" align="left" class="letraDetalle"><%=contactoVar%></td>
    <td width="35%" align="right" class="letraDetalle"><%=emailVar%></td>
  </tr>
    </lst:listaProspecto>
</table>

<table border="0" width="100%">
  <tr>
  <td width="25%" align="left"><b><font face="Verdana"><small><small><small><small>
    <input type="submit" value="Salir" name="accionContenedor">
    <input type="submit" value="Actualizar" name="accionContenedor">
    </td>
  </tr>
  </tr>
</table>

</form>
</body>

</html>