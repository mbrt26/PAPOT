<html>


    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaOT" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaUsuarioOperacion" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaJobCausa" prefix="lsx" %>

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

    <% String xIdTipoCausaRetal = "2";%>

    <head>
        <title>Registra Produccion</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngOTProducto.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenDetalleBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenDetalleBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>">
            <input type="hidden" name="xItemPadre" value="<%=fachadaDctoOrdenBean.getItemPadreStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">REGISTRA PRODUCCION</td>
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
                        <td width="33%" align="center" class="letraResaltadaGrande"><%=estadoVar%></td>
                        <td width="34%" align="center" class="letraResaltadaGrande"><%=idClienteVar%></td>
                        <td width="33%" align="center" class="letraResaltadaGrande"><%=nombreTerceroVar%></td>
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
                    <td width="25%" align="left" class="letraResaltadaGrande">
                        <%=fachadaPluFicha.getReferencia()%>
                    </td>
                    <td width="25%" align="left" class="letraResaltadaGrande">
                        <%=fachadaPluFicha.getReferenciaCliente()%>
                    </td>
                    <td width="25%" align="center" class="letraResaltadaGrande">
                        <%=fachadaDctoOrdenBean.getNumeroOrdenStr()%>
                    </td>
                    <td width="25%" align="center" class="letraResaltadaGrande">
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
                    <td width="5%" align="center" class="letraTitulo">ITEM</td>
                    <td width="5%" align="left" class="letraTitulo">OPERARIO</td>
                    <td width="5%" align="right" class="letraTitulo">PESO(KG)
                        <br>FINALIZADO</td>
                    <td width="5%" align="right" class="letraTitulo">PESO(KG)
                        <br>TARA</td>
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD
                        <br>FINALIZADA</td>
                    <td width="5%" align="right" class="letraTitulo">PESO(KG)
                        <br>RETAL</td>
                    <td width="5%" align="center" class="letraTitulo">FECHA
                                                                  <br>HORA</td>
                </tr>

                <lsu:liquidaOT idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                idLogTag="<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                idOperacionTag="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>"
                itemPadreTag="<%=fachadaDctoOrdenBean.getItemPadreStr()%>">

                    <tr>
                        <td width="5%" align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmIngOTProducto.jsp&accionContenedor=retira&xIdLocal=<%=fachadaDctoOrdenBean.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>&xIdLog=<%=fachadaDctoOrdenBean.getIdLogStr()%>&xItem=<%=itemVar%>&xIdOperacion=<%=fachadaDctoOrdenBean.getIdOperacionStr()%>&xItemPadre=<%=fachadaDctoOrdenBean.getItemPadreStr()%>"><%=itemVar%></a>
                        </td>
                        <td width="5%" align="left" class="letraDetalle"><%=nombreOperarioVar%>                                                                         
                        </td>
                        <td width="5%" align="right" class="letraDetalle"><%=pesoTerminadoVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=pesoTaraVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=pesoPerdidoVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaFinVar%></td>
                    </tr>
                </lsu:liquidaOT>

                <tr>
                    <td width="5%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="5%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraResaltadaTitulo">KG.PED</td>
                    <td width="5%" align="right" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraResaltadaTitulo">CAN.PED</td>
                    <td width="5%" align="right" class="letraResaltadaTitulo">KG.RET</td>
                    <td width="5%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaPluFicha.getPesoPedidoDf1()%>
                    </td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaPluFicha.getCantidadDf0()%>
                    </td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="5%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="5%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraResaltadaTitulo">KG.TER</td>
                    <td width="5%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraResaltadaTitulo">CAN.TER</td>
                    <td width="5%" align="right" class="letraResaltadaTitulo">KG.RET</td>
                    <td width="5%" align="center" class="letraResaltadaTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaDctoOrdenConcluida.getPesoTerminadoDf1()%>
                    </td>
                    <td width="5%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaDctoOrdenConcluida.getPesoTaraDf0()%>
                    </td>
                    <td width="5%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaDctoOrdenConcluida.getCantidadTerminadaDf0()%>
                    </td>
                    <td width="5%" align="right" class="letraResaltadaDetalle">
                        <%=fachadaDctoOrdenConcluida.getPesoPerdidoDf1()%>
                    </td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="5%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="center" class="letraTitulo">OPERARIO/TERCERO</td>
                    <td width="5%" align="right" class="letraTitulo">PESO (KG)
                                                                 <br>FINALIZADO</td>
                    <td width="5%" align="right" class="letraTitulo">PESO (KG)
                                                                 <br>TARA</td>
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD
                                                                 <br>UNIDADES</td>
                    <td width="5%" align="right" class="letraTitulo">PESO (KG)
                                                                 <br>RETAL</td>
                    <td width="5%" align="center" class="letraTitulo">CAUSAL
                                                                  <br>RETAL</td>
                </tr>

                <tr>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="center" class="letraDetalle">
                        <jsp:include page="./comboTerceroOperacion.jsp"/>
                    </td>
                    <td width="5%" align="right" class="letraDetalle">
                        <input name="xPesoTerminado" size="10" maxlength="10" value="0">
                    </td>
                    <td width="5%" align="right" class="letraDetalle">
                        <input name="xPesoTara" size="10" maxlength="10" value="0">
                    </td>
                    <td width="5%" align="right" class="letraDetalle">
                        <input name="xCantidadTerminada" size="10" maxlength="10" value="0">
                    </td>
                    <td width="5%" align="right" class="letraDetalle">
                        <input name="xPesoPerdido" size="10" maxlength="10" value="0">
                    </td>

                    <td width="5%" align="center" class="letraDetalle">
                        <select name="xIdCausa">
                            <lsx:listaJobCausa idTipoCausaTag = "<%=xIdTipoCausaRetal%>">
                                <option value ="<%=idCausaVar%>">
                                    <%=idCausaFormatoVar%>-<%=nombreCausaVar%>
                                </option>
                            </lsx:listaJobCausa>
                        </select>
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>

                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Pendiente" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Confirmar" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>

                </tr>
            </table>

        </form>
    </body>
</html>
