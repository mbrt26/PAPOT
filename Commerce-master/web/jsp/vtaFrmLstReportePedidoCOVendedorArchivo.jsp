<html>
<%@ page contentType="application/rnd.ms-excel" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/listaReportePedidoCOVendedor" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaTotalTipoOrdenFechaCO" prefix="total" %>
<%@ taglib uri="/WEB-INF/tlds/listaLocal" prefix="local" %>
<%@ page import="com.solucionesweb.losbeans.negocio.UsuarioBean" %>

<jsp:useBean id="colaboraReporteDctoBean"
  scope="request"
  type="com.solucionesweb.losbeans.colaboraciones.ColaboraReporteDctoBean" />

    <%
       String idTipoOrden   = "9";

       //
       HttpSession sesion = request.getSession();
       UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

    %>

<head>
<title>Reporte Pedidos C.O./Vendedor</title>
<link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<body>
<b><font size="5" face="Verdana" color="#0000A0">Reporte Pedidos C.O./Vendedor</font></b>
<form method="POST" action="GralControladorServlet">

  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstReportePedidoCOVendedorArchivo.jsp">

<table border="1" cellspacing="0" width="90%">

  <local:listaLocal idLocalTag = "<%=colaboraReporteDctoBean.getIdLocalStr()%>">
  <tr>
    <td width="100%" nowrap align="center"><big><strong><font face="Verdana"><%=razonSocialVar%> <%=nombreLocalVar%></font></strong></big></td>
  </tr>
  </local:listaLocal>

  <tr>
    <td width="100%" nowrap align="center"><big><strong><font face="Verdana">Del <%=colaboraReporteDctoBean.getFechaInicial()%>
                                                                             Al <%=colaboraReporteDctoBean.getFechaFinal()%></font></strong></big></td>
  </tr>
</table>

      <lista:fechaTipoOrden idUsuarioTag = "<%=usuarioBean.getIdUsuarioStr()%>"
                          idTipoOrdenTag = "<%=colaboraReporteDctoBean.getIdTipoOrdenStr()%>"
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

          <lista:pedidoTipoOrden idUsuarioTag = "<%=usuarioBean.getIdUsuarioStr()%>"
                               idTipoOrdenTag = "<%=colaboraReporteDctoBean.getIdTipoOrdenStr()%>"
                                fechaOrdenTag = "<%=fechaOrdenVar%>">


             <tr>
               <td width="15%" align="left"  class="letraDetalle"><%=idUsuarioVar%> </td>
               <td width="30%" align="left"  class="letraDetalle"><%=nombreUsuarioVar%> </td>
               <td width="15%" align="right" class="letraDetalle"><%=totalOrdenesVar%> </td>
               <td width="15%" align="right" class="letraDetalle"><%=totalVrVentaConIvaVar%> </td>
             </tr>
          </lista:pedidoTipoOrden>

        </table>

     </lista:fechaTipoOrden>

   </table>

   <table border="1" cellspacing="0" width="90%">
         <tr>
          <td width="100%" nowrap align="center"><big><strong><font face="Verdana"> TOTALES </font></strong></big></td>
        </tr>
   </table>

   <table border="1" cellspacing="0" width="90%">
        <tr>
          <td width="15%" align="left" class="letraTitulo">IDVENDEDOR</td>
          <td width="30%" align="left" class="letraTitulo">NOMBRE VENDEDOR</td>
          <td width="15%" align="right" class="letraTitulo">#PEDIDOS</td>
          <td width="15%" align="right" class="letraTitulo">$TOTAL PEDIDOS</td>
        </tr>
      <total:listaTotalTipoOrdenFechaCO idUsuarioTag = "<%=usuarioBean.getIdUsuarioStr()%>"
                                      idTipoOrdenTag = "<%=colaboraReporteDctoBean.getIdTipoOrdenStr()%>"
                                     fechaInicialTag = "<%=colaboraReporteDctoBean.getFechaInicial()%>"
                                       fechaFinalTag = "<%=colaboraReporteDctoBean.getFechaFinal()%>">
        <tr>
          <td width="15%" align="left" class="letraDetalle"><%=idUsuarioVendorVar%> </td>
          <td width="30%" align="left" class="letraDetalle"><%=nombreUsuarioVar%> </td>
          <td width="15%" align="right" class="letraDetalle"><%=totalOrdenesVar%> </td>
          <td width="15%" align="right" class="letraDetalle"><%=totalVrVentaConIvaVar%> </td>
        </tr>
     </total:listaTotalTipoOrdenFechaCO>

   </table>


</form>
</body>

</html>                  ;