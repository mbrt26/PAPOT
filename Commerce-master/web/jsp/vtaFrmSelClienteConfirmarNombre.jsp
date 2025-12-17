<html>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<%@ taglib uri="/WEB-INF/tlds/listaClienteNombre.tld" prefix="lista" %>

<jsp:useBean id="fachadaColaboraTerceroBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaColaboraTerceroBean" />

<head>
<title>Confirmar Ingreso Cliente</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelClienteConfirmarNombre.jsp">

  <table border="1" width="100%">
    <tr>
        <th width="100%" nowrap class="letraTitulo">CONFIRMAR CLIENTE</th>
    </tr>
  </table>

  <table border="1" width="100%">
    <lista:listaClienteNombre idUsuarioTag="<%=fachadaColaboraTerceroBean.getIdUsuarioStr()%>"
                          nombreClienteTag="<%=fachadaColaboraTerceroBean.getNombreCliente()%>">
        <tr>
          <td width="25%" class="letraDetalle">
          <input type="radio" name="radIdCliente" value="<%=idClienteSinFormatoVar%>"> <%=idClienteVar%>-(<%=idVendedorVar%>)<%=nombreClienteVar%></td>
        </tr>
      </td>
    </tr>
   </lista:listaClienteNombre>
  </table>

  <table border="0" width="100%">
    <tr>
      <td width="25%"><input type="submit" value="Confirmar" name="accionContenedor">
                      <input type="submit" value="Salir" name="accionContenedor1"></td>
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