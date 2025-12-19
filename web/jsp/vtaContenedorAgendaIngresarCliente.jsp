<jsp:useBean id="day" scope="page" class="com.solucionesweb.losbeans.utilidades.Day">
<jsp:setProperty name="day" property="*" />
</jsp:useBean>
<html>
<head>
<title>Ingresar Cliente Agenda</title>
</head>
<body bgcolor="#FFFFFF" topmargin="2" leftmargin="2">
<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorAgendaIngresarCliente.jsp">
   <table width="100%">
    <tr>
      <td><table border="0" width="15%">
        <tr>
          <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>INGRESAR CLIENTE AGENDA</strong></font></th>
        </tr>
      </table>
      <table border="0" width="184">
        <tr>
          <td width="180"><small><strong><small><font face="Verdana">Fecha(AAAA/MM/DD)</font></small></strong></small></td>
        </tr>
        <tr>
          <font face="Verdana" size="1"><b><td width="180"><input size="17" maxlength="12"
          tabindex="1" type="text" value="<%=day.getFechaFormateada()%>" name="fechaVisita"></b></font></td>
        </tr>
        <tr>
          <td width="180"><small><strong><small><font face="Verdana">idCliente</font></small></strong></small></td>
        </tr>
        <tr>
          <font face="Verdana" size="1"><b><td width="180"><input size="17" maxlength="12"
          tabindex="1" type="text" value="" name="idCliente"></b></font></td>
        </tr>
        <tr>
          <font face="Verdana" size="1"><b><td width="180"><input type="submit" value="Ingresar"
          name="accionContenedor"></b></font></td>
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