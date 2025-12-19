<html>

<%@ taglib uri="/WEB-INF/tlds/listaProducto/" prefix="lista" %>
<jsp:useBean id="fachadaColaboraHistoriaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean" />

  <head>
    <title>Historico Productos</title>
  </head>
  <body>

    <% int nroLinea         = 0;
       String colorAmarillo = "#FFFF00";
       String colorVerde    = "#00FF00";
       String colorFila     = colorAmarillo;
    %>

    <form method="POST" action="GralControladorServlet">


    <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelClienteAdicionaPedido.jsp&accionContenedor=Regresar">
    <b><img src="../img/ARW06LT.GIF" width="32" height="32"></a>

      <table border="0" width="100%">

       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelClienteAdicionaPedido.jsp">
    </table>
    <table border="1" width="100%">

      <tr>
        <td width="100%" bgcolor=<%=colorVerde%>>
		<p align="center"><strong><font face="Verdana" size="1">SELECCIONAR
		PRODUCTO</font></strong></td>
      </tr>

    </table>
    <table border="1" width="100%">
    <lista:listaProducto idClienteTag = "<%=fachadaColaboraHistoriaBean.getIdClienteStr()%>">

      <tr>
        <td width="100%" bgcolor=<%=colorAmarillo%>><font size="1" face="Verdana">
        <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelClienteAdicionaPedido.jsp&accionContenedor=SeleccionaProducto&idProducto=<%=idProductoVar%>&idCliente=<%=fachadaColaboraHistoriaBean.getIdClienteStr()%>">
        <b><%=nombrePluVar.trim()%></a>
        </font></td>
      </tr>
    </lista:listaProducto>
    </table>
  </form>
    </b>
  </body>
</html>