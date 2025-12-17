<html>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaOTEstado.tld" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

    <jsp:useBean id="fachadaDctoOrdenEstado"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenEstado" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <head>
        <title>Pedido Estado</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstOTEstado.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraResaltadaTitulo">PEDIDO ESTADO<br>
                        <%=fachadaDctoOrdenEstado.getNombreEstado().toUpperCase()%>
                    </td>
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
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraResaltadaTitulo">
                        <%=fachadaTerceroBean.getNombreTercero()%>
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>


            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">OT</td>
                    <td width="5%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraTitulo">OC</td>
                    <td width="5%" align="left" class="letraTitulo">NOMBRE TERCERO</td>
                    <td width="5%" align="center" class="letraTitulo">FEC.ENT</td>
                    <td width="20%" align="left" class="letraTitulo">REF.CLIENTE</td>
                    <!--td width="5%" align="right" class="letraTitulo">KG.PED</td-->
                    <!--td width="5%" align="right" class="letraTitulo">KG.TER</td-->
                    <!--td width="5%" align="right" class="letraTitulo">KG.PEN</td-->
                    <% if ((fachadaColaboraReporteDctoBean.getIdEstado() == 20)
                                        || (fachadaColaboraReporteDctoBean.getIdEstado() == 21)) {%>
                    <td width="5%" align="center" class="letraTitulo">CAN.PED</td>
                    <td width="5%" align="center" class="letraTitulo">CAN.TER</td>
                    <td width="5%" align="center" class="letraTitulo">PEN.TER</td>
                    <% }%>
                    <% if ((fachadaColaboraReporteDctoBean.getIdEstado() == 50)) {%>
                    <td width="5%" align="center" class="letraTitulo">CAN.FRA</td>
                    <% }%>
                    <% if ((fachadaColaboraReporteDctoBean.getIdEstado() == 51)
                                        || (fachadaColaboraReporteDctoBean.getIdEstado() == 53)
                                        || (fachadaColaboraReporteDctoBean.getIdEstado() == 54)) {%>
                    <td width="5%" align="center" class="letraTitulo">CAN.PED</td>
                    <td width="5%" align="center" class="letraTitulo">CAN.TER</td>
                    <td width="5%" align="center" class="letraTitulo">PEN.TER</td>
                    <td width="5%" align="center" class="letraTitulo">CAN.FRA</td>
                    <td width="5%" align="center" class="letraTitulo">PEN.FRA</td>
                    <% }%>
                </tr>
                <lst:listaOTEstado idLocalTag = "<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                idTipoOrdenTag = "<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenStr()%>"
                idEstadoTag = "<%=fachadaColaboraReporteDctoBean.getIdEstadoStr()%>"
                idClienteTag = "<%=fachadaColaboraReporteDctoBean.getIdCliente()%>"
                idTipoTerceroTag = "<%=fachadaColaboraReporteDctoBean.getIdTipoTerceroStr()%>">
                    <tr>
                        <td width="5%" align="right" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstOTEstado.jsp&accionContenedor=UnPedido&xIdLocal=<%=idLocalVar%>&xIdTipoOrden=<%=idTipoOrdenVar%>&xIdOrden=<%=idOrdenVar%>&xNumeroOrden=<%=numeroOrdenVar%>&xItem=<%=itemVar%>"><%=numeroOrdenVar%>-<%=itemVar%></a>
                        </td>
                        <td width="5%" align="right" class="letraDetalle">
                            <% if (fachadaColaboraReporteDctoBean.getIdEstado() == 53) {%>
                            <input type="checkbox" name="xIdLocalTipoOrdenOrdenItem" value="<%=idLocalVar%>~<%=idTipoOrdenVar%>~<%=idOrdenVar%>~<%=itemVar%>">
                            <% } else {%>
                            &nbsp;
                            <% }%>
                        </td>
                        <td width="5%" align="right" class="letraDetalle"><%=ordenCompraVar%></td>
                        <td width="5%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaEntregaVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=referenciaClienteVar%></td>
                        <!--td width="5%" align="right" class="letraDetalle"><%=pesoPedidoVar%></td-->
                        <!--td width="5%" align="right" class="letraDetalle"><%=pesoTerminadoVar%></td-->
                        <!--td width="5%" align="right" class="letraDetalle"><%=pesoPendienteVar%></td-->

                        <% if ((fachadaColaboraReporteDctoBean.getIdEstado() == 20)
                                            || (fachadaColaboraReporteDctoBean.getIdEstado() == 21)) {%>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadDf0Var%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadProduccionPendienteVar%></td>
                        <% }%>
                        <% if ((fachadaColaboraReporteDctoBean.getIdEstado() == 50)) {%>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadFacturadaVar%></td>
                        <% }%>
                        <% if ((fachadaColaboraReporteDctoBean.getIdEstado() == 51)
                                            || (fachadaColaboraReporteDctoBean.getIdEstado() == 53)
                                            || (fachadaColaboraReporteDctoBean.getIdEstado() == 54)) {%>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadDf0Var%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadProduccionPendienteVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadFacturadaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadFacturadaPendienteVar%></td>
                        <% }%>
                    </tr>
                </lst:listaOTEstado>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">
                        <% if (fachadaColaboraReporteDctoBean.getIdEstado() == 53) {%>
                        <input type="submit" value="Cumplir" name="accionContenedor">
                        <% } else {%>
                        &nbsp;
                        <% }%>
                    </td>
                    <td width="34%" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>