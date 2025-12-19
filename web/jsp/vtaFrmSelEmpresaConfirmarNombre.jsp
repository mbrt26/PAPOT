<html>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
<jsp:setProperty name="jhDate" property="*" />
</jsp:useBean>

<%@ taglib uri="/WEB-INF/tlds/listaClienteNombre.tld" prefix="lista" %>

<jsp:useBean id="fachadaAgendaLogVisitaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

<head>
<title>Confirmar Ingreso Cliente</title>
<link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
  <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorEmpresaSelecciona.jsp">

  <table border="1" width="100%">
    <tr>
        <th width="100%" nowrap class="letraTitulo">CONFIRMAR CLIENTE</th>
    </tr>
  </table>

  <table border="1" width="100%">
    <lista:listaClienteNombre idUsuarioTag="<%=fachadaAgendaLogVisitaBean.getIdUsuarioStr()%>"
                          nombreClienteTag="<%=fachadaAgendaLogVisitaBean.getIdUsuarioStr()%>">
        <tr>
          <td width="25%" class="letraDetalle">
          <input type="radio" name="radIdCliente" value="<%=idClienteSinFormatoVar%>"> <%=idClienteVar%>-<%=nombreTerceroVar%></td>
        </tr>
      </td>
    </tr>
   </lista:listaClienteNombre>
  </table>

  <table border="0" width="100%">
    <tr>
      <td width="25%"><input type="submit" value="Confirmar" name="accionContenedor">
                      <input type="submit" value="Salir" name="accionContenedor1"></td>
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