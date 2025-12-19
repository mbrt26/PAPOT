<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/listaReferenciaAtributo" prefix="listaAtributo" %>
<%@ taglib uri="/listaInventarioReferencia" prefix="lista" %>


<jsp:useBean id="fachadaInventarioBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaInventarioBean" />

    <%
       String colorAmarillo = "#FFFF00";
       String colorVerde    = "#00FF00";
       String colorFila     = colorAmarillo;
    %>

<head>
<title>Inventario Actual</title>
</head>
<body>

<form method="POST" action="/Comercial/servlet/GralControladorServlet">
  <font size="1" face="Verdana">
  <input type="hidden" name="nombrePaginaRequest" value="/vtaFrmLstClienteConsultarHistoricoInventario.jsp">

	</font>

<table border="0" width="100%" cellspacing="1">
    <tr>
        <td width="10%" nowrap bgcolor=<%=colorVerde%> height="10"><div align="left"><p>
			<font face="Verdana" size="1" color="#0000A0"><strong>INVENTARIO ACTUAL</strong></font></td>
    </tr>
</table>

    <table border="1" width="100%">
    <listaAtributo:listaReferenciaAtributo strIdReferenciaTag = "<%=fachadaInventarioBean.getStrIdReferencia()%>">

	<tr bgcolor=<%=colorAmarillo%>>
		<td><b><font face="Verdana" size="1">Linea <%=nombreLineaVar%></font></b></td>
	</tr>

	<tr bgcolor=<%=colorAmarillo%>>
		<td><b><font face="Verdana" size="1">Sublinea <%=nombreSublineaVar%></font></b></td>
	</tr>

	<tr bgcolor=<%=colorAmarillo%>>
		<td><b><font face="Verdana" size="1">Articulo <%=nombrePluVar%></font></b></td>
	</tr>

	<tr bgcolor=<%=colorAmarillo%>>
		<td><b><font face="Verdana" size="1">VrVentaUn$ <%=vrVentaUnitarioVar%></font></b></td>
	</tr>

	<tr bgcolor=<%=colorAmarillo%>>
		<td><b><font face="Verdana" size="1">Referencia# <%=strIdReferenciaVar%></font></b></td>
	</tr>
	</listaAtributo:listaReferenciaAtributo>
</table>

  <table border="1" width="100%" cellspacing="1">
        <tr>
          <td width="40%" nowrap bgcolor=<%=colorVerde%>><strong><font face="Verdana" size="1">BODEGA</font></strong></td>
          <td width="30%" nowrap bgcolor=<%=colorVerde%>><strong><font face="Verdana" size="1">INVENTARIO</font></strong></td>
          <td width="30%" nowrap bgcolor=<%=colorVerde%>><strong><font face="Verdana" size="1"></font></strong></td>
        </tr>
    <lista:listaInventarioReferencia strIdReferenciaTag = "<%=fachadaInventarioBean.getStrIdReferencia()%>">

        <tr>
          <td width="40%" nowrap bgcolor=<%=colorAmarillo%>><strong><font face="Verdana" size="1"><%=strIdBodegaVar%></font></strong></td>
          <td width="30%" nowrap bgcolor=<%=colorAmarillo%>><strong><font face="Verdana" size="1"><%=existenciaVar%></font></strong></td>
          <td width="30%" nowrap bgcolor=<%=colorAmarillo%>><strong><font face="Verdana" size="1"></font></strong></td>
        </tr>
     </lista:listaInventarioReferencia>

  </table>

<table border="0" width="100%" cellspacing="1">
    <tr>
      <b><font face="Verdana"><td width="41"><small><small><small><small>
		<font size="1" face="Verdana"><input type="submit"
      value="Salir" name="accionContenedor"></font></small></small></small></small><font face="Verdana" size="1"></font></b></font></td>
    </tr>
</table>

</form>
</body>
</html>