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

    <% String xControlCumplido = "" ; %>

    <head>
        <title>Resurtido Compra</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLegResurtidoCompra.jsp">

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
                    <td width="34%" align="center" class="letraTitulo">RESURTIDO COMPRA</td>
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
                    <td width="25%" align="right" class="letraTitulo">VR.COMPRA</td>
                </tr>

                <tr>
                    <td width="60%" align="left" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getNombreTercero()%></td>
                    <td width="10%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getIdOrdenStr()%></td>
                    <td width="10%" align="center" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getFechaOrdenCorta()%></td>
                    <td width="10%" align="right" class="letraDetalle"><%=fachadaColaboraDctoOrdenBean.getCantidadArticulosStr()%></td>
                    <td width="10%" align="right" class="letraResaltadaGrande"><%=fachadaColaboraDctoOrdenBean.getVrPagarFacturaDf0()%></td>
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
                    <td width="5%" align="right" class="letraTitulo">CAN.REC</td>
                    <td width="5%" align="right" class="letraTitulo">VR.BASE</td>
                    <td width="5%" align="right" class="letraTitulo">%IVA</td>
                    <!--td width="5%" align="right" class="letraTitulo">V.IMPOCO</td-->
                    <td width="5%" align="right" class="letraTitulo">VR.IVA</td>
                    <td width="5%" align="right" class="letraTitulo">CTO.PED</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.PEN</td>
                    <td width="5%" align="right" class="letraTitulo">CTO.NEG</td>
                    <td width="35%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    <td width="5%" align="left" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraTitulo">PLU</td>
                    <!--td width="10%" align="left" class="letraTitulo">MARCA</td-->
                </tr>
                <lst:listaResurtidoDetalle idLocalTag="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>"
                idTipoOrdenTag="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>"
                idLogTag="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>">
                    <input type="hidden" name="xIdPlu" value="<%=idPluVar%>">
                    <input type="hidden" name="xVrImpoconsumoArr" value="0">

                    <% if (  new Double(cantidadPendienteStrVar).doubleValue() <= 0) {

                    xControlCumplido = "readonly"; %>

                    <% } else {  xControlCumplido = ""; }%>
                    <tr>
                        <td width="5%" align="right" class="letraDetalle">
                            <input type="text" name="xCantidad" value="<%=cantidadVar%>" size="6" maxlength="6" <%=xControlCumplido%>>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <input type="text" name="xVrSubtotalArr" value="<%=vrCostoResurtidoSf0Var%>" size="10" maxlength="10" <%=xControlCumplido%>>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <input type="text" name="xPorcentajeIvaArr" value="<%=porcentajeIvaVar%>" size="4" maxlength="4" <%=xControlCumplido%>>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <input type="text" name="xVrIvaResurtidoArr" value="<%=vrIvaResurtidoSf0Var%>" size="10" maxlength="10" <%=xControlCumplido%>>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=vrCostoResurtidoTotalDf0Var%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=cantidadPendienteVar%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=vrCostoNegociadoVar%>
                        </td>
                        <td width="35%" align="left" class="letraDetalle">
                            <%=nombreCategoriaVar%>
                            <%=nombrePluVar%>
                        </td>
                        <td width="5%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLegResurtidoCompra.jsp&accionContenedor=retira&xIdLocal=<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>&xIdLog=<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>&xIdPlu=<%=idPluVar%>">RET</a>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=idPluVar%>
                        </td>
                        <!--td width="10%" align="left" class="letraDetalle">
                            <%=nombreMarcaVar%>
                        </td-->
                    </tr>
                </lst:listaResurtidoDetalle>
                <tr>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <!--td width="5%" align="right" class="letraDetalle">&nbsp;</td-->
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="35%" align="left" class="letraDetalle">
                        <input type="text" name="xIdLinea" id="xIdLinea" size="30" tabindex="1" maxlength="30">
                        <input type="submit" value="+Productos" name="accionContenedor">
                    </td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <!--td width="10%" align="left" class="letraDetalle">&nbsp;</td-->
                </tr>
            </table>
            <script type="text/javascript">
                document.getElementById('xIdLinea').focus();
            </script>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="100%" align="left" class="letraResaltadaTitulo">FACTURA PROVEEDOR</td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="10%" align="center" class="letraTitulo">FECHA<br>RECIBO</td>
                    <td width="10%" align="center" class="letraTitulo">FECHA<br>FACTURA</td>
                    <td width="10%" align="center" class="letraTitulo"># FACTURA</td>
                    <td width="10%" align="right" class="letraTitulo">VR.BASE</td>
                    <td width="10%" align="right" class="letraTitulo">V.IMPOCO</td>
                    <td width="10%" align="right" class="letraTitulo">VR.DSCTO</td>
                    <td width="10%" align="right" class="letraTitulo">VR.IVA</td>
                    <td width="10%" align="right" class="letraTitulo">VR.RFTE</td>
                    <td width="10%" align="right" class="letraTitulo">VR.RCREE</td>                    
                    <td width="10%" align="right" class="letraTitulo">VR.PAGAR</td>
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
                    <td width="10%" align="right" class="letraDetalle">
                        <input type="text" value="<%=fachadaColaboraDctoOrdenBean.getVrCostoSinIvaSf0()%>" name="xVrBase" size="12" maxlength="12">
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <input type="text" value="<%=fachadaColaboraDctoOrdenBean.getVrImpoconsumoSf0()%>" name="xVrImpoconsumo" size="12" maxlength="12">
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <input type="text" value="<%=fachadaColaboraDctoOrdenBean.getVrDescuentoSf0()%>" name="xVrDescuento" size="12" maxlength="12">
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <input type="text" value="<%=fachadaColaboraDctoOrdenBean.getVrCostoIvaSf0()%>" name="xVrIva" size="12" maxlength="12">
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <input type="text" value="<%=fachadaColaboraDctoOrdenBean.getVrCostoRteFuenteSf0()%>" name="xVrRteFuente" size="12" maxlength="12">
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <input type="text" value="<%=fachadaColaboraDctoOrdenBean.getVrCostoRteCreeSf0()%>" name="xVrRteCree" size="12" maxlength="12">
                    </td>                    
                    <td width="10%" align="right" class="letraDetalle">
                        <%=fachadaColaboraDctoOrdenBean.getVrPagarFacturaDf0()%>
                    </td>
                </tr>
                <tr>
                    <td width="10%" align="left" class="letraResaltadaGrande">FACTURA<br>PROVEEDOR</td>
                    <td width="10%" align="center" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="10%" align="center" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraProveedorBean.getVrCostoSinIvaDf0()%>
                    </td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraProveedorBean.getVrImpoconsumoDf0()%>
                    </td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraProveedorBean.getVrDescuentoDf0()%>
                    </td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraProveedorBean.getVrCostoIvaDf0()%>
                    </td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraProveedorBean.getVrCostoRteFuenteDf0()%>
                    </td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraProveedorBean.getVrCostoRteCreeDf0()%>
                    </td>                    
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraProveedorBean.getVrPagarFacturaDf0()%>
                    </td>
                </tr>
                <tr>
                    <td width="10%" align="left" class="letraResaltadaGrande">DIFERENCIA</td>
                    <td width="10%" align="center" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="10%" align="center" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraDctoOrdenBean.getVrDiferenciaVrCostoSinIvaDf0()%>
                    </td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraDctoOrdenBean.getVrDiferenciaVrImpoconsumoDf0()%>
                    </td>
                    <td width="10%" align="right" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraDctoOrdenBean.getVrDiferenciaVrCostoIvaDf0()%>
                    </td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraDctoOrdenBean.getVrDiferenciaVrRteFuenteDf0()%>
                    </td>
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraDctoOrdenBean.getVrDiferenciaVrRteCreeDf0()%>
                    </td>                    
                    <td width="10%" align="right" class="letraResaltadaGrande">
                        <%=fachadaColaboraDctoOrdenBean.getVrDiferenciaDf0()%>
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="17%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="17%" align="center" class="letraTitulo">
                        <input type="submit" value="Elaborar" name="accionContenedor">
                    </td>
                    <td width="17%" align="center" class="letraTitulo">
                        <input type="submit" value="Imprimir" name="accionContenedor">
                    </td>
                    <td width="17%" align="center" class="letraTitulo">
                        <input type="submit" value="Liquidar" name="accionContenedor">
                    </td>
                    <td width="17%" align="center" class="letraTitulo">
                        <input type="submit" value="Legalizar" name="accionContenedor">
                    </td>
                    <td width="15%" align="center" class="letraTitulo">
                        <input type="submit" value="Eliminar" name="accionContenedor">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

