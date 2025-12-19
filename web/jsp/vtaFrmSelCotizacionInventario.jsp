<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaGrupoNombrePlu" prefix="lista" %>

<jsp:useBean id="fachadaColaboraHistoriaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean" />

  <head>
    <title>Historico Referencia</title>
    <link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
  </head>
  <body>

    <form method="POST" action="GralControladorServlet">

    <table border="1" width="100%">
    <lista:listaGrupoNombrePlu idLineaTag = "<%=fachadaColaboraHistoriaBean.getIdLinea()%>"
                             nombrePluTag = "<%=fachadaColaboraHistoriaBean.getNombrePlu()%>">

      <tr>
        <td width="100%" class="letraDetalle">
        <input type="checkbox" name="chkIdReferencia" value="<%=strIdReferenciaVar%>">
        <b><%=strIdReferenciaVar%> INV=<%=existenciaVar%>
        <br><%=nombrePluVar.trim()%> </b></td>
      </tr>

    </lista:listaGrupoNombrePlu>
    </table>

    <table border="0" width="100%">
       <a href="javascript:window.history.back()"><img src="./img/ARW06LT.GIF" width="32" height="32"></a>
       <input type="submit" value="Confirmar" name="accionContenedor"></b></font></td>
       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelCotizacionInventario.jsp">
    </table>

  </form>
    </b>
  </body>
</html>