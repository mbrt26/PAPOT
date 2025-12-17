<html>
<%@ page contentType="application/rnd.ms-excel" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/listaReportePedidoCorporativo" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaTotalTipoOrdenFechaCorporativo" prefix="total" %>

<jsp:useBean id="colaboraReporteDctoBean"
  scope="request"
  type="com.solucionesweb.losbeans.colaboraciones.ColaboraReporteDctoBean" />

<head>
<title>Reporte Pedidos Corporativo</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<body>
<b><font size="5" face="Verdana" color="#0000A0">Reporte Pedidos Corporativo</font></b>
<form method="POST" action="GralControladorServlet">

  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstReportePedidoCorporativoArchivo.jsp">

<table border="1" cellspacing="0" width="90%">

  <tr>
    <td width="100%" nowrap align="center"><big><strong><font face="Verdana">Del <%=colaboraReporteDctoBean.getFechaInicial()%>
                                                                             Al <%=colaboraReporteDctoBean.getFechaFinal()%></font></strong></big></td>
  </tr>
</table>

      <lista:fechaTipoOrden idTipoOrdenTag = "<%=colaboraReporteDctoBean.getIdTipoOrdenStr()%>"
                           fechaInicialTag = "<%=colaboraReporteDctoBean.getFechaInicial()%>"
                             fechaFinalTag = "<%=colaboraReporteDctoBean.getFechaFinal()%>">

       <table border="1" cellspacing="0" width="90%">
        <tr>
          <td width="100%" align="center" class="letraTitulo"><%=fechaOrdenVar%></td>
        </tr>
       </table>

       <table border="1" cellspacing="0" width="90%">

        <tr>
          <td width="15%" align="left" class="letraTitulo">IDVENDEDOR</td>
          <td width="30%" align="left" class="letraTitulo">NOMBRE VENDEDOR</td>
          <td width="15%" align="right" class="letraTitulo">#PEDIDOS</td>
          <td width="15%" align="right" class="letraTitulo">$TOTAL PEDIDOS</td>
        </tr>

        <table border="1" cellspacing="0" width="90%">

          <lista:pedidoTipoOrden idTipoOrdenTag = "<%=colaboraReporteDctoBean.getIdTipoOrdenStr()%>"
                                  fechaOrdenTag = "<%=fechaOrdenVar%>">


        <tr>
          <td width="15%" align="left" class="letraDetalle"><%=idUsuarioVar%> </td>
          <td width="30%" align="left" class="letraDetalle"><%=nombreUsuarioVar%> </td>
          <td width="15%" align="right" class="letraDetalle"><%=totalOrdenesVar%> </td>
          <td width="15%" align="right" class="letraDetalle"><%=totalVrVentaConIvaVar%> </td>
        </tr>
          </lista:pedidoTipoOrden>

        </table>

     </lista:fechaTipoOrden>

   </table>

   <table border="1" cellspacing="0" width="90%">
         <tr>
          <td width="100%" nowrap align="center"><big><strong><font face="Verdana"> TOTAL PEDIDOS X VENDEDOR </font></strong></big></td>
        </tr>
   </table>

   <table border="1" cellspacing="0" width="90%">
        <tr>
          <td width="15%" align="left" class="letraTitulo">IDVENDEDOR</td>
          <td width="30%" align="left" class="letraTitulo">NOMBRE VENDEDOR</td>
          <td width="15%" align="right" class="letraTitulo">#PEDIDOS</td>
          <td width="15%" align="right" class="letraTitulo">$TOTAL PEDIDOS</td>
        </tr>
      <total:listaTotalTipoOrdenFechaCorporativo idTipoOrdenTag = "<%=colaboraReporteDctoBean.getIdTipoOrdenStr()%>"
                                                fechaInicialTag = "<%=colaboraReporteDctoBean.getFechaInicial()%>"
                                                  fechaFinalTag = "<%=colaboraReporteDctoBean.getFechaFinal()%>">
        <tr>
          <td width="15%" align="left" class="letraDetalle"><%=idUsuarioVar%> </td>
          <td width="30%" align="left" class="letraDetalle"><%=nombreUsuarioVar%> </td>
          <td width="15%" align="right" class="letraDetalle"><%=totalOrdenesVar%> </td>
          <td width="15%" align="right" class="letraDetalle"><%=totalVrVentaConIvaVar%> </td>
        </tr>
     </total:listaTotalTipoOrdenFechaCorporativo>

   </table>


</form>
</body>

</html>                                                  ;