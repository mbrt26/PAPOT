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

    <head>
        <title>Resurtido Compra</title>
    </head
    <body>


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
                    <td width="5%" align="right" class="letraTitulo">PLU</td>
                    <td width="35%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    <td width="10%" align="left" class="letraTitulo">MARCA</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.PED</td>
                    <td width="10%" align="right" class="letraTitulo">CTO.PED</td>
                    <td width="5%" align="right" class="letraTitulo">CTO.NEG</td>
                    <td width="5%" align="right" class="letraTitulo">%IVA</td>
                </tr>
                <lst:listaResurtidoDetalle idLocalTag="<%=fachadaColaboraDctoOrdenBean.getIdLocalStr()%>"
                                           idTipoOrdenTag="<%=fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr()%>"
                                           idLogTag="<%=fachadaColaboraDctoOrdenBean.getIdLogStr()%>">
                    <tr>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=idPluVar%>
                        </td>
                        <td width="35%" align="left" class="letraDetalle">
                            <%=nombreCategoriaVar%>
                            <%=nombrePluVar%>
                        </td>
                        <td width="10%" align="left" class="letraDetalle">
                            <%=nombreMarcaVar%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=cantidadPedidoVar%>
                        </td>
                        <td width="10%" align="right" class="letraDetalle">
                            <%=vrCostoResurtidoDf0Var%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <%=vrCostoNegociadoVar%>
                        </td>

                        <td width="5%" align="right" class="letraDetalle">
                            <%=porcentajeIvaVar%>
                        </td>
                    </tr>
                </lst:listaResurtidoDetalle>
            </table>
    </body>
</html>

