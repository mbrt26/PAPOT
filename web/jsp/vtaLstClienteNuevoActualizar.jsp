<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Pagina nueva 1</title>
</head>

<body>

<html>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<font face="Verdana" size="2">

<jsp:useBean id="fachadaAgendaLogSoporteBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogSoporteBean" />


<head>
<title>ACTUALIZAR CLIENTE</title>
</head>
</font>
<body bgcolor="#FFFFFF" topmargin="2" leftmargin="2">
<form method="POST" action="GralControladorServlet">
  <font face="Verdana" size="1">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaLstClienteNuevoActualizar.jsp">
  <input type="hidden" name="idLog" value="<%=fachadaAgendaLogSoporteBean.getIdLogStr()%>">
   </font>

   <table border="0" width="100%">
        <tr>
          <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>ACTUALIZAR CLIENTE</strong></font></th>
        </tr>
      </table>

      <table border="1" width="100%">
        <tr>
          <td width="100%" class="letraDetalle">nitCC</td>
        </tr>
        <tr>
          <td width="100%" class="letraDetalle"><input size="20" tabindex="1" type="text" name="idCliente" value="<%=fachadaAgendaLogSoporteBean.getIdCliente()%>" maxlength="13"></td>
        </tr>
        <tr>
          <td width="100%" class="letraDetalle">nombreCliente</td>
        </tr>
        <tr>
          <td width="100%" class="letraDetalle"><input size="50" tabindex="1" type="text" name="nombreCliente" maxlength="50" value="<%=fachadaAgendaLogSoporteBean.getNombreCliente()%>"></font><font face="Verdana" size="2"></td>
        </tr>
        <tr>
          <td width="100%" class="letraDetalle">Celular</td>
        </tr>
		<tr>
          <td width="100%" class="letraDetalle"><input size="50" tabindex="1" type="text" name="telefono" maxlength="20" value="<%=fachadaAgendaLogSoporteBean.getTelefono()%>"></td>
        </tr>
        <tr>
          <td width="100%" class="letraDetalle">email</td>
        </tr>
		<tr>
          <td width="100%" class="letraDetalle"><input size="50" tabindex="1" type="text" name="email" maxlength="50" value="<%=fachadaAgendaLogSoporteBean.getEmail()%>"></td>
        </tr>
		<tr>
          <td width="100%" class="letraDetalle">contacto</td>
        </tr>
		<tr>
          <td width="100%" class="letraDetalle"><input size="50" tabindex="1" type="text" name="contacto" maxlength="50" value="<%=fachadaAgendaLogSoporteBean.getContacto()%>"></td>
        </tr>
        <tr>
          <td width="100%"><font face="Verdana" size="1"><input type="submit" value="Salir" name="accionContenedor"></font><font face="Verdana" size="2"></b></font></font><font face="Verdana" size="1"><b><font face="Verdana">
          <input type="submit" value="Actualizar"  name="accionContenedor"></font></font></td>
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

</html></body>

</html>