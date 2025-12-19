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
</head>

<font face="Verdana" size="2">

<jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
<jsp:setProperty name="jhDate" property="*" />
</jsp:useBean>

<head>
<title>CODIFICAR CLIENTE</title>
</head>
</font>
<body bgcolor="#FFFFFF" topmargin="2" leftmargin="2">
<form method="POST" action="GralControladorServlet">
  <font face="Verdana" size="1">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorClienteNuevoCodificar.jsp">
  <input size="17" type="hidden" value="<%=jhDate.getDate(5,true)%>" name="fechaVisita">
   </font>

   <table border="0" width="100%">
        <tr>
          <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>CODIFICAR CLIENTE</strong></font></th>
        </tr>
        <tr>
          <td width="100%"><small><strong><font size="2" face="Verdana">FECHA <%=jhDate.getDate(5,true)%></font></strong></small></td>
        </tr>

      </table>

      <table border="0" width="100%">
        <tr>
          <td width="100%"><strong>
			<font face="Verdana" size="2">nitCC</font></strong></td>
        </tr>
        <tr>
          <td width="100%"><font
          face="Verdana" size="1"><b>
			<font face="Verdana">
			&nbsp;<!--webbot bot="Validation" i-minimum-length="13" i-maximum-length="13" --><input size="20" tabindex="1" type="text" value name="idCliente"
          maxlength="13"></font></b></font></td>
        </tr>
        <tr>
          <td width="100%"><small><strong><font face="Verdana"><font size="2">nombreCliente</font></font></strong></small></td>
        </tr>
        <tr>
          <td width="100%" nowrap>
			<font face="Verdana" size="1">
			&nbsp;<!--webbot bot="Validation" i-minimum-length="13" i-maximum-length="50" --><input size="50" tabindex="1" type="text" value name="nombreCliente" maxlength="50"></font><font face="Verdana" size="2"></b></font></font></td>
        </tr>
        <tr>
          <td width="100%"><small><strong><font size="2" face="Verdana">teléfono</font></strong></small><font face="Verdana" size="2"><b>/</b></font><strong><font face="Verdana" size="2">Celular</font></strong></td>
        </tr>
        <tr>
          <td width="100%"><font face="Verdana" size="1"><b>
			<font face="Verdana">
			<!--webbot bot="Validation" i-minimum-length="20" i-maximum-length="20" --><input size="20" tabindex="1" type="text" value name="telefono" maxlength="20"></font></b></font></td>
        </tr>
        <tr>
          <td width="100%"><b><font face="Verdana" size="2">email</font></b></td>
        </tr>
		<tr>
          <td width="100%"><b>
			<font face="Verdana" size="2">&nbsp;</font><font face="Verdana" size="1"><!--webbot bot="Validation" i-minimum-length="20" i-maximum-length="50" --><input size="50" tabindex="1" type="text" value name="email" maxlength="50"></font></b></td>
        </tr>
		<tr>
          <td width="100%"><b><font face="Verdana" size="2">contacto</font></b></td>
        </tr>
		<tr>
          <td width="100%"><b>
			<font face="Verdana" size="2">&nbsp;</font><font face="Verdana" size="1"><!--webbot bot="Validation" i-minimum-length="20" i-maximum-length="50" --><input size="50" tabindex="1" type="text" value name="contacto" maxlength="50"></font></b></td>
        </tr>
        <tr>
          <td width="100%"><font face="Verdana" size="1"><input type="submit" value="Salir" name="accionContenedor"></font><font face="Verdana" size="2"></b></font></font><font face="Verdana" size="1"><b><font face="Verdana">
          <input type="submit" value="Confirmar"  name="accionContenedor"></font></font></td>
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