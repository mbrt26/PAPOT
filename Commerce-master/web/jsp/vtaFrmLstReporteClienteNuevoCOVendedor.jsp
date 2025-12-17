<html>

<%@ taglib uri="/WEB-INF/tlds/listaLocal" prefix="local" %>
<%@ taglib uri="/WEB-INF/tlds/listaClienteNuevoCOVendedor" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaTotalClienteNuevo" prefix="total" %>

<jsp:useBean id="colaboraReporteDctoBean"
  scope="request"
  type="com.solucionesweb.losbeans.colaboraciones.ColaboraReporteDctoBean" />

<head>
<title>Reporte Cliente Nuevo C.O./Vendedor</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>

<body>
<b><font size="5" face="Verdana" color="#0000A0">Reporte Cliente Nuevo C.O./Vendedor</font></b>
<form method="POST" action="GralControladorServlet">

  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstReporteClienteNuevoCOVendedor.jsp">

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

      <lista:fechaNuevo idLocalTag = "<%=colaboraReporteDctoBean.getIdLocalStr()%>"
                   fechaInicialTag = "<%=colaboraReporteDctoBean.getFechaInicial()%>"
                     fechaFinalTag = "<%=colaboraReporteDctoBean.getFechaFinal()%>">

       <table border="1" cellspacing="0" width="90%">
        <tr>
          <td width="100%" align="center" class="letraTitulo"><%=fechaVisitaVar%></td>
        </tr>
       </table>

       <table border="1" cellspacing="0" width="90%">

        <tr>
          <td width="30%" align="left" class="letraTitulo">NOMBRE VENDEDOR</td>
          <td width="15%" align="left" class="letraTitulo">IDCLIENTE</td>
          <td width="30%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
          <td width="15%" align="left" class="letraTitulo">TELEFONO</td>
        </tr>

        <table border="1" cellspacing="0" width="90%">

          <lista:clienteNuevo idLocalTag = "<%=colaboraReporteDctoBean.getIdLocalStr()%>"
                          fechaVisitaTag = "<%=fechaVisitaVar%>">
             <tr>
               <td width="30%" align="left" class="letraDetalle"><%=nombreUsuarioVar%> </font></small></td>
               <td width="15%" align="left" class="letraDetalle"><%=idClienteVar%> </font></small></td>
               <td width="30%" align="left" class="letraDetalle"><%=nombreClienteVar%> </font></small></td>
               <td width="15%" align="left" class="letraDetalle"><%=telefonoVar%> </font></small></td>
             </tr>
          </lista:clienteNuevo>

        </table>

     </lista:fechaNuevo>


   </table>

   <table border="1" cellspacing="0" width="90%">
         <tr>
          <td width="100%" nowrap align="center"><big><strong><font face="Verdana">TOTAL CLIENTES NUEVOS X VENDEDOR </font></strong></big></td>
        </tr>
   </table>

   <table border="1" cellspacing="0" width="90%">

        <tr>
          <td width="30%" align="left" class="letraTitulo">NOMBRE VENDEDOR</td>
          <td width="15%" align="left" class="letraTitulo">&nbsp;</td>
          <td width="30%" align="left" class="letraTitulo">&nbsp;</td>
          <td width="15%" align="left" class="letraTitulo">TOTAL CLIENTES</td>
        </tr>


      <total:listaTotalClienteNuevo idLocalTag = "<%=colaboraReporteDctoBean.getIdLocalStr()%>"
                               fechaInicialTag = "<%=colaboraReporteDctoBean.getFechaInicial()%>"
                                 fechaFinalTag = "<%=colaboraReporteDctoBean.getFechaFinal()%>">
        <tr>
          <td width="30%" align="left" class="letraDetalle"><%=nombreUsuarioVar%> </font></small></td>
          <td width="15%" align="left" class="letraDetalle">&nbsp;</td>
          <td width="30%" align="left" class="letraDetalle">&nbsp;</td>
          <td width="15%" align="left" class="letraDetalle"><%=totalNuevoVar%></td>
        </tr>
     </total:listaTotalClienteNuevo>

   </table>

   <table border="0" cellspacing="0" width="90%">
        <tr>
          <td width="100%" style="font-family: Verdana; font-size: 10 px">
            <b><font face="Verdana"><input type="submit" value="Regresar" name="accionContenedor"></font></b></td>
        </tr>
   </table>

</form>
</body>

</html>