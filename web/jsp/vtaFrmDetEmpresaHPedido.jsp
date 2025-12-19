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

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

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
        <title>Actualizar Pedido</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>


    <form method="POST" action="GralControladorServlet">

        <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmDetEmpresaHPedido.jsp">
        <input type="hidden" name="idLocal" value="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
        <input type="hidden" name="idTipoOrden" value="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
        <input type="hidden" name="idOrden" value="<%=fachadaDctoOrdenBean.getIdOrdenStr()%>">

        <table border="0" width="90%" class="letraTitulo">
            <tr>
                <td align="center" width="9%"><b><font face="Verdana" size="1">
                <img src="./imagenes/Logo_BrilloColor.jpg" width="103" height="88"></font></b></td>
                <td width="73%" colspan="3">
                    <table border="0" width="99%">
                        <tr>
                            <td class="letraDetalle">NOMBRE CLIENTE</td>
                            <td class="letraDetalle"><%=fachadaTerceroBean.getNombreTercero()%></td>
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
                            <td class="letraDetalle"><%=fachadaUsuarioBean.getNombreUsuario()%></td>
                        </tr>
                        <tr>
                            <td class="letraDetalle">ESTADO CLIENTE</td>
                            <td class="letraDetalle"><%=fachadaTerceroBean.getEstado()%></td>
                        </tr>
                    </table>
                </td>
                <td colspan="4">
                    <table border="0" width="100%" id="table2">
                        <tr>
                            <td width="171" align="center" class="letraDetalle">FECHA FACTURA</td>
                            <td height="18" align="center" class="letraDetalle">FACTURA</td>
                        </tr>
                        <tr>
                            <td width="171" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getFechaOrdenCorta()%></td>
                            <td align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getIdDctoDf0()%></td>
                        </tr>
                        <tr>
                            <td width="171" align="center" class="letraDetalle">CEDULA / NIT</td>
                            <td align="center"class="letraDetalle">PLAZO DE PAGO</td>
                        </tr>
                        <tr>
                            <td width="171" align="center" class="letraDetalle"><%=fachadaTerceroBean.getIdClienteDf0()%></td>
                            <td align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getDiasPlazoStr()%></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td width="4%" align="left" class="letraTitulo">RFCIA</td>
                <td width="40%" align="left" class="letraTitulo">DESCRIPCIÓN</td>
                <td width="6%" align="right" class="letraTitulo">CANTIDAD</td>
                <td width="8%" align="right" class="letraTitulo">%DSCTO</td>
                <td width="8%" align="right" class="letraTitulo">V.UNITARIO</td>
                <td width="6%" align="right" class="letraTitulo">SUBTOTAL</td>
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
                    <td width="4%" align="left" class="letraDetalle"><%=strIdReferenciaVar%></td>
                    <td width="40%" align="left" class="letraDetalle"><%=nombrePluVar%></td>
                    <td width="6%" align="right" class="letraDetalle"><%=cantidadVar%></td>
                    <td width="8%" align="right" class="<%=strEstiloLetra%>"><%=porcentajeDescuentoVar%></td>
                    <td width="8%" align="right" class="<%=strEstiloLetra%>"><%=vrVentaUnitarioVar%></td>
                    <td width="6%" align="right" class="<%=strEstiloLetra%>"><%=vrVentaSinIvaVar%></td>
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
                <td colspan="4">
                    <table border="1" width="100%" id="table3">
                        <tr>
                            <td width="172" class="letraDetalle">TOTAL BRUTO</td>
                            <td align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getVrVentaSinIvaDf2()%></td>
                        </tr>
                        <tr>
                            <td width="172" class="letraDetalle">&nbsp;</td>
                            <td align="right" class="letraDetalle">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="172" class="letraDetalle">&nbsp;</td>
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

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">
                    <input type="submit" value="Salir" name="accionContenedor">
                </td>
                <td width="34%" align="center" class="letraTitulo">
                    <input type="submit" value="Listar" name="accionContenedor">
                    </td>
                <td width="33%" class="letraTitulo">&nbsp;</td>
            </tr>
        </table>

    </form>

    </body>
</html>