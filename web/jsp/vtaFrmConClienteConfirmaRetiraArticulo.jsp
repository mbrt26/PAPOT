<html>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<%@ taglib uri="/WEB-INF/tlds/listaClienteCotizacionArticulosRetirar" prefix="listaRetirar" %>
<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <%
       String colorAmarillo = "#FFFF00";
       String colorVerde    = "#00FF00";
       String colorFila     = colorAmarillo;
       String xIdLog        = request.getParameter("idLog");
       String xItem         = request.getParameter("item");
    %>

<head>
<title>Confirmar Retiro</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConClienteConfirmaRetiraArticulo.jsp">

<table border="0" width="100%" cellspacing="1">
    <tr>
        <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>CONFIRMAR RETIRO</strong></font></th>
    </tr>
  </table>

<table border="1" width="100%" cellspacing="1">
    <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                        idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>">
    <tr>
    <td width="100%" nowrap class="letraTitulo"><%=idClienteVar%> <%=nombreClienteVar%></td>
    </tr>
    </lista:listaClienteControlAgendaNit>
    </table>

<table border="1" width="100%" cellspacing="1">


    <listaRetirar:listaClienteCotizacionArticulosRetirar idLogTag = "<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                                     idClienteTag = "<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                                    idSucursalTag = "<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>"
                                                     idUsuarioTag = "<%=fachadaAgendaLogVisitaBean.getIdUsuarioStr()%>">
    <tr>
    <td width="100%" nowrap class="letraDetalle"><%=nombrePluVar%>
                                                <br>Unid# <%=cantidadVar%>
                                                <br>VrVentaUn$ <%=vrVentaUnitarioVar%>
                                                <br>Ref# <%=strIdReferenciaVar%>
                                                </td>
    </tr>


    </listaRetirar:listaClienteCotizacionArticulosRetirar>

</table>

<table border="0" width="100%" cellspacing="1">
    <tr>
      <b><font face="Verdana"><td width="265"><small><small><small><small>
      <input type="submit" value="Confirmar Retiro" name="accionContenedor">
      <input type="submit" value="Regresar" name="accionContenedor"></td>
       <input type="hidden" name="item" value="<%=xItem%>">
       <input type="hidden" name="idLog" value="<%=xIdLog%>">
    </tr>
    <tr>
      <b><font face="Verdana"><td width="265"></font></b></td>
    </tr>
</table>

</form>
</body>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

</html>