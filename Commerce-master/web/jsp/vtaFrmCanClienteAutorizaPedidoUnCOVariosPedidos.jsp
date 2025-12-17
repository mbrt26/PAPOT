<html>

<%@ taglib uri="/WEB-INF/tlds/listaDetallePedidoIdLog" prefix="lista" %>
<%@ taglib uri="/WEB-INF/tlds/listaDctoEstadoActual" prefix="combobox" %>
<%@ taglib uri="/WEB-INF/tlds/listaEstadoReferencia" prefix="comboReferencia" %>

<jsp:useBean id="fachadaImpuestoVentaBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaImpuestoVentaBean" />

<jsp:useBean id="fachadaCodeMasterBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaCodeMasterBean" />

<jsp:useBean id="fachadaColaboraTerceroBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaColaboraTerceroBean" />

<jsp:useBean id="fachadaUsuarioBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaUsuarioBean" />

<jsp:useBean id="fachadaDctoOrdenBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

<jsp:useBean id="fachadaColaboraDctoOrdenBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean" />

  <head>
    <title>Confirmar Cancelar Pedido</title>
    <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
  </head>

  <body>
    <b><font size="5" face="Verdana" color="#0000A0">CONFIRMAR CANCELAR PEDIDO</font><font face="Verdana">
	</font> </b>

    <form method="POST" action="GralControladorServlet">

