<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ page import="java.text.DecimalFormat" %>
    <%@ page import="com.solucionesweb.lasayudas.ProcesoCupoDisponible" %>
    <%@ page import="com.solucionesweb.lasayudas.ProcesoValidaPlazo" %>

    <%@ taglib uri="/WEB-INF/tlds/listaPrecio" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaCuentaLocalTotal" prefix="lsj" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOrden" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaOrden" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaUnTerceroLocal" prefix="lista" %>

    <jsp:useBean id="fachadaAgendaLogVisitaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <%
        String xIdTipoOrdenFactura = "9";
        String xIdBodega = "1";

        //
        DecimalFormat df0 = new DecimalFormat("###,###,###,###");

        //
        ProcesoCupoDisponible procesoCupoDisponible = new ProcesoCupoDisponible();

        //
        double xCupoDisponible = procesoCupoDisponible.valida(
                fachadaTerceroBean.getIdLocal(),
                fachadaAgendaLogVisitaBean.getIdLog(),
                fachadaTerceroBean.getIdTipoOrden(),
                new Integer(xIdTipoOrdenFactura).intValue());

        //
        ProcesoValidaPlazo procesoValidaPlazo = new ProcesoValidaPlazo();

        //
        String xExcedePlazo = procesoValidaPlazo.valida(
                fachadaTerceroBean.getIdLocal(),
                fachadaAgendaLogVisitaBean.getIdLog(),
                fachadaTerceroBean.getIdTipoOrden());
    %>


    <head>
        <title>Elaborar Pedido</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest"
                   value="/jsp/vtaContenedorEmpresaElaboraPedido.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">ELABORAR PEDIDO</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraResaltadaTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        &nbsp;
                    </td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>

                <lista:listaUnTerceroLocal idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                           idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                                           idLocalTerceroTag="<%=fachadaTerceroBean.getIdLocalTerceroStr()%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle">LISTA PRECIO</td>
                        <td width="34%" align="center" class="letraDetalle">%DSCTO.PIE FACTURA</td>
                        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                    </tr>

                    <tr>
                        <td width="33%" align="center" class="letraDetalle">
                            <select name=xIdListaPrecio>
                                <lst:listaPrecio idLocalTag = "<%=fachadaTerceroBean.getIdLocalStr()%>"
                                                 idListaPrecioTag = "<%=fachadaTerceroBean.getIdListaPrecioStr()%>">
                                    <option value="<%=idListaPrecioVar%>">
                                        <%=idListaPrecioVar%> - <%=nombreListaVar%>
                                    </option>
                                </lst:listaPrecio>
                            </select>
                        </td>
                        <td width="33%" align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorEmpresaElaboraPedido.jsp&accionContenedor=ModificaDescuento&idLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"><%=fachadaDctoOrdenBean.getDescuentoComercialStr()%></a>
                        </td>
                        <td width="33%" align="center" class="letraResaltada">
                            <%=idClienteVar%> <%=nombreTerceroVar%> <br>
                            <%=nombreEmpresaVar%>  <br>
                            <%=ciudadTerceroVar%>
                        </td>
                    </tr>
                </lista:listaUnTerceroLocal>
                <tr>
                    <td width="33%" align="right" class="letraDetalle">
                    <table border="0" width="90%" id="tablaTitulo">
                        <td width="33%" align="left" class="letraDetalle">PLAZO</td>
                        <td width="33%" align="right" class="letraDetalle">$ DISPONIBLE</td>
                    </table>
                    <td width="34%" align="right" class="letraDetalle">#DCTOS</td>
                    <td width="33%" align="right" class="letraDetalle">$SALDO PENDIENTE</td>
                </tr>
                <tr>
                    <td width="33%" align="right" class="letraResaltadaGrande">
                    <table border="0" width="90%" id="tablaTitulo">
                        <td width="33%" align="left" class="letraResaltadaGrande">
                            <%=xExcedePlazo%>
                        </td>
                        <td width="33%" align="right" class="letraResaltadaGrande">
                            <%=df0.format(xCupoDisponible)%>
                        </td>
                    </table>
                    <td width="34%" align="right" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="33%" align="right" class="letraResaltadaGrande">&nbsp;</td>
                </tr>
                
                <lsj:listaCuentaLocalTotal idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                           idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                                           idTipoOrdenTag="<%=xIdTipoOrdenFactura%>"
                                           idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>">
                    <tr>
                        <td width="33%" align="right" class="letraResaltadaGrande">
                        <table border="0" width="90%" id="tablaTitulo">
                            <td width="33%" align="left" class="letraResaltadaGrande">
                                <%=xExcedePlazo%>
                            </td>
                            <td width="33%" align="right" class="letraResaltadaGrande">
                                <%=df0.format(xCupoDisponible)%>
                            </td>
                        </table>
                        <td width="34%" align="right" class="letraResaltadaGrande"><%=numeroDctosVar%></td>
                        <td width="33%" align="right" class="letraResaltadaGrande"><%=vrSaldoVar%></td>
                    </tr>
                </lsj:listaCuentaLocalTotal>

                <tr>
                    <td width="33%" align="right" class="letraTitulo">#ARTICULOS</td>
                    <td width="34%" align="right" class="letraTitulo">$VR.BASE</td>
                    <td width="33%" align="right" class="letraTitulo">$VR.TOTAL</td>
                </tr>

                <lsu:liquidaOrden idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                  idLogTag = "<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                  idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>">
                    <tr>
                        <td width="33%" align="right" class="letraDetalle"><%=cantidadArticulosVar%></td>
                        <td width="34%" align="right" class="letraDetalle"><%=vrVentaSinDsctoDf0Var%></td>
                        <td width="33%" align="right" class="letraResaltadaGrande"><%=vrVentaConIvaVar%></td>
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
                    <td width="5%" nowrap align="right" class="letraTitulo">CANT</td>
                    <td width="10%" nowrap align="left" class="letraTitulo">MARCA</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">VR.UN</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">%DSCTO</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">EXIST</td>
                    <td width="15%" nowrap align="right" class="letraTitulo">SUBTOTAL</td>
                </tr>
                <lsv:listaOrden idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>"
                                idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                idLogTag="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                idBodegaTag="<%=xIdBodega%>">

                    <tr>
                        <td width="5%"  nowrap align="center" class="<%=letraDetalleInventarioVar%>">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorEmpresaElaboraPedido.jsp&accionContenedor=Retirar&item=<%=itemVar%>&idLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">R</a>
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorEmpresaElaboraPedido.jsp&accionContenedor=ModificarItem&item=<%=itemVar%>&idLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">M</a>
                        </td>
                        <td width="5%" nowrap align="left" class="<%=letraDetalleInventarioVar%>"><%=strIdReferenciaVar%></td>
                        <td width="30%" nowrap align="left" class="<%=letraDetalleInventarioVar%>"><%=nombrePluVar%></td>
                        <td width="5%" nowrap align="right" class="letraResaltadaGrande"><%=cantidadVar%></td>
                        <td width="10%" nowrap align="left" class="<%=letraDetalleInventarioVar%>"><%=nombreMarcaVar%></td>
                        <td width="5%" nowrap align="right" class="<%=letraDetalleInventarioVar%>"><%=vrVentaUnitarioVar%></td>
                        <td width="5%" nowrap align="right" class="<%=letraDetalleInventarioVar%>"><%=porcentajeDsctoVar%></td>
                        <td width="5%" nowrap align="right" class="<%=letraDetalleInventarioVar%>"><%=existenciaVar%></td>
                        <td width="15%" nowrap align="right" class="letraResaltadaGrande"><%=vrVentaConIvaVar%></td>
                    </tr>

                </lsv:listaOrden>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Finalizar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Suspender" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Cotizar" name="accionContenedor">
                    </td>
                </tr>
            </table>
        </form>

    </body>

</html>