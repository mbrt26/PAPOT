<html>
<%@ page contentType="application/rnd.ms-excel" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/listaUnUsuario" prefix="usuario" %>
<%@ taglib uri="/WEB-INF/tlds/listaReporteVisitaUnVendedor" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaTotalClienteVendedor" prefix="total" %>

<jsp:useBean id="colaboraReporteLogBean"
  scope="request"
  type="com.solucionesweb.losbeans.colaboraciones.ColaboraReporteLogBean" />

<head>
<title>Reporte Visitas Un Vendedor</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<body>
<b><font size="5" face="Verdana" color="#0000A0">Reporte Visitas Un Vendedor</font></b>
<form method="POST" action="GralControladorServlet">

  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstReporteVisitaUnVendedorArchivo.jsp">

<table border="1" cellspacing="0" width="90%">

  <usuario:listaUnUsuario idUsuarioTag = "<%=colaboraReporteLogBean.getIdUsuarioStr()%>">
  <tr>
    <td width="100%" nowrap align="center"><big><strong><font face="Verdana">VENDEDOR <%=nombreUsuarioVar%></font></strong></big></td>
  </tr>
  </usuario:listaUnUsuario>

  <tr>
    <td width="100%" nowrap align="center"><big><strong><font face="Verdana">Del <%=colaboraReporteLogBean.getFechaInicial()%>
                                                                             Al <%=colaboraReporteLogBean.getFechaFinal()%></font></strong></big></td>
  </tr>
</table>

   <table border="1" cellspacing="0" width="90%">

      <lista:listaFechaVisita idUsuarioTag = "<%=colaboraReporteLogBean.getIdUsuarioStr()%>"
                           fechaInicialTag = "<%=colaboraReporteLogBean.getFechaInicial()%>"
                             fechaFinalTag = "<%=colaboraReporteLogBean.getFechaFinal()%>">

       <table border="1" cellspacing="0" width="90%">
        <tr>
          <td width="100%" align="center" class="letraTitulo"><%=fechaVisitaVar%></td>
        </tr>
       </table>

      <table border="1" cellspacing="0" width="90%">
        <tr>
          <td width="15%" align="left" class="letraTitulo">IDCLIENTE</td>
          <td width="30%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
          <td width="15%" align="center" class="letraTitulo">HORA VISITA</td>
          <td width="15%" align="left" class="letraTitulo">NOMBRE VISITA</td>
        </tr>

      <lista:listaVisita idUsuarioTag = "<%=colaboraReporteLogBean.getIdUsuarioStr()%>"
                       fechaVisitaTag = "<%=fechaVisitaVar%>">
        <tr>
          <td width="15%" align="left" class="letraDetalle"><%=idClienteVar%></td>
          <td width="30%" align="left" class="letraDetalle"><%=nombreClienteVar%></td>
          <td width="15%" align="center" class="letraDetalle"><%=horaVisitaVar%></td>
          <td width="15%" align="left" class="letraDetalle"><%=nombreEstadoVar%></td>
        </tr>
     </lista:listaVisita>

   </table>

     </lista:listaFechaVisita>

   </table>


   <table border="1" cellspacing="0" width="90%">
         <tr>
          <td width="100%" nowrap align="center"><big><strong><font face="Verdana"> TOTAL CLIENTES X VENDEDOR </font></strong></big></td>
        </tr>
   </table>

   <table border="1" cellspacing="0" width="90%">

        <tr>
          <td width="15%" align="left" class="letraTitulo">IDVENDEDOR</td>
          <td width="30%" align="left" class="letraTitulo">NOMBRE VENDEDOR</td>
          <td width="15%" align="center" class="letraTitulo">&nbsp;</td>
          <td width="15%" align="right" class="letraTitulo">#CLIENTES</td>
        </tr>

      <total:listaTotalClienteVendedor idUsuarioTag = "<%=colaboraReporteLogBean.getIdUsuarioStr()%>"
                                    fechaInicialTag = "<%=colaboraReporteLogBean.getFechaInicial()%>"
                                      fechaFinalTag = "<%=colaboraReporteLogBean.getFechaFinal()%>">

        <tr>
          <td width="15%" align="left" class="letraDetalle"><%=idUsuarioVar%> </td>
          <td width="30%" align="left" class="letraDetalle"><%=nombreUsuarioVar%> </td>
          <td width="15%" align="right" class="letraDetalle">&nbsp;</td>
          <td width="15%"  align="right" class="letraDetalle"><%=totalOrdenesVar%> </td>
        </tr>

     </total:listaTotalClienteVendedor>

   </table>


</form>
</body>

</html>