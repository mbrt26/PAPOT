<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaPrecioLista" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>

<% String xIdListaPrecio = "1"; %>

<jsp:useBean id="fachadaColaboraHistoriaBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean" />

<head>
    <title>PRODUCTOS</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstUtilPrecioVenta.jsp">

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">PRODUCTOS</td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">
            <jsp:include page="./comboFechaHoy.jsp"/>
        </td>
    </tr>

</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="5%" align="right" class="letraTitulo">PLU</td>
        <td width="45%" nowrap align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
        <td width="5%" nowrap align="center" class="letraTitulo">UD</td>
        <td width="5%" align="right" class="letraTitulo">V.VENTA</td>
        <td width="5%" align="right" class="letraTitulo">EXIST</td>
        <td width="15%" align="left" class="letraTitulo">NOMBRE MARCA</td>
    </tr>

    <lista:listaPrecioLista idLineaTag = "<%=fachadaColaboraHistoriaBean.getIdLinea()%>"
                        nombrePluTag = "<%=fachadaColaboraHistoriaBean.getNombrePlu()%>"
                        idListaPrecioTag = "<%=xIdListaPrecio%>"
                        idLocalTag = "<%=fachadaColaboraHistoriaBean.getIdLocalStr()%>">

             <input type="hidden" name="chkIdReferencia" value="<%=strIdReferenciaVar%>">
             <input type="hidden" name="chkVrVentaUnitario" value="<%=vrVentaUnitarioVar%>">

    <tr>
        <td width="5%" align="right" class="letraDetalle">
            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstUtilPrecioVenta.jsp&accionContenedor=Traer&xIdPlu=<%=strIdReferenciaVar%>"><%=strIdReferenciaVar%></a>
        </td>
        <td width="45%" nowrap align="left" class="letraDetalle"><%=nombrePluVar.trim()%></td>
        <td width="5%" nowrap align="center" class="letraDetalleDespacho"><%=factorDespachoVar%></td>
        <td width="5%" align="right" class="letraDetalle"><%=vrVentaUnitarioDf0Var%></td>
        <td width="5%" align="right" class="letraDetalle"><%=existenciaVar%></td>
        <td width="15%" align="left" class="letraDetalle"><%=nombreMarcaVar.trim()%></td>
    </tr>

    </lista:listaPrecioLista>

</table>
<script type="text/javascript">
    document.getElementById('chkCantidad').focus();
</script>
<table border="0" width="90%" id="tablaTitulo">
    <tr>
         <td width="34%" align="left" class="letraTitulo">
            <a href="javascript:window.history.back()"><img src="./img/ARW06LT.GIF" width="32" height="32"></a>
        </td>
        <td width="33%" align="right" class="letraTitulo">&nbsp;</td>
    </tr>
</table>

</form>
</body>
</html>