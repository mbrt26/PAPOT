<%@ page import="java.util.*" %>
<jsp:useBean id="day" scope="page" class="com.solucionesweb.losbeans.utilidades.Day">
<jsp:setProperty name="day" property="*"/>
</jsp:useBean>

<html>
  <head>
    <title>Reporte Visitas Un Vendedor</title>
  </head>


  <body>
    <b><font size="5" face="Verdana" color="#0000A0">Reporte Visitas Un Vendedor</font></b>
    <p>
        <b><font face="Verdana" size="2"><font color="#000000">Información Requerida
               (</font><font color="#0000A0">Fecha Inicial y Final</font><font color="#000000">)
               en Seleccionar</font>
           </font
           </b>
    </p>
    <form method="POST" action="GralControladorServlet">
      <table border="0" width="68%">
        <tr>
          <td width="1%" nowrap>
            <b><font face="Verdana"><input type="submit" value="Consultar" name="accionContenedor"></font></b></td>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
            <b><font face="Verdana"><input type="submit" value="Regresar" name="accionContenedor"></font></b></td>
        </tr>
       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorReporteVisitaUnVendedor.jsp">
      </b>
        <tr>
          <td width="1%" nowrap><font face="Verdana" size="1" color="#0000A0"><b>Fecha
            Inicial (aaaa/mm/dd)</b></font></td>
        <b>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
          <font face="Verdana"><input type="text" name="fechaInicial" size="10" value="<%=day.getFechaFormateada()%>"></font></td>
        </tr>
      </b>
        <tr>
          <td width="1%" nowrap><font face="Verdana" size="1" color="#0000A0"><b>Fecha
            Final (aaaa/mm/dd)</b></font></td>
        <b>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
          <font face="Verdana"><input type="text" name="fechaFinal" size="10" value="<%=day.getFechaFormateada()%>"></font></td>
        </b>
      </tr>
      <tr>
          <td width="1%" nowrap><font face="Verdana" size="1" color="#0000A0"><b>Destinación</b></font></td>
        <b>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
          <font face="Verdana">
          <%@ include file="./comboDestinacion.jsp" %></font></td>
        </b>
      </tr>
      <tr>
          <td width="1%" nowrap><font face="Verdana" size="1" color="#0000A0"><b>Vendedor</b></font></td>
        <b>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
          <font face="Verdana">
          <%@ include file="./comboUsuario.jsp" %></font></td>
       </b>
      </tr>
    </table>
  </form>
</b>
  </body>
</html>