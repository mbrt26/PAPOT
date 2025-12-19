<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>
    <%@ taglib uri="/WEB-INF/tlds/listaCuentaLocalDetalle" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaCuentaLocalTotal" prefix="lst" %>

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <head>
        <title>CxP Consolidada x Nit</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorPagoCxP.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">PAGO CXP</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>

                <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                                idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">

                        <td width="33%" align="center" class="letraDetalle">ESTADO CLIENTE</td>
                        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
                        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                    </tr>

                    <tr>
                        <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
                        <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
                        <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
                    </tr>
                </lista:listaClienteControlAgendaNit>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="10%" align="center" class="letraTitulo">#DCTO</td>
                    <td width="10%" align="right" class="letraTitulo">SALDO</td>
                    <td width="10%" align="right" class="letraTitulo">VR.RECIBIDO</td>
                    <td width="10%" align="right" class="letraTitulo">VR.DSCTO</td>
                    <td width="10%" align="right" class="letraTitulo">VR.RTEFTE</td>
                    <td width="10%" align="right" class="letraTitulo">VR.RTEIVA</td>
                    <td width="10%" align="right" class="letraTitulo">DIFERENCIA</td>
                    <td width="10%" align="center" class="letraTitulo">FECHA VCTO</td>
                    <td width="10%" align="right" class="letraTitulo">DIAS</td>
                </tr>
                <lsu:listaCuentaLocalDetalle idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                         idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                                           idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                               idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>">

                    <input type="hidden" name="xIdDcto" value=<%=idDctoVar%>>
                    <input type="hidden" name="xIdDctoNitCC" value=<%=idDctoNitCCVar%>>
                    <input type="hidden" name="xVrSaldo" value=<%=vrSaldoSFVar%>>
                    <input type="hidden" name="xFechaVencimiento" value=<%=fechaVencimientoVar%>>
                    <input type="hidden" name="xDiasMora" value=<%=diasMoraVar%>>
                    <input type="hidden" name="xIndicador" value=<%=indicadorVar%>>
                    <input type="hidden" name="xIdTipoOrden" value=<%=idTipoOrdenVar%>>
                    <input type="hidden" name="xIdLocal" value=<%=idLocalVar%>>
                <tr>
                    <td width="10%" align="center" class="letraTitulo"><%=idDctoNitCCVar%></td>
                    <td width="10%" align="right" class="letraTitulo"><%=vrSaldoVar%></td>
                    <td width="10%" align="right" class="letraTitulo">
                        <input type="text" value="0" name="xVrPago" size="10" tabindex="1">
                        </td>
                    <td width="10%" align="right" class="letraTitulo">
                        <input type="text" value="0" name="xVrDescuento" size="10">
                        </td>
                    <td width="10%" align="right" class="letraTitulo">
                        <input type="text" value="0" name="xVrRteFuente" size="10">
                    </td>
                    <td width="10%" align="right" class="letraTitulo">
                        <input type="text" value="0" name="xVrRteIva" size="10">
                    </td>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="10%" align="center" class="letraTitulo"><%=fechaVencimientoVar%></td>
                    <td width="10%" align="right" class="letraTitulo"><%=diasMoraVar%></td>
                </tr>

                </lsu:listaCuentaLocalDetalle>

                <lst:listaCuentaLocalTotal idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                       idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                                         idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>"
                                             idLocalTag="<%=fachadaTerceroBean.getIdLocalStr()%>">

                <tr>
                    <td width="10%" align="center" class="letraTitulo">TOTAL</td>
                    <td width="10%" align="center" class="letraTitulo"><%=numeroDctosVar%></td>
                    <td width="10%" align="right" class="letraTitulo"><%=vrSaldoVar%></td>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>

                </lst:listaCuentaLocalTotal>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                       <input type="submit" value="Liquidar" name="accionContenedor">
                        </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
</html>