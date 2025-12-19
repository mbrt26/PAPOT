<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaUsuarioOperacion" prefix="lsv" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />
    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />
    <jsp:useBean id="fachadaDctoOrdenProgreso"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso" />

    <% String xIdTipoCausaRetal = "2";%>

    <head>
        <title>Retira Produccion Externa</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmRetOTExternaSalida.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenBean.getIdLogStr()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>">
            <input type="hidden" name="xItemPadre" value="<%=fachadaDctoOrdenBean.getItemPadreStr()%>">
            <input type="hidden" name="xItem" value="<%=fachadaDctoOrdenProgreso.getItemStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">RETIRA PRODUCCION EXTERNA</td>
                    <td width="33%" class="letraResaltadaTituloGrande">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
                <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaDctoOrdenBean.getIdCliente()%>"
                idTipoTerceroTag="<%=fachadaDctoOrdenBean.getIdTipoTerceroStr()%>">
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
                    <td width="40%" align="left" class="letraTitulo">OBSERVACION</td>
                    <td width="10%" align="right" class="letraTitulo">PESO(KG)
                        <br>FINALIZADO</td>
                    <td width="10%" align="right" class="letraTitulo">CANTIDAD
                        <br>FINALIZADA</td>
                    <td width="40%" align="left" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="40%" align="left" class="letraDetalle">
                        <input type="text" value="" name="xObservacion" size="50" maxlength="50">
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <%=fachadaDctoOrdenProgreso.getPesoTerminadoDf0()%>
                    </td>
                    <td width="10%" align="right" class="letraDetalle">
                        <%=fachadaDctoOrdenProgreso.getCantidadTerminadaDf0()%>
                    </td>
                    <td width="40%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>

                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Confirmar Retiro Salida" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>