<table border="0" width="100%" class="letraTitulo">
	<tr>
		<td width="9%"><b><font face="Verdana" size="1">
		<img src="/Comercial/imagenes/logo30.JPG"></font></b></td>
		<td width="73%" colspan="3">
		<table border="0" width="99%">
			<tr>
				<td class="letraDetalle">NOMBRE CLIENTE</td>
				<td class="letraDetalle"><%=fachadaColaboraTerceroBean.getNombreCliente()%></td>
			</tr>
			<tr>
				<td class="letraDetalle">DIRECCIÓN</td>
				<td class="letraDetalle"><font face="Verdana"><%=fachadaDctoOrdenBean.getDireccionDespacho()%></td>
			</tr>
			<tr>
				<td class="letraDetalle">CIUDAD</td>
   			    <td class="letraDetalle"><%=fachadaDctoOrdenBean.getCiudadDespacho()%></td>
			</tr>
			<tr>
				<td class="letraDetalle">VENDEDOR</td>
				<td class="letraDetalle"><%=fachadaColaboraTerceroBean.getIdRuta().trim()%> - <%=fachadaUsuarioBean.getNombreUsuario()%></td>
			</tr>
			<tr>
				<td class="letraDetalle">ESTADO CLIENTE</td>
				<td class="letraDetalle"><%=fachadaColaboraTerceroBean.getNombreEstadoCliente()%></td>
			</tr>
		</table>
		</td>
		<td colspan="3">
		<table border="0" width="100%" id="table2">
			<tr>
				<td width="171" align="center" class="letraDetalle">FECHA PEDIDO</td>
				<td height="18" align="center" class="letraDetalle">CONDICION DE PAGO</td>
			</tr>
			<tr>
				<td width="171" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getFechaOrdenCorta()%></td>
				<td align="center" class="letraDetalle"><%=fachadaCodeMasterBean.getNombreCode()%></td>
			</tr>
			<tr>
				<td width="171" align="center" class="letraDetalle">CEDULA / NIT</td>
				<td align="center"class="letraDetalle">CODIGO CLIENTE</td>
			</tr>
			<tr>
				<td width="171" align="center" class="letraDetalle"><%=fachadaColaboraTerceroBean.getNitCC()%></td>
				<td align="center" class="letraDetalle"><%=fachadaColaboraTerceroBean.getIdCliente()%></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td width="8%" align="left" class="letraTitulo">REFERENCIA</td>
		<td width="20%" align="right" class="letraTitulo">
		<p align="left">DESCRIPCIÓN</td>
		<td width="5%" align="right" class="letraTitulo">INV.ACTUAL</td>
		<td width="6%" align="right" class="letraTitulo">CANTIDAD</td>
		<td width="8%" align="right" class="letraTitulo">VR.ORIGINAL</td>
		<td width="6%" align="right" class="letraTitulo">VR.VENTA</td>
		<td width="6%" align="right" class="letraTitulo">TOTAL</td>
	</tr>

  <lista:listaDetallePedidoIdLog idOrdenTag = "<%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%>"
                             idTipoOrdenTag = "<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>">
     <%
      String strEstiloLetra = "letraDetalle";
      if (vrVentaOriginalVar.compareTo(vrVentaUnitarioVar) != 0) {
         strEstiloLetra = "letraResaltada";
      }
     %>

 	<tr>
		<td width="8%" align="left" class="letraDetalle"><%=strIdReferenciaVar%></td>
		<td width="20%" align="left" class="letraDetalle"><%=nombrePluVar%></td>
		<td width="5%" align="right" class="letraDetalle"><%=existenciaVar%></td>
		<td width="6%" align="right" class="letraDetalle"><%=cantidadVar%></td>
		<td width="8%" align="right" class="letraDetalle"><%=vrVentaOriginalVar%></td>
		<td width="6%" align="right" class="<%=strEstiloLetra%>"><%=vrVentaUnitarioVar%></td>
		<td width="6%" align="right" class="letraDetalle"><%=vrVentaSinIvaVar%></td>
        <font face="Verdana">
        <input type="hidden" name="item" value="<%=itemVar%>" style="font-weight: 700">
		</font>
	</tr>

  </lista:listaDetallePedidoIdLog>


	<tr>
		<td colspan="4" valign="top">

		&nbsp;<table border="1" width="100%" id="table4">
			<tr>
				<td width="10%" class="letraDetalle">ORDEN COMPRA</td>
				<td width="13%" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getOrdenCompra()%></td>
				<td width="10%" class="letraDetalle">DESCUENTO</td>
				<td width="12%" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getDescuentoComercial()%></td>
				<td width="10%" class="letraDetalle">TIPO VENTA</td>
				<td width="13%" class="letraDetalle"><%=fachadaImpuestoVentaBean.getNombreImpuesto()%></td>
				<td width="10%" class="letraDetalle">PEDIDO</td>
				<td width="11%" class="letraDetalle"><%=fachadaCodeMasterBean.getNombreCode()%></td>
			</tr>
			<tr>
				<td colspan="8" class="letraDetalle">OBSERVACIONES</td>
			</tr>
			<tr>
				<td colspan="8" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getObservacion()%></td>
			</tr>
			<tr>
				<td colspan="8" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getContacto()%></td>
			</tr>
			<tr>
				<td colspan="8" class="letraDetalle">&nbsp;</td>
			</tr>
		</table>
		</td>
		<td colspan="3">
		<table border="1" width="100%" id="table3">
			<tr>
				<td width="172" class="letraDetalle">TOTAL BRUTO</td>
				<td align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getVrVentaSinIvaDf2()%></td>
			</tr>
			<tr>
				<td width="172" class="letraDetalle">DESCUENTO</td>
				<td align="right" class="letraDetalle">&nbsp;</td>
			</tr>
			<tr>
				<td width="172" class="letraDetalle">RETENCION</td>
				<td align="right" class="letraDetalle">&nbsp;</td>
			</tr>
			<tr>
				<td width="172" class="letraDetalle">SUB-TOTAL</td>
				<td align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getVrVentaSinIvaDf2()%></td>
			</tr>
			<tr>
				<td width="172" class="letraDetalle">IMPTO. DE VENTA</font></b></td>
				<td align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getVrIvaDf2()%></td>
			</tr>
			<tr>
				<td width="172" class="letraDetalle">TOTAL</td>
				<td align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getVrVentaConIvaDf2()%></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

       <table border="1" width="100%" id="table5">
		<tr>
			<td><input type="submit" value="Regresar" name="accionContenedor"></td>
			<td><input type="submit" value="ConfirmarCancelar" name="accionContenedor"></td>
			<td>&nbsp;</td>
		</tr>
       </table>

       <font face="Verdana">

       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmCanClienteAutorizaPedidoUnCOVariosPedidos.jsp" style="font-weight: 700">
       <input type="hidden" name="idLog" value="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>" style="font-weight: 700">
       <input type="hidden" name="idOrden" value="<%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%>" style="font-weight: 700">
   </font>
   </form>
    <font face="Verdana">
  	</b>
  	</font>
  </body>
</html>