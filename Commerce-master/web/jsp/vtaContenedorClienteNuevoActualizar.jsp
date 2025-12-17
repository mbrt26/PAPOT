<html>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<%@ taglib uri="/WEB-INF/tlds/listaClientePendientes" prefix="lista" %>

<jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
<jsp:setProperty name="jhDate" property="*" />
</jsp:useBean>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

<head>
<title>Actualizar Cliente</title>
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
</font>
<body bgcolor="#FFFFFF" topmargin="2" leftmargin="2">
<form method="POST" action="GralControladorServlet">
  <font face="Verdana" size="1">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorClienteNuevoActualizar.jsp">
  <input size="17" type="hidden" value="<%=jhDate.getDate(5,true)%>" name="fechaVisita">
   </font>

   <table border="0" width="100%">
        <tr>
          <th width="100%" nowrap><div align="left"><p><font face="Verdana" size="2" color="#0000A0"><strong>ACTUALIZAR CLIENTE</strong></font></th>
        </tr>
        <tr>
          <td width="100%"><small><strong><font size="2" face="Verdana">FECHA <%=jhDate.getDate(5,true)%></font></strong></small></td>
        </tr>
    </table>


   <table border="1" width="100%">

    <lista:listaClientePendientes idUsuarioTag = "<%=fachadaAgendaLogVisitaBean.getIdUsuarioStr()%>">
   	<tr>
		<td class="letraDetalle">
           <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorClienteNuevoActualizar.jsp&accionContenedor=seleccionar&idLog=<%=idLogVar%>">
           <br><b><%=idClienteVar%> <%=nombreClienteVar%></b></a>
        </td>
	</tr>
    </lista:listaClientePendientes>

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