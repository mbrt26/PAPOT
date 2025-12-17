<html>
<head>
<title>Programar Agenda</title>
</head>
<body bgcolor="#FFFFFF" topmargin="2" leftmargin="2">
<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorAgendaProgramar.jsp">
    <table border="0" width="17%">
    <tr>
    <th width="169" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>PROGRAMAR AGENDA</strong></font></th>
    </tr>
    <tr>
    <td width="169"><small><strong><small><font face="Verdana">IdCliente</font></small></strong></small></td>
    </tr>
    </table>

    <table border="0" width="184">
       <textarea COLS=40 ROWS=3 name="txtIdClienteAll" > </TEXTAREA>
    </table>

    <table border="0" width="177">
        <tr>
          <font face="Verdana" size="1"><b><td width="171"><input type="submit" value="Confirmar"
          name="accionContenedor"></b></font></td>
        </tr>
        <tr>
          <font face="Verdana" size="1"><b><td width="171">
          <input type="submit" value="Regresar" name="accionContenedor"></b></font></td>
        </tr>
    </table>

</form>
</body>
</html>