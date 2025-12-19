<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<%@ taglib uri="/WEB-INF/tlds/listaDevolucionOrden" prefix="lsu" %>
<%@ taglib uri="/WEB-INF/tlds/listaLocal" prefix="lst" %>

<jsp:useBean id="fachadaDctoBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoBean" />

<head>
    <title>ADICIONAR PRODUCTOS</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelResurtidoDespacho.jsp">
<input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoBean.getIdTipoOrdenStr()%>">
<input type="hidden" name="xIdLocal" value="<%=fachadaDctoBean.getIdLocalStr()%>">
<input type="hidden" name="xIdOrden" value="<%=fachadaDctoBean.getIdOrdenStr()%>">
<input type="hidden" name="xIdTipoAjuste" value="<%=fachadaDctoBean.getIdTipoAjusteStr()%>">

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">AJUSTE
                                                      <br> DESPACHO ORIGEN <%=fachadaDctoBean.getIdOrdenStr()%>
        </td>
        <td width="33%" class="letraTitulo">&nbsp;</td>
    </tr>
    <tr>
        <td width="33%" class="letraResaltadaTitulo">
                    <jsp:include page="./comboLocal.jsp"/>
        </td>
        <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
        <td width="33%" class="letraTitulo">
            <jsp:include page="./comboFechaHoy.jsp"/>
        </td>
    </tr>

    <lst:listaLocal idLocalTag="<%=fachadaDctoBean.getIdLocalStr()%>">
        <tr>
        <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
        <td width="34%" align="center" class="letraDetalle">&nbsp;</td>
        <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
        </tr>

        <tr>
            <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
            <td width="34%" align="center" class="letraDetalle">&nbsp;</td>
            <td width="33%" align="center" class="letraDetalle"><%=nombreLocalVar%></td>
        </tr>
    </lst:listaLocal>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="10%" align="right" class="letraTitulo">CANTIDAD</td>
        <td width="10%" align="right" class="letraTitulo">CANTIDAD</td>
        <td width="10%" align="right" class="letraTitulo">VR.SUBTOTAL</td>
        <td width="10%" align="center" class="letraTitulo">REFERENCIA</td>
        <td width="40%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>

   </tr>



    <lsu:listaDevolucionOrden idLocalTag = "<%=fachadaDctoBean.getIdLocalStr()%>"
                     idTipoOrdenTag = "<%=fachadaDctoBean.getIdTipoOrdenStr()%>"
                          idOrdenTag = "<%=fachadaDctoBean.getIdOrdenStr()%>"
                       indicadorTag = "<%=fachadaDctoBean.getIndicadorStr()%>">

             <input type="hidden" name="chkIdReferencia" value="<%=idPluVar%>">
             <input type="hidden" name="chkVrVentaUnitario" value="<%=vrVentaUnitarioVar%>">
             <input type="hidden" name="chkCantidadOrigen" value="<%=cantidadVar%>">

    <tr>
        <td width="10%" align="right" class="letraDetalle">
             <input type="text"   name="chkCantidad" value="" size="6">
         </td>
        <td width="10%" align="right" class="letraDetalle">
                <%=cantidadVar%>
            </td>
        <td width="10%" align="right" class="letraDetalle">
                <%=vrSubtotalVentaDf0Var%>
            </td>
        <td width="10%" align="center" class="letraDetalle">
              <%=idPluVar%>
            </td>
        <td width="40%" align="left" class="letraDetalle">
                <%=nombrePluVar.trim()%>
            </td>

    </tr>

    </lsu:listaDevolucionOrden>

</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" align="center" class="letraTitulo">
            <input type="submit" value="Guardar" name="accionContenedor">
        </td>
        <td width="34%" align="left" class="letraTitulo">
            <a href="javascript:window.history.back()"><img src="./img/ARW06LT.GIF" width="32" height="32"></a>
        </td>
        <td width="33%" align="right" class="letraTitulo">&nbsp;</td>
    </tr>
</table>
</form>
</body>
</html>