<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaOTCosto" prefix="lsu" %>

    <jsp:useBean id="fachadaDctoOrdenDetalleBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />

    <jsp:useBean id="fachadaAgendaLogVisitaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />

    <head>
        <title>Registra Costo</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngOTCosto.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenDetalleBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenDetalleBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>">
            <input type="hidden" name="xItemPadre" value="<%=fachadaDctoOrdenBean.getItemPadreStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">REGISTRA COSTO</td>
                    <td width="33%" class="letraResaltadaTituloGrande">&nbsp;</td>
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
                    <td width="25%" align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="25%" align="left" class="letraTitulo">REFERENCIA CLIENTE</td>
                    <td width="25%" align="center" class="letraTitulo">O.T.</td>
                    <td width="25%" align="center" class="letraTitulo">FICHA</td>
                </tr>
                <tr>
                    <td width="25%" align="left" class="letraDetalle">
                        <%=fachadaPluFicha.getReferencia()%>
                    </td>
                    <td width="25%" align="left" class="letraDetalle">
                        <%=fachadaPluFicha.getReferenciaCliente()%>
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                        <%=fachadaDctoOrdenBean.getNumeroOrdenStr()%>
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                        <%=fachadaPluFicha.getIdFichaSf0()%>
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">OP.ANTERIOR</td>
                    <td width="34%" align="center" class="letraResaltadaTituloGrande">OP.ACTUAL</td>
                    <td width="33%" align="center" class="letraTitulo">OP.SIGUIENTE</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <jsp:include page="./comboOperacionAnteriorActual.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraResaltadaTituloGrande">
                        <jsp:include page="./comboUnaOperacion.jsp"/>
                    </td>
                    <td width="33%" align="center" class="letraTitulo">
                    <jsp:include page="./comboOperacionActualSiguiente.jsp"/>
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">PLU</td>
                    <td width="25%" align="left" class="letraTitulo">NOMBRE PLU</td>
                    <td width="25%" align="right" class="letraTitulo">CANTIDAD</td>
                    <td width="25%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>

                <!--tr>
                    <td width="20%" align="left" class="letraDetalle">TURNO</td>
                    <td width="20%" align="left" class="letraDetalle">&nbsp;</td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr-->

                <!--tr>
                    <td width="20%" align="left" class="letraDetalle">#ROLLO</td>
                    <td width="20%" align="left" class="letraDetalle">&nbsp;</td>
                    <td width="60%" align="left" class="letraDetalle">&nbsp;</td>
                </tr-->

                <lsu:liquidaOTCosto idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                               idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                               idOrdenTag="<%=fachadaDctoOrdenBean.getIdOrdenStr()%>"
                               idOperacionTag="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>"
                               itemPadreTag="<%=fachadaDctoOrdenBean.getItemPadreStr()%>">

                    <tr>
                        <td width="25%" align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmIngOTCosto.jsp&accionContenedor=retira&xIdLocal=<%=idLocalVar%>&xIdTipoOrden=<%=idTipoOrdenVar%>&xIdLog=<%=idLogVar%>&xIdOrden=<%=idOrdenVar%>&xIdPlu=<%=idPluVar%>&xIdOperacion=<%=fachadaDctoOrdenBean.getIdOperacionStr()%>&xPesoTerminado=<%=cantidadVar%>&xItemPadre=<%=fachadaDctoOrdenBean.getItemPadreStr()%>"><%=idPluVar%></a>
                        </td>
                        <td width="25%" align="left" class="letraDetalle"><%=nombrePluVar%></td>
                        <td width="25%" align="right" class="letraDetalle"><%=cantidadVar%></td>
                        <td width="25%" align="right" class="letraDetalle"><%=cantidadVar%></td>
                    </tr>
                </lsu:liquidaOTCosto>

                <tr>
                    <td width="25%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="25%" align="center" class="letraDetalle">
                        <jsp:include page="./comboPluTipo.jsp"/>
                    </td>
                    <td width="25%" align="right" class="letraDetalle">
                        <input name="xPesoTerminado" size="10" maxlength="10" value="0">
                    </td>
                    <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="right" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="50%" align="left" class="letraTitulo">
                        <input type="submit" value="Confirmar" name="accionContenedor">
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
