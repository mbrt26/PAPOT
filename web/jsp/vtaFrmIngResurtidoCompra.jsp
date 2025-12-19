<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoCompra" prefix="lst" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />
    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />
    <head>
        <title>RESURTIDO COMPRA</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngResurtidoCompra.jsp">
            <input type="hidden" name="xDiasHistoria" value="<%=fachadaDctoOrdenBean.getDiasHistoria()%>">
            <input type="hidden" name="xDiasInventario" value="<%=fachadaDctoOrdenBean.getDiasInventario()%>">
            <input type="hidden" name="xIdTercero" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaDctoOrdenBean.getFechaOrden()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">Resurtido Compra</td>
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
                <table border="0" width="90%" id="tablaTitulo">

                    <tr>
                        <td width="25%" align="center" class="letraTitulo">#DIAS HISTORIA</td>
                        <td width="25%" align="center" class="letraTitulo">FECHA CORTE</td>
                        <td width="25%" align="center" class="letraTitulo">#DIAS INVENTARIO</td>
                        <td width="25%" align="center" class="letraTitulo">PROVEEDOR</td>
                    </tr>

                    <tr>
                        <td width="25%" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getDiasHistoriaStr()%></td>
                        <td width="25%" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getFechaOrdenCorta()%></td>
                        <td width="25%" align="center" class="letraDetalle"><%=fachadaDctoOrdenBean.getDiasInventario()%></td>
                        <td width="25%" align="center" class="letraDetalle"><%=fachadaTerceroBean.getNombreTercero()%></td>
                    </tr>

                </table>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">PLU</td>
                    <td width="30%" align="left" class="letraTitulo">ARTICULO</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.PED</td>
                    <td width="5%" align="right" class="letraTitulo">CTO.NEG</td>
                    <td width="5%" align="right" class="letraTitulo">PED.SUG</td>
                    <td width="5%" align="right" class="letraTitulo">COSTO</td>
                    <td width="5%" align="right" class="letraTitulo">I.ACTUAL</td>
                    <td width="5%" align="right" class="letraTitulo">COM.PEN.</td>
                    <td width="5%" align="right" class="letraTitulo">VTA.PEN</td>
                    <td width="5%" align="right" class="letraTitulo">TRA.PEN</td>
                    <td width="5%" align="right" class="letraTitulo">DES.PEN</td>
                    <td width="5%" align="right" class="letraTitulo">I.MAX</td>
                    <td width="5%" align="right" class="letraTitulo">PVD</td>
                </tr>


                <lst:listaResurtidoAll idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                                       idTerceroTag="<%=fachadaDctoOrdenBean.getIdCliente()%>"
                                       idLogTag="<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                                       idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
                    <input type="hidden" name="xIdPlu" value="<%=idPluVar%>">
                    <tr>
                       <td width="5%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        <lst:listaResurtidoPlu idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                                               diasHistoriaTag="<%=fachadaDctoOrdenBean.getDiasHistoriaStr()%>"
                                               diasInventarioTag="<%=fachadaDctoOrdenBean.getDiasInventarioStr()%>"
                                               idTerceroTag="<%=fachadaDctoOrdenBean.getIdCliente()%>"
                                               fechaCorteTag="<%=fachadaDctoOrdenBean.getFechaOrden()%>"
                                               idPluTag="<%=idPluVar%>"
                                               existenciaActualTag="<%=existenciaActualSf0Var%>">
                            <td width="30%" align="left" class="<%=letraEstiloVar%>"><%=nombreCategoriaVar%>
                            <%=nombrePluVar%></td>
                            <td width="5%" align="right" class="<%=letraEstiloVar%>">
                                <input type="text" name="xCantidadPedido" value="<%=cantidadPedidoStrVar%>" size="6" maxlength="6">
                            </td>
                            <td width="5%" align="right" class="<%=letraEstiloVar%>">
                                <input type="text" name="xCostoPedido" value="<%=vrCostoStrVar%>" size="6" maxlength="6">
                            </td>
                            <td width="5%" align="right" class="<%=letraEstiloVar%>"><%=cantidadPedidoSugeridoVar%></td>
                            <td width="5%" align="right" class="letraDetalle"><%=vrCostoVar%></td>
                            <td width="5%" align="right" class="letraDetalle"><%=existenciaActualVar%></td>
                            <td width="5%" align="right" class="letraDetalle"><%=cantidadCompraPendienteVar%></td>
                            <td width="5%" align="right" class="letraDetalle"><%=cantidadVentaPendienteVar%></td>
                            <td width="5%" align="right" class="letraDetalle"><%=cantidadTrasladoPendienteVar%></td>
                            <td width="5%" align="right" class="letraDetalle"><%=cantidadDespachoPendienteVar%></td>
                            <td width="5%" align="right" class="letraDetalle"><%=cantidadInventarioMaximoVar%></td>
                            <td width="5%" align="right" class="letraDetalle"><%=cantidadPvdVar%></td>
                        </lst:listaResurtidoPlu>
                    </lst:listaResurtidoAll>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Guardar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Iniciar" name="accionContenedor">
                        </td>
                </tr>
            </table>

        </form>
    </body>
</html>
</html>