<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaOT" prefix="lsu" %>

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

    <jsp:useBean id="fachadaDctoOrdenConcluida"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso" />


    <head>
        <title>Registra Orden Trabajo</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngOrdenTrabajoActivo.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenDetalleBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenDetalleBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>">
            <input type="hidden" name="xItemPadre" value="<%=fachadaDctoOrdenBean.getItemPadreStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">REGISTRA ORDEN TRABAJO</td>
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
                    <td width="50%" align="center" class="letraTitulo">REFERENCIA</td>
                    <td width="50%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                </tr>
                <tr>
                    <td width="50%" align="center" class="letraDetalle">
                        <%=fachadaDctoOrdenDetalleBean.getIdPluStr()%>
                    </td>
                    <td width="50%" align="left" class="letraDetalle">
                        <%=fachadaDctoOrdenDetalleBean.getNombrePlu()%>
                    </td>
                </tr>
                <tr>
                    <td width="50%" align="center" class="letraTitulo">O.T.</td>
                    <td width="50%" align="left" class="letraTitulo">FICHA</td>
                </tr>
                <tr>
                    <td width="50%" align="center" class="letraDetalle">
                        <%=fachadaDctoOrdenBean.getNumeroOrdenStr()%>
                    </td>
                    <td width="50%" align="left" class="letraDetalle">
                        <%=fachadaDctoOrdenBean.getIdFichaSf0()%>
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
                    <td width="12%" align="center" class="letraTitulo">ITEM</td>
                    <td width="12%" align="center" class="letraTitulo">FECHA/HORA FIN</td>
                    <td width="12%" align="left" class="letraTitulo">OPERARIO</td>
                    <td width="12%" align="right" class="letraTitulo">CAN.FIN</td>
                    <td width="12%" align="right" class="letraTitulo">KG.FIN</td>
                    <td width="12%" align="right" class="letraTitulo">KG.RETAL</td>
                    <td width="12%" align="center" class="letraTitulo">&nbsp;</td>
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

                <lsu:liquidaOT idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                               idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                               idLogTag="<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                               idOperacionTag="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>"
                               itemPadreTag="<%=fachadaDctoOrdenBean.getItemPadreStr()%>">

                    <tr>
                        <td width="12%" align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmIngOrdenTrabajoActivo.jsp&accionContenedor=retira&xIdLocal=<%=fachadaDctoOrdenBean.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>&xIdLog=<%=fachadaDctoOrdenBean.getIdLogStr()%>&xItem=<%=itemVar%>&xIdOperacion=<%=fachadaDctoOrdenBean.getIdOperacionStr()%>&xItemPadre=<%=fachadaDctoOrdenBean.getItemPadreStr()%>"><%=itemVar%></a>
                        </td>
                        <td width="12%" align="center" class="letraDetalle"><%=fechaFinVar%></td>
                        <td width="12%" align="left" class="letraDetalle"><%=nombreOperarioVar%></td>
                        <td width="12%" align="right" class="letraDetalle"><%=cantidadTerminadaVar%></td>
                        <td width="12%" align="right" class="letraDetalle"><%=pesoTerminadoVar%></td>
                        <td width="12%" align="right" class="letraDetalle"><%=pesoPerdidoVar%></td>
                        <!--td width="12%" align="center" class="letraDetalle"><%=idCausaVar%></td-->
                        <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    </tr>
                </lsu:liquidaOT>

                <tr>
                    <td width="12%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="12%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="12%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="12%" align="right" class="letraResaltadaTitulo">CAN.PED</td>
                    <td width="12%" align="right" class="letraResaltadaTitulo">KG.PED</td>
                    <td width="12%" align="right" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="12%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="12%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaPluFicha.getCantidadDf0()%>
                    </td>
                    <td width="12%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaPluFicha.getPesoPedidoDf1()%>
                    </td>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="12%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="12%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="12%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="12%" align="right" class="letraResaltadaTitulo">CAN.TER</td>
                    <td width="12%" align="right" class="letraResaltadaTitulo">KG.TER</td>
                    <td width="12%" align="right" class="letraResaltadaTitulo">KG.RET</td>
                    <td width="12%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="12%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaDctoOrdenConcluida.getCantidadTerminadaDf0()%>
                    </td>
                    <td width="12%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaDctoOrdenConcluida.getPesoTerminadoDf1()%>
                    </td>
                    <td width="12%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaDctoOrdenConcluida.getPesoPerdidoDf1()%>
                    </td>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="12%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="12%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="12%" align="center" class="letraTitulo">OPERARIO/TERCERO</td>
                    <td width="12%" align="right" class="letraTitulo">CAN.FIN</td>
                    <td width="12%" align="right" class="letraTitulo">KG.FIN</td>
                    <td width="12%" align="right" class="letraTitulo">KG.RETAL</td>
                    <td width="12%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="12%" align="center" class="letraDetalle">
                        <jsp:include page="./comboTerceroOperacion.jsp"/>
                    </td>
                    <td width="12%" align="right" class="letraDetalle">
                        <input name="xCantidadTerminada" size="10" maxlength="10" value="0">
                    </td>
                    <td width="12%" align="right" class="letraDetalle">
                        <input name="xPesoTerminado" size="10" maxlength="10" value="0">
                    </td>
                    <td width="12%" align="right" class="letraDetalle">
                        <input name="xPesoPerdido" size="10" maxlength="10" value="0">
                    </td>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;
                        <!--input name="xIdCausa" size="10" maxlength="10" value="0"-->
                    </td>
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
