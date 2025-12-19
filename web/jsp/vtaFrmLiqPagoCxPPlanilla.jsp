<html>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaPagoPlanilla" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaTotalPagoPlanilla" prefix="lsu" %>

    <jsp:useBean id="fachadaUsuarioBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaUsuarioBean" />

    <jsp:useBean id="fachadaPagoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPagoBean" />

    <head>
        <title>Registro Pagos Proveedor</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLiqPagoCxPPlanilla.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaUsuarioBean.getIdLocalUsuarioStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaUsuarioBean.getIdLocalUsuarioStr()%>">
            <input type="hidden" name="xIdVendedor" value="<%=fachadaPagoBean.getIdVendedorDi0()%>">
            <input type="hidden" name="xFechaPago" value="<%=fachadaPagoBean.getFechaPagoCorta()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">REGISTRO PAGOS PROVEEDOR</td>
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
                    <td width="33%" align="center" class="letraDetalle">USUARIO</td>
                    <td width="34%" align="center" class="letraDetalle">ALIAS USUARIO</td>
                    <td width="33%" align="center" class="letraDetalle">FECHA PAGO</td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraDetalle">
                        <%=fachadaUsuarioBean.getNombreUsuario()%>
                    </td>
                    <td width="34%" align="center" class="letraDetalle">
                        <%=fachadaUsuarioBean.getAliasUsuario()%>
                    </td>
                    <td width="33%" align="center" class="letraDetalle"><%=fachadaPagoBean.getFechaPagoCorta()%></td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="30%" align="left" class="letraTitulo">PROVEEDOR</td>
                    <td width="5%" align="center" class="letraTitulo">#DCTO</td>
                    <td width="8%" align="right" class="letraTitulo">SALDO</td>
                    <td width="8%" align="right" class="letraTitulo">V.PAGO</td>
                    <td width="8%" align="right" class="letraTitulo">V.DSCTO</td>
                    <td width="8%" align="right" class="letraTitulo">V.RFTE</td>
                    <td width="8%" align="right" class="letraTitulo">V.RIVA</td>
                    <td width="8%" align="right" class="letraTitulo">V.RICA</td>
                    <td width="8%" align="right" class="letraTitulo">V.RCREE</td>                    
                    <td width="8%" align="right" class="letraTitulo">DFCIA</td>
                    <td width="8%" align="left" class="letraTitulo">MEDIO</td>
                    <td width="1%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>
                <lst:listaPagoPlanilla idLocalTag="<%=fachadaPagoBean.getIdLocalStr()%>"
                                       idTipoOrdenTag="<%=fachadaPagoBean.getIdTipoOrdenStr()%>"
                                       idLogTag= "<%=fachadaPagoBean.getIdLogStr()%>">

                    <tr>
                        <td width="30%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=idDctoNitCCVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrSaldoVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrPagoVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrDescuentoVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrRteFuenteVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrRteIvaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrRteIcaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrRteCreeVar%></td>                              
                        <td width="8%" align="right" class="letraDetalle"><%=vrDiferenciaDf0Var%></td>
                        <td width="8%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLiqPagoCxPPlanilla.jsp&accionContenedor=traeMedio&xIdLocal=<%=fachadaPagoBean.getIdLocalStr()%>&xIdTipoOrden=<%=idTipoOrdenVar%>&xIndicador=<%=indicadorVar%>&xIdDcto=<%=idDctoVar%>&xIdVendedor=<%=fachadaPagoBean.getIdVendedorDi0()%>&xFechaPago=<%=fachadaPagoBean.getFechaPagoCorta()%>"><%=nombreMedioVar%></a>
                        </td>
                        <td width="1%" align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLiqPagoCxPPlanilla.jsp&accionContenedor=retiraDcto&xIdLocal=<%=fachadaPagoBean.getIdLocalStr()%>&xIdTipoOrden=<%=idTipoOrdenVar%>&xIndicador=<%=indicadorVar%>&xIdDcto=<%=idDctoVar%>&xIdVendedor=<%=fachadaPagoBean.getIdVendedorDi0()%>&xFechaPago=<%=fachadaPagoBean.getFechaPagoCorta()%>">R</a>
                        </td>
                    </tr>
                </lst:listaPagoPlanilla>

                <lsu:listaTotalPagoPlanilla idLocalTag="<%=fachadaPagoBean.getIdLocalStr()%>"
                                            idTipoOrdenTag="<%=fachadaPagoBean.getIdTipoOrdenStr()%>"
                                            idLogTag= "<%=fachadaPagoBean.getIdLogStr()%>">
                    <tr>
                        <td width="30%" align="left" class="letraTitulo">TOTALES</td>
                        <td width="5%" align="center" class="letraTitulo">&nbsp;</td>
                        <td width="8%" align="right" class="letraTitulo">&nbsp;</td>
                        <td width="8%" align="right" class="letraTitulo"><%=vrPagoVar%></td>
                        <td width="8%" align="right" class="letraTitulo"><%=vrDescuentoVar%></td>
                        <td width="8%" align="right" class="letraTitulo"><%=vrRteFuenteVar%></td>
                        <td width="8%" align="right" class="letraTitulo"><%=vrRteIvaVar%></td>
                        <td width="8%" align="right" class="letraTitulo"><%=vrRteIcaVar%></td>
                        <td width="8%" align="right" class="letraTitulo"><%=vrRteCreeVar%></td>                             
                        <td width="8%" align="right" class="letraTitulo">&nbsp;</td>
                        <td width="8%" align="left" class="letraTitulo">&nbsp;</td>
                        <td width="1%" align="center" class="letraTitulo">&nbsp;</td>
                    </tr>
                </lsu:listaTotalPagoPlanilla>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Buscar Proveedor" name="accionContenedor">
                        <input type="text" value="" name="xNombreTercero" id="xNombreTercero" value="" size="30">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">	NOTA(max 100)
                        <input type="text" value="" name="xObservacion" id="xObservacion" value="" size="40" maxlength="100">
                        <input type="submit" value="Pagar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                </tr>
            </table>

            <script type="text/javascript">
                document.getElementById('xNombreTercero').focus();
            </script>

        </form>
    </body>
</html>