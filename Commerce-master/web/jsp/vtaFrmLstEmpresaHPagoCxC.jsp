<html>
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaHPagoCxC" prefix="lista" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraLogisticaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean" />

    <jsp:useBean id="fachadaAgendaLogVisitaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <head>
        <title>Historico Pagos</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstEmpresaHPagoCxC.jsp">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaColaboraLogisticaBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xFechaInicial" value="<%=fachadaColaboraLogisticaBean.getFechaInicial()%>">
            <input type="hidden" name="xFechaFinal" value="<%=fachadaColaboraLogisticaBean.getFechaFinal()%>">
            <input type="hidden" name="xIdCliente" value="<%=fachadaColaboraLogisticaBean.getIdCliente()%>">
            <input type="hidden" name="xIdLocal" value="<%=fachadaColaboraLogisticaBean.getIdLocalStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">HISTORICO PAGOS</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>


                <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                                  idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle">ESTADO CLIENTE</td>
                        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
                        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                    </tr>
                    <tr>
                        <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
                        <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
                        <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
                    </tr>
                </lst:listaClienteControlAgendaNit>
            </table>

            <table border="0" width="90%" id="tablaTitulo">

                <tr>
                    <td width="10%" align="center" class="letraDetalle">#RECIBO</td>
                    <td width="10%" align="center" class="letraDetalle">FEC.PAGO</td>
                    <td width="10%" align="right" class="letraDetalle">VR.PAGO</td>
                    <td width="10%" align="right" class="letraDetalle">VR,RTEFTE</td>
                    <td width="10%" align="right" class="letraDetalle">VR.DSCTO</td>
                    <td width="10%" align="right" class="letraDetalle">VR.RTEIVA</td>
                    <td width="10%" align="right" class="letraDetalle">VR.RTEICA</td>
                    <td width="10%" align="right" class="letraDetalle">VR.RTECREE</td>                
                    <td width="5%" align="center" class="letraDetalle">#FACTURA</td>
                    <td width="5%" align="center" class="letraDetalle">#FE</td>
                    <td width="10%" align="center" class="letraDetalle">#PLANILLA</td>
                </tr>

                <lista:listaHPagoCxC idLocalTag  = "<%=fachadaColaboraLogisticaBean.getIdLocalStr()%>"
                                     idTipoOrdenTag = "<%=fachadaColaboraLogisticaBean.getIdTipoOrdenStr()%>"
                                     fechaInicialTag = "<%=fachadaColaboraLogisticaBean.getFechaInicial()%>"
                                     fechaFinalTag = "<%=fachadaColaboraLogisticaBean.getFechaFinal()%>"
                                     idClienteTag = "<%=fachadaColaboraLogisticaBean.getIdCliente()%>">

                    <tr>

                 
                        <td width="10%" align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstEmpresaHPagoCxC.jsp&accionContenedor=listaRecibo&xIdLocal=<%=idLocalVar%>&xIdTipoOrden=<%=fachadaColaboraLogisticaBean.getIdTipoOrdenStr()%>&xIndicador=<%=indicadorVar%>&xIdRecibo=<%=idReciboVar%>"><%=idReciboVar%></a>
                        </td>
                        <td width="10%" align="center" class="letraDetalle"><%=fechaPagoVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=vrPagoVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=vrRteFuenteVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=vrDescuentoVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=vrRteIvaVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=vrRteIcaVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><!--%=vrRteCreeVar%--></td>                
                        <td width="5%" align="center" class="letraDetalle"><%=idDctoVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=idDctoDian%></td>
                        <td width="10%" align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstEmpresaHPagoCxC.jsp&accionContenedor=listaPlanilla&xIdLocal=<%=idLocalVar%>&xIdTipoOrden=<%=fachadaColaboraLogisticaBean.getIdTipoOrdenStr()%>&xIdPlanilla=<%=idPlanillaVar%>"><%=idPlanillaVar%></a>
                        </td>
                    </tr>

                </lista:listaHPagoCxC>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Listar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>