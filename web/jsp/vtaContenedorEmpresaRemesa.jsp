<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <% String xIdTipoOrdenFactura = "9";
        String xIdBodega = "1";
    %>

    <script type="text/javascript">
        function vrCambio(form) {
            a=eval(form.xVrPago.value)
            b=eval(form.xVrMedio.value)
            c=a-b
            if (a>=b) {
                form.xVrCambio.value=0
                alert("VR.FALTANTE  $ "  + c )
            } else {
                form.xVrCambio.value=c
            }
        }
    </script>

    <%@ taglib uri="/WEB-INF/tlds/listaPrecio" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaCuentaLocalTotal" prefix="lsj" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOrden" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaOrden" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>

    <jsp:useBean id="fachadaAgendaLogVisitaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <head>
        <title>Venta Mostrador</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorEmpresaRemesa.jsp">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">VENTA MOSTRADOR</td>
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

                <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                                    idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle">LISTA PRECIO</td>
                        <td width="34%" align="center" class="letraDetalle">%DSCTO.COMERCIAL</td>
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
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorEmpresaRemesa.jsp&accionContenedor=ModificaDescuento&xIdLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"><%=fachadaDctoOrdenBean.getDescuentoComercialStr()%></a>
                        </td>
                        <td width="33%" align="center" class="letraDetalle"><%=idClienteVar%>
                        <%=nombreTerceroVar%></td>
                    </tr>
                </lista:listaClienteControlAgendaNit>
                <tr>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                    <td width="34%" align="right" class="letraDetalle">#DCTOS</td>
                    <td width="33%" align="right" class="letraDetalle">$SALDO PENDIENTE</td>
                </tr>

                <lsj:listaCuentaLocalTotal idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                           idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                                           idTipoOrdenTag="<%=xIdTipoOrdenFactura%>"
                                           idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>">
                    <tr>
                        <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                        <td width="34%" align="right" class="letraResaltada"><%=numeroDctosVar%></td>
                        <td width="33%" align="right" class="letraResaltada"><%=vrSaldoVar%></td>
                    </tr>
                </lsj:listaCuentaLocalTotal>

                <tr>
                    <td width="33%" align="right" class="letraTitulo">#ARTICULOS</td>
                    <td width="34%" align="right" class="letraTitulo">$VR.BASE</td>
                    <td width="33%" align="right" class="letraTitulo">$VR.TOTAL</td>
                </tr>

                <lsu:liquidaOrden idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                  idLogTag = "<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                  idLocalTag ="<%=fachadaTerceroBean.getIdLocalStr()%>">
                    <tr>
                        <td width="33%" align="right" class="letraDetalle"><%=cantidadArticulosVar%></td>
                        <td width="34%" align="right" class="letraDetalle"><%=vrVentaSinDsctoDf0Var%></td>
                        <td width="33%" align="right" class="letraResaltadaGrande"><%=vrVentaConIvaVar%></td>
                    </tr>

                </lsu:liquidaOrden>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="20%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="30%" align="left"  class="letraTitulo">
                        <input type="text" name="idLinea" id="idLinea" size="30" tabindex="1" maxlength="30">
                        <input type="submit" value="+Productos" name="accionContenedor">
                    </td>
                    <lsu:liquidaOrden idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                      idLogTag = "<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                      idLocalTag ="<%=fachadaTerceroBean.getIdLocalStr()%>">

                        <td width="30%" align="right" class="letraTitulo">
                            VR.RECIBIDO
                            <input type="text" name="xVrMedio" id="xVrMedio" value="<%=vrVentaConIvaSf0Var%>" size="8" tabindex="2" maxlength="8">
                            <input type="hidden" name="xVrPago" id="xVrPago" value="<%=vrVentaConIvaSf0Var%>">
                            <input type="button" value=" CAMBIO = " onClick="vrCambio(this.form)">
                            <input type="button" name="xVrCambio" id="xVrCambio" value="0">
                        </td>
                        <td width="20%" align="left"  class="letraTitulo">
                            <input type="radio" value="Efectivo" name="xMedioPago" CHECKED>Efectivo<br>
                            <input type="radio" value="Otro Medio" name="xMedioPago">Otro Medio
                        </td>
                    </lsu:liquidaOrden>
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
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorEmpresaRemesa.jsp&accionContenedor=retira&xItem=<%=itemVar%>&xIdLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">R</a>
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorEmpresaRemesa.jsp&accionContenedor=modifica&xItem=<%=itemVar%>&xIdLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">M</a>
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
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>

    </body>

</html>