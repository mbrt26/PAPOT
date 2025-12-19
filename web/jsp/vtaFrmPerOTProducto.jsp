<html>


    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
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

    <% String xIdTipoCausaTiempoPerdido = "1";%>

    <head>
        <title>Registra Tiempo Perdido</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <link type="text/css" href="./styles/jquery-ui-1.8.13.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="./js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-1.8.13.custom.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-timepicker-addon.js"></script>
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmPerOTProducto.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenDetalleBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenDetalleBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>">
            <input type="hidden" name="xItemPadre" value="<%=fachadaDctoOrdenBean.getItemPadreStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">REGISTRA TIEMPO PERDIDO</td>
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
                    <td width="12%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="12%" align="center" class="letraTitulo">OPERARIO/TERCERO</td>
                    <td width="12%" align="center" class="letraTitulo">FECHA/HORA
                           <br>INICIAL</td>
                    <td width="12%" align="center" class="letraTitulo">FECHA/HORA
                           <br>FINAL</td>
                    <td width="12%" align="center" class="letraTitulo">CAUSAL
                           <br>TIEMPO PERDIDO
                    </td>
                </tr>
                <tr>
                    <td width="12%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="12%" align="center" class="letraDetalle">
                        <select name="xIdTercero">
                            <lsv:listaUsuarioOperacion idLocalTag="<%=fachadaDctoOrdenDetalleBean.getIdLocalStr()%>"
                            idOperacionTag="<%=fachadaPluFicha.getIdOperacionStr()%>">
                                <option value ="<%=idUsuarioVar%>">
                                    <%=nombreUsuarioVar%>
                                </option>
                            </lsv:listaUsuarioOperacion>
                        </select>
                    </td>
                    <td width="12%" align="center" class="letraDetalle">
                        <input name="xFechaHoraInicio"  id="xFechaHoraInicio" readonly="readonly">
                    </td>
                    <td width="12%" align="center" class="letraDetalle">
                        <input name="xFechaHoraFin"  id="xFechaHoraFin" readonly="readonly">
                    </td>
                    <td width="12%" align="center" class="letraDetalle">
                        <select name="xIdCausa">
                            <lsx:listaJobCausa idTipoCausaTag = "<%=xIdTipoCausaTiempoPerdido%>">
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
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Confirmar Tiempo Perdido" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>
<script type="text/javascript">
    $(function(){
        $('#xFechaHoraInicio').datetimepicker({
            showSecond: true,
            timeFormat: 'hh:mm:ss'
        });
        $('#xFechaHoraFin').datetimepicker({
            showSecond: true,
            timeFormat: 'hh:mm:ss'
        });
    });
</script>