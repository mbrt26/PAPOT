<jsp:useBean id="day" scope="page" class="com.solucionesweb.losbeans.utilidades.Day">
<jsp:setProperty name="day" property="*"/>
</jsp:useBean>

  <html>
  <head>
    <title>Cargar Agenda</title>
  </head>
  <body>
    <form method="POST" action="GralControladorServlet">
  <table border="0" width="269">
        <tr>
           <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>CARGAR AGENDA</strong></font></th>
        </tr>
  </table>

      <table border="0" width="40%">
        <tr>
            <b><font face="Verdana" size="1">Fecha Visita (AAAA/MM/DD)</font></b></td>
        </tr>
        <tr>
            <b><font face="Verdana" size="1"><%=day.getFechaFormateada()%></font></b></td>
        </tr>
        <tr>
            <b><font face="Verdana" size="1"><input type="submit" value="Cargar" name="accionContenedor"></font></b></td>
        </tr>
        <tr>
            <b><font face="Verdana" size="1"><input type="submit" value="Salir" name="accionContenedor"></font></b></td>
        </tr>
       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorAgendaCargar.jsp">
       <input type="hidden" name="fechaVisita" value="<%=day.getFechaFormateada()%>">
    </table>
  </form>
    </b>
  </body>
</html>