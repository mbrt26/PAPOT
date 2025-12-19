<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <% String xIdBodega = "1";%>

    <%@ taglib uri="/WEB-INF/tlds/listaPrecio" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaOrden" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOrden" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteCotizacionArticulos" prefix="listaArticulos" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <head>
        <title>Elaborar Autoconsumo</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngResurtidoAutoconsumo.jsp">
            <input type="hidden" name="xIdTercero" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaDctoOrdenBean.getFechaOrdenCorta()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">ELABORA AUTOCONSUMO</td>
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

                <tr>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="34%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="33%" align="center" class="letraDetalle">
                    <jsp:include page="./comboCentroResurtido.jsp"/></td>
                </tr>

                <tr>
                    <td width="33%" align="right" class="letraTitulo">#ARTICULOS</td>
                    <td width="34%" align="right" class="letraTitulo">$VR.BASE</td>
                    <td width="33%" align="right" class="letraTitulo">$VR.TOTAL</td>
                </tr>

                <lsu:liquidaOrden idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                                  idLogTag = "<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                                  idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">

                    <tr>
                        <td width="33%" align="right" class="letraDetalle"><%=cantidadArticulosVar%></td>
                        <td width="34%" align="right" class="letraDetalle"><%=vrCostoSinIvaVar%></td>
                        <td width="33%" align="right" class="letraDetalle"><%=vrCostoConIvaVar%></td>
                    </tr>

                </lsu:liquidaOrden>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="right" class="letraTitulo">
                        <input type="text" name="idLinea" id="idLinea" size="30" tabindex="1" maxlength="30">
                    </td>
                    <td width="50%" align="left"  class="letraTitulo">
                        <input type="submit" value="+Productos" name="accionContenedor">
                    </td>
                </tr>
            </table>

            <script type="text/javascript">
                document.getElementById('idLinea').focus();
            </script>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" class="letraTitulo">&nbsp;</td>
                    <td width="5%"  align="left" class="letraTitulo">REF</td>
                    <td width="30%" nowrap align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    <td width="10%" nowrap align="left" class="letraTitulo">MARCA</td>
                    <td width="5%" nowrap align="center" class="letraTitulo">UD</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">%DSCTO</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">CANT</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">EXIST</td>
                    <td width="10%" nowrap align="right" class="letraTitulo">SUBTOTAL</td>
                </tr>

                <lsv:listaOrden idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                                idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                                idLogTag="<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                                idBodegaTag="<%=xIdBodega%>">

                    <tr>
                        <td width="5%"  nowrap align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmIngResurtidoAutoconsumo.jsp&accionContenedor=retira&xItem=<%=itemVar%>&xIdLog=<%=fachadaDctoOrdenBean.getIdLogStr()%>&xIdTercero=<%=fachadaDctoOrdenBean.getIdCliente()%>&xFechaCorte=<%=fachadaDctoOrdenBean.getFechaOrdenCorta()%>">R</a>
                        </td>
                        <td width="5%" nowrap align="left" class="<%=letraDetalleInventarioVar%>"><%=strIdReferenciaVar%></td>
                        <td width="30%" nowrap align="left" class="<%=letraDetalleInventarioVar%>"><%=nombrePluVar%></td>
                        <td width="10%" nowrap align="left" class="<%=letraDetalleInventarioVar%>"><%=nombreMarcaVar%></td>
                        <td width="5%" nowrap align="center" class="letraDetalleDespacho"><%=factorDespachoVar%></td>
                        <td width="5%" nowrap align="right" class="<%=letraDetalleInventarioVar%>"><%=porcentajeDsctoVar%></td>
                        <td width="5%" nowrap align="right" class="<%=letraDetalleInventarioVar%>"><%=cantidadVar%></td>
                        <td width="5%" nowrap align="right" class="<%=letraDetalleInventarioVar%>"><%=existenciaVar%></td>
                        <td width="15%" nowrap align="right" class="<%=letraDetalleInventarioVar%>"><%=vrCostoConIvaVar%></td>
                    </tr>

                </lsv:listaOrden>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Legalizar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>

    </body>

</html>