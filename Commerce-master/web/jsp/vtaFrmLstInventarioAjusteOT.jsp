<html>


    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaOT" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaSalidaOT" prefix="lsw" %>
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


    <% String xIdTipoCausaRetal = "2";%>

    <head>
        <title>Registra Ajuste O.T.</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstInventarioAjusteOT.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>">
            <input type="hidden" name="xItemPadre" value="<%=fachadaDctoOrdenBean.getItemPadreStr()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaDctoOrdenBean.getFechaOrdenCorta()%>">   
            <input type="hidden" name="xNumeroOrden" value="<%=fachadaDctoOrdenBean.getNumeroOrdenStr()%>">                        
            <input type="hidden" name="xPesoPerdido" value="0">
            <input type="hidden" name="xIdCausa" value="0">
            <input type="hidden" name="xPesoTara" value="0">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">REGISTRA AJUSTE O.T.</td>
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
                    <td width="5%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraTitulo">PESO (KG)</td>                    
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD
                        <br>UNIDADES</td>
                    <td width="5%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="right" class="letraDetalle">
                        <input name="xPesoTerminado" size="10" maxlength="10" value="0">
                    </td>
                    <td width="5%" align="right" class="letraDetalle">
                        <input name="xCantidadTerminada" size="10" maxlength="10" value="0">
                    </td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                    <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>                
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Confirmar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>
