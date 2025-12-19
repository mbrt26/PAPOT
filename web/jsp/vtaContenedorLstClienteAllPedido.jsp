
<html>
  <head>
    <title>Estado Pedido</title>
  </head>

<jsp:useBean id="fachadaColaboraReporteDctoBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

  <body>
    <b><font size="5" face="Verdana" color="#0000A0">Estado Pedido</font></b>
    <form method="POST" action="GralControladorServlet">
      <table border="0" width="68%">
        <tr>
          <td width="1%" nowrap>
            <b><font face="Verdana"><input type="submit" value="Consultar" name="accionContenedor"></font></b></td>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
            <b><font face="Verdana"><input type="submit" value="Regresar" name="accionContenedor"></font></b></td>
        </tr>
       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorLstClienteAllPedido.jsp">
      </b>
        <tr>
          <td width="1%" nowrap><font face="Verdana" size="1" color="#0000A0"><b>Fecha
            Inicial</b></font></td>
        <b>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
          <font face="Verdana"><input type="text" name="fechaInicial" size="10" value="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"></font></td>
        </tr>
        <tr>
          <td width="1%" nowrap><font face="Verdana" size="1" color="#0000A0"><b>Fecha
            Final</b></font></td>
        <b>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
          <font face="Verdana"><input type="text" name="fechaFinal" size="10" value="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>"></font></td>
        </b>
      </tr>
      </b>
    </table>
  </form>
</b>
  </body>
</html>