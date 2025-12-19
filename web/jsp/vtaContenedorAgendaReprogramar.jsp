<jsp:useBean id="day" scope="page" class="com.solucionesweb.losbeans.utilidades.Day">
<jsp:setProperty name="day" property="*" />
</jsp:useBean>
<html>
<head>
<title>Seleccionar Cliente</title>
</head>
<body bgcolor="#FFFFFF" topmargin="2" leftmargin="2">
<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorAgendaReprogramar.jsp">
   <table width="100%">
    <tr>
      <td><table border="0" width="15%">
        <tr>
          <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>SELECCIONAR
          CLIENTE</strong></font></th>
        </tr>
      </table>
      <table border="0" width="184">
        <tr>
            <b><font face="Verdana" size="1">Fecha Visita (AAAA/MM/DD)</font></b></td>
        </tr>
        <tr>
            <b><font face="Verdana" size="1"><%=day.getFechaFormateada()%></font></b></td>
        </tr>
        <tr>
          <font face="Verdana" size="1"><b><td width="180"><input type="submit" value="Listar"
          name="accionContenedor"></b></font></td>
        </tr>
        <tr>
          <font face="Verdana" size="1"><b><td width="180">
          <input type="submit" value="Salir" name="accionContenedor"></b></font></td>
          <input type="hidden" name="fechaVisita" value="<%=day.getFechaFormateada()%>">
        </tr>
      </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>