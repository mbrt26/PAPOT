<html>
<%@ page contentType="application/rnd.ms-excel" %>
<%@ taglib uri="/WEB-INF/tlds/listaLocal" prefix="local" %>
<%@ taglib uri="/WEB-INF/tlds/listaReporteVisitaActual" prefix="lista" %>
<%@ page import="com.solucionesweb.losbeans.negocio.UsuarioBean" %>

<jsp:useBean id="colaboraReporteLogBean"
  scope="request"
  type="com.solucionesweb.losbeans.colaboraciones.ColaboraReporteLogBean" />

    <%
       String idTipoOrden   = "9";

       //
       HttpSession sesion = request.getSession();
       UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

    %>

<head>
<title>Reporte Ultima Visita C.O./Vendedor</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<body>
<b><font size="5" face="Verdana" color="#0000A0">Reporte Ultima Visita C.O./Vendedor</font></b>
<form method="POST" action="GralControladorServlet">

  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstReporteVisitaActualCOVendedor.jsp">

<table border="1" cellspacing="0" width="90%">

  <local:listaLocal idLocalTag = "<%=colaboraReporteLogBean.getIdLocalStr()%>">
  <tr>
    <td width="100%" nowrap align="center"><big><strong><font face="Verdana"><%=razonSocialVar%> <%=nombreLocalVar%></font></strong></big></td>
  </tr>
  </local:listaLocal>

  <tr>
    <td width="100%" nowrap align="center"><big><strong><font face="Verdana">Del <%=colaboraReporteLogBean.getFechaInicial()%>
                                                                              Al <%=colaboraReporteLogBean.getFechaFinal()%></font></strong></big></td>
  </tr>
</table>

   <table border="1" cellspacing="0" width="90%">

      <lista:listaFechaVisita idUsuarioTag = "<%=usuarioBean.getIdUsuarioStr()%>"
                           fechaInicialTag = "<%=colaboraReporteLogBean.getFechaInicial()%>"
                             fechaFinalTag = "<%=colaboraReporteLogBean.getFechaFinal()%>">

       <table border="1" cellspacing="0" width="90%">
        <tr>
          <td width="100%" align="center" class="letraTitulo"><%=fechaOrdenVar%></td>
        </tr>
       </table>


   <table border="1" cellspacing="0" width="90%">
        <tr>
          <td width="30%" align="left" class="letraTitulo">NOMBRE VENDEDOR</td>
          <td width="3%" align="left" class="letraTitulo">&nbsp;</td>
          <td width="35%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
          <td width="15%" align="left" class="letraTitulo">NOMBRE VISITA</td>
          <td width="15%" align="center" class="letraTitulo">HORA VISITA</td>
        </tr>
      <lista:visitaFechaCO idUsuarioTag = "<%=usuarioBean.getIdUsuarioStr()%>"
                          fechaOrdenTag = "<%=fechaOrdenVar%>">
        <tr>
          <td width="30%" align="left" class="letraDetalle"><%=nombreUsuarioVar%> </td>
          <td width="3%"  align="left" class="letraDetalle">&nbsp;</td>
          <td width="35%" align="left" class="letraDetalle"><%=nombreClienteVar%> </td>


          <%if (nombreEstadoVar.compareTo("Seguimiento") == 0 ) { %>

          <td width="15%" align="left" class="letraDetalle">
          <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstReporteVisitaCOVendedorContacto.jsp&accionContenedor=contacto&idLog=<%=idLogVar%>" target="popup" onClick="window.open(this.href, this.target, 'width=300,height=400'); return false;"><%=nombreEstadoVar%></a>
          </td>

          <% } else { %>

            <td width="15%" align="left" class="letraDetalle"><%=nombreEstadoVar%> </td>

          <% } %>

          <td width="15%" align="left" class="letraDetalle"><%=horaVisitaVar%></td>
        </tr>
     </lista:visitaFechaCO>
   </table>


     </lista:listaFechaVisita>

   </table>


</form>
</body>

</html>