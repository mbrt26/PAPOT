<html>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<%@ taglib uri="/WEB-INF/tlds/listaDevolucion" prefix="lsu" %>
<%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>

<jsp:useBean id="fachadaDctoBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoBean" />

<jsp:useBean id="fachadaTerceroBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />


<head>
    <title>ADICIONAR PRODUCTOS</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
</head>
<body>

<form method="POST" action="GralControladorServlet">
<input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelResurtidoNota.jsp">
<input type="hidden" name="xIdDcto" value="<%=fachadaDctoBean.getIdDctoSf0()%>">
<input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoBean.getIdTipoOrdenStr()%>">
<input type="hidden" name="xIdLocal" value="<%=fachadaDctoBean.getIdLocalStr()%>">
<input type="hidden" name="xIndicador" value="<%=fachadaDctoBean.getIndicadorStr()%>">
<input type="hidden" name="xTipoNota" value="<%=fachadaDctoBean.getTipoNota()%>">
<input type="hidden" name="xIdOrden" value="<%=fachadaDctoBean.getIdOrdenStr()%>">
<input type="hidden" name="xTipoNota" value="<%=fachadaDctoBean.getTipoNota()%>">

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" class="letraTitulo">&nbsp;</td>
        <td width="34%" align="center" class="letraTitulo">REFERENCIAS NOTA CREDITO
                                                      <br> FACTURA ORIGEN <%=fachadaDctoBean.getIdDctoNitCC()%>
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

    <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                  idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
        <tr>
        <td width="33%" align="center" class="letraDetalle">ESTADO</td>
        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
        <td width="33%" align="center" class="letraDetalle">NOMBRE PROVEEDOR</td>
        </tr>

        <tr>
            <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
            <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
            <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
        </tr>
    </lst:listaClienteControlAgendaNit>
</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="10%" align="right" class="letraTitulo">CANTIDAD</td>
        <td width="10%" align="right" class="letraTitulo">CANTIDAD</td>
        <td width="10%" align="right" class="letraTitulo">VR.SUBTOTAL</td>
        <td width="10%" align="center" class="letraTitulo">REFERENCIA</td>
        <td width="40%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
        <td width="20%" align="right" class="letraTitulo">&nbsp;</td>
    </tr>



    <lsu:listaDevolucion idLocalTag = "<%=fachadaDctoBean.getIdLocalStr()%>"
                     idTipoOrdenTag = "<%=fachadaDctoBean.getIdTipoOrdenStr()%>"
                          idDctoTag = "<%=fachadaDctoBean.getIdDctoSf0()%>"
                       indicadorTag = "<%=fachadaDctoBean.getIndicadorStr()%>">

             <input type="hidden" name="chkIdReferencia" value="<%=idPluVar%>">
             <input type="hidden" name="chkVrVentaUnitario" value="<%=vrVentaUnitarioVar%>">
             <input type="hidden" name="chkCantidadOrigen" value="<%=cantidadVar%>">
             <input type="hidden" name="chkPorcentajeIva" value="<%=porcentajeIvaVar%>">
             <input type="hidden" name="chkVrCosto" value="<%=vrCostoVar%>">

    <tr>
        <td width="10%" align="right" class="letraDetalle">
             <input type="text"   name="chkCantidad" value="" size="6">
         </td>
        <td width="10%" align="right" class="letraDetalle">
                <%=cantidadVar%>
            </td>
        <td width="10%" align="right" class="letraDetalle">
                <%=vrCostoConIvaDf0Var%>
            </td>
        <td width="10%" align="center" class="letraDetalle">
              <%=idPluVar%>
            </td>
        <td width="40%" align="left" class="letraDetalle">
                <%=nombrePluVar.trim()%>
            </td>
        <td width="20%" align="right" class="letraDetalle">&nbsp;</td>
    </tr>

    </lsu:listaDevolucion>

</table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="33%" align="center" class="letraTitulo">
            <input type="submit" value="Confirmar" name="accionContenedor">
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