<html>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<%@ taglib uri="/WEB-INF/tlds/listaInventarioReferencia" prefix="lista" %>

<jsp:useBean id="fachadaDctoOrdenDetalleBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />

    <%
       String colorAmarillo = "#FFFF00";
       String colorVerde    = "#00FF00";
       String colorFila     = colorAmarillo;
       String xIdLog        = request.getParameter("idLog");
       String xItem         = request.getParameter("item");

    %>

<head>
<title>Modificar Cantidad</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModClienteConfirmaRetiraArticulo.jsp">

<table border="0" width="100%" cellspacing="1">
    <tr>
        <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>MODIFICAR CANTIDAD</strong></font></th>
    </tr>
  </table>


<table border="1" width="100%">

	<tr>
		<td width="100%" nowrap class="letraTitulo">RFCIA <%=fachadaDctoOrdenDetalleBean.getStrIdReferencia()%></td>
	</tr>

	<tr>
		<td width="100%" class="letraTitulo"><%=fachadaDctoOrdenDetalleBean.getNombrePlu()%></td>
	</tr>

</table>

  <table border="1" width="100%" cellspacing="1">
        <tr>
          <td width="40%" nowrap class="letraTitulo">BODEGA</font></strong></td>
          <td width="30%" nowrap class="letraTitulo">INVENTARIO</font></strong></td>
        </tr>
    <lista:listaInventarioReferencia strIdReferenciaTag = "<%=fachadaDctoOrdenDetalleBean.getStrIdReferencia()%>">

        <tr>
          <td width="40%" nowrap class="letraDetalle"><%=strIdBodegaVar%></td>
          <td width="30%" nowrap class="letraDetalle"><%=existenciaVar%></tr>
     </lista:listaInventarioReferencia>

  </table>

<table border="1" width="100%">
	<tr>
		<td width="100%" nowrap class="letraDetalle">Ingrese Cantidad</font></b></td>
	</tr>

	<tr>
		<td width="100%" nowrap class="letraDetalle">
		<input name="cantidad" size="20" value="<%=fachadaDctoOrdenDetalleBean.getCantidadDf2()%>" style="font-weight: 700"></td>
	</tr>

	<tr>
		<td width="100%" nowrap class="letraDetalle">Ingrese VrVentaUnitario</font></b></td>
	</tr>

	<tr>
		<td width="100%" nowrap class="letraDetalle">
		<input name="vrVentaUnitario" size="20" value="<%=fachadaDctoOrdenDetalleBean.getVrVentaUnitarioStr()%>" style="font-weight: 700"></td>
	</tr>

</table>

<table border="1" width="100%">
	<tr>
		<td width="100%"><font face="Verdana">
       <input type="submit" value="Confirmar Cantidad" name="accionContenedor">
       <input type="submit" value="Regresar" name="accionContenedor">
       <input type="hidden" name="item" value="<%=xItem%>">
       <input type="hidden" name="idLog" value="<%=xIdLog%>">
      </font></td>
	</tr>
</table>

</form>
</body>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

</html>