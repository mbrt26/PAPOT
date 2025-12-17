
<html>
<head>
<title>Inventarios/Precios</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" topmargin="2" leftmargin="2">
<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorCotizacionInventario.jsp">
   <table width="100%">
    <tr>
      <td><table border="0" width="15%">
        <tr>
          <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>INVENTARIOS</strong></font></th>
        </tr>
      </table>
    <table border="1" width="100%" cellspacing="1">
      <tr><td width="10%" valign="top" align="left" class="letraDetalle">
         <font face="Verdana"><p><input type="text" name="idLinea" size="10" tabindex="1" maxlength="20"></p></td>
         <td width="90%" valign="top" align="left" class="letraDetalle"><input type="submit" value="+Productos" name="accionContenedor"></td>
      </tr>
        <tr>
          <font face="Verdana" size="1"><b><td width="180">
          <input type="submit" value="Salir" name="accionContenedor"></b></font></td>
        </tr>
      </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>