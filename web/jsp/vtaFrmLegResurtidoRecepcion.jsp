<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoDetalle" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean" />

    <jsp:useBean id="fachadaColaboraProveedorBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean" />

    <% String xControlCumplido = "";%>

    <head>
        <title>Resurtido Recepcion</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLegResurtidoRecepcion.jsp">

            <input type="hidden" name="xDiasHistoria" value="<%=fachadaColaboraDctoOrdenBean.getDiasHistoria()%>">
            <input type="hidden" name="xDiasInventario" value="<%=fachadaColaboraDctoOrdenBean.getDiasInventario()%>">
            <input type="hidden" name="xIdTercero" value="<%=fachadaColaboraDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaColaboraDctoOrdenBean.getFechaOrden()%>">
            <input type="hidden" name="xIdLocal" value="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdOrden" value="<%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">RESURTIDO RECEPCION</td>
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
            </table>
            <table border="0" width="90%" id="tablaTitulo">

                <tr>
                    <td width="25%" align="left" class="letraTitulo">PROVEEDOR</td>
                    <td width="25%" align="center" class="letraTitulo">#ORDEN</td>
                    <td width="25%" align="center" class="letraTitulo">FECHA ORDEN</td>
                    <td width="25%" align="right" class="letraTitulo">#ARTICULOS</td>
                    <td width="25%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="60%" align="left" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getNombreTercero()%></td>
                    <td width="10%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%></td>
                    <td width="10%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getFechaOrdenCorta()%></td>
                    <td width="10%" align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getCantidadArticulosStr()%></td>
                    <td width="10%" align="right" class="letraResaltadaGrande">&nbsp;</td>
                </tr>

            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="100%" align="left" class="letraResaltadaTitulo">OBSERVACIONES
                        <br><%=fachadaColaboraDctoOrdenBean.getObservacionMayuscula()%>
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD
                        <br>RECIBIDA</td>
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD
                        <br>PENDIENTE</td>
                    <td width="5%" align="right" class="letraTitulo">COSTO
                        <br>NEGOCIADO</td>
                    <td width="40%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    <td width="5%" align="right" class="letraTitulo">PLU</td>
                    <td width="5%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="35%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>
                <lst:listaResurtidoDetalle idLocalTag="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>"
                idTipoOrdenTag="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>"
                idLogTag="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>">
                    <input type="hidden" name="xIdPlu" value="<%=idPluVar%>">
                    <input type="hidden" name="xVrImpoconsumoArr" value="0">
                    <input type="hidden" name="xVrSubtotalArr" value="0">
                    <input type="hidden" name="xPorcentajeIvaArr" value="0">
                    <input type="hidden" name="xVrIvaResurtidoArr" value="0">
                    <input type="hidden" name="xVrImpoconsumoArr" value="0">

                    <% if (new Double(cantidadPendienteStrVar).doubleValue() <= 0) {

                                    xControlCumplido = "readonly";%>

                    <% } else {
                                    xControlCumplido = "";
                                }%>
                    <tr>
                        <td width="5%" align="right" class="letraDetalle">
                            <input type="text" name="xCantidad" value="<%=cantidadVar%>" size="6" maxlength="6" <%=xControlCumplido%>>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=cantidadPendienteVar%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=vrCostoNegociadoVar%>
                        </td>
                        <td width="40%" align="left" class="letraDetalle">
                            <%=nombreCategoriaVar%>
                            <%=nombrePluVar%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=idPluVar%>
                        </td>
                        <td width="5%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLegResurtidoRecepcion.jsp&accionContenedor=retira&xIdLocal=<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>&xIdLog=<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>&xIdPlu=<%=idPluVar%>">RET</a>
                        </td>
                        <td width="35%" align="right" class="letraDetalle">&nbsp;</td>
                    </tr>
                </lst:listaResurtidoDetalle>
                <tr>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="40%" align="left" class="letraDetalle">
                        <input type="text" name="xIdLinea" id="xIdLinea" size="30" tabindex="1" maxlength="30">
                        <input type="submit" value="+Productos" name="accionContenedor">
                    </td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="35%" align="right" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>
            <script type="text/javascript">
                document.getElementById('xIdLinea').focus();
            </script>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="100%" align="left" class="letraResaltadaTitulo">FACTURA
                        <br>REMISON</td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="10%" align="center" class="letraTitulo">FECHA
                        <br>RECIBO</td>
                    <td width="10%" align="center" class="letraTitulo">FECHA FACTURA
                        <br>REMISION</td>
                    <td width="10%" align="center" class="letraTitulo">#FACTURA
                        <br>#REMISION</td>
                    <td width="70%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="10%" align="center" class="letraDetalle">
                        <input type="text" value="<%=fachadaColaboraDctoOrdenBean.getFechaOrdenCorta()%>" name="xFechaDcto" size="10" maxlength="10">
                    </td>
                    <td width="10%" align="center" class="letraDetalle">
                        <input type="text" value="<%=fachadaColaboraDctoOrdenBean.getFechaEntregaCorta()%>" name="xFechaDctoNitCC" size="10" maxlength="10">
                    </td>
                    <td width="10%" align="center" class="letraDetalle">
                        <input type="text" value="<%=fachadaColaboraDctoOrdenBean.getIdDctoNitCC()%>" name="xIdDctoNitCC" size="12" maxlength="12">
                    </td>
                    <td width="70%" align="right" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Liquidar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Legalizar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Eliminar" name="accionContenedor">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

