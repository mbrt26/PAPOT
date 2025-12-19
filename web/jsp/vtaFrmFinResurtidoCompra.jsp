<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%
         String strNegocioCredido = "";
         String strNegocioContado = "";
%>

    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoDetalle" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean" />

<jsp:useBean id="fachadaTerceroBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean"/>

    <jsp:useBean id="day" scope="page" class="com.solucionesweb.losbeans.utilidades.Day">
        <jsp:setProperty name="day" property="*"/>
    </jsp:useBean>
    
    <head>
        <title>RESURTIDO COMPRA</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmFinResurtidoCompra.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">Resurtido Compra</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
                <table border="0" width="90%" id="tablaTitulo">

                    <tr>
                        <td width="25%" align="left" class="letraTitulo">PROVEEDOR</td>
                        <td width="25%" align="center" class="letraTitulo">#ORDEN</td>
                        <td width="25%" align="center" class="letraTitulo">FECHA ORDEN</td>
                        <td width="25%" align="right" class="letraTitulo">#ARTICULOS</td>
                        <td width="25%" align="right" class="letraTitulo">VR.COMPRA</td>
                    </tr>

                    <tr>
                        <td width="60%" align="left" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getNombreTercero()%></td>
                        <td width="10%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%></td>
                        <td width="10%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getFechaOrdenCorta()%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getCantidadArticulosStr()%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getVrCostoBaseDf0()%></td>
                    </tr>

                </table>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraDetalle">FECHA FACTURA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xFechaDctoNitCC" value="<%=day.getFechaFormateada()%>" size="10" maxlength="10">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">NUMERO FACTURA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xIdDctoNitCC" size="10" id="xIdDctoNitCC" maxlength="10" value="">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>

                <script type="text/javascript">
                    document.getElementById('xIdDctoNitCC').focus();
                </script>

                <tr>
                    <td width="33%" align="left" class="letraDetalle">VR.BASE</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xVrBase" size="10" maxlength="10" value="0">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">
                        <%=fachadaColaboraDctoOrdenBean.getVrCostoSinIvaDf0()%>
                    </td>
                </tr>
                <tr>
                    <td width="33%" align="left" class="letraDetalle">VR.DESCUENTO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xVrDescuento" size="10" maxlength="10" value="0">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">
                        <%=fachadaColaboraDctoOrdenBean.getVrCostoDescuentoSf0()%>
                    </td>
                </tr>

                <tr>
                    <td width="33%" align="left" class="letraDetalle">VR.IVA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xVrIva" size="10" maxlength="10" value="0">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">
                        <%=fachadaColaboraDctoOrdenBean.getVrCostoIvaDf0()%>
                    </td>
                </tr>

                <tr>
                    <td width="33%" align="left" class="letraDetalle">VR.RTE.FUENTE</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xVrRteFuente" size="10" maxlength="10" value="0">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">
                        <%=fachadaColaboraDctoOrdenBean.getVrCostoRteFuenteDf0()%>
                    </td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">VR. A PAGAR FACTURA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xVrPagarFactura" size="10" maxlength="10" value="">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">
                        <%=fachadaColaboraDctoOrdenBean.getVrPagarFacturaDf0()%>
                    </td>
                </tr>

                <% if (fachadaTerceroBean.getIdFormaPago() > 0) {

            //
            strNegocioCredido = "checked";
            strNegocioContado = "unchecked";
        } else {

            //
            strNegocioCredido = "unchecked";
            strNegocioContado = "checked";

        }%>

                <tr>
                    <td width="33%" align="left" class="letraDetalle">COMPRA CREDITO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="radio" name="xIdTipoNegocio" value="CREDITO" "<%=strNegocioCredido%>">
                           </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    <tr>
                    </tr>
                    <td width="33%" align="left" class="letraDetalle">COMPRA CONTADO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="radio" name="xIdTipoNegocio" value="CONTADO" "<%=strNegocioContado%>">
                           </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Legalizar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>
</html>
