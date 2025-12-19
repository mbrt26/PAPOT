<html>
    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaOTExterna.tld" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <jsp:useBean id="fachadaDctoOrdenProgreso"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso" />

    <head>
        <title>Detalle Orden Trabajo Externa</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmDetOTExterna.jsp">


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">DETALLE ORDEN TRABAJO EXTERNA</td>
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
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="left" class="letraResaltadaTituloGrande">
                        <table border="0" width="100%">
                            <tr>
                                <td align="left" class="letraResaltadaTitulo">#O.T.</td>
                                <td class="letraResaltadaTitulo"><%=fachadaDctoOrdenProgreso.getNumeroOrdenStr()%></td>
                            </tr>
                            <tr>
                                <td align="left" class="letraResaltadaTitulo">REFERENCIA CLIENTE </td>
                                <td class="letraResaltadaTitulo">
                                    <%=fachadaDctoOrdenProgreso.getReferenciaCliente()%>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" class="letraResaltadaTitulo">REFERENCIA</td>
                                <td class="letraResaltadaTitulo">
                                    <%=fachadaDctoOrdenProgreso.getReferencia()%>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" class="letraResaltadaTitulo">TERCERO EXTERNO</td>
                                <td class="letraResaltadaTitulo">
                                    <%=fachadaDctoOrdenProgreso.getNombreTercero()%>
                                </td>
                            </tr>
                        </table>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="5%" align="left" class="letraTitulo">OPERACION</td>
                    <td width="5%" align="right" class="letraTitulo">O.E.</td>
                    <td width="5%" align="left" class="letraTitulo">TIPO</td>
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.SALDO</td>
                    <td width="5%" align="right" class="letraTitulo">KG</td>
                    <td width="5%" align="right" class="letraTitulo">KG.SALDO</td>
                    <td width="5%" align="center" class="letraTitulo">FECHA</td>
                </tr>
                <lsu:listaOTExterna idLocalTag  = "<%=fachadaDctoOrdenProgreso.getIdLocalStr()%>"
                idTipoOrdenTag = "<%=fachadaDctoOrdenProgreso.getIdTipoOrdenStr()%>"
                numeroOrdenTag = "<%=fachadaDctoOrdenProgreso.getNumeroOrdenStr()%>"
                itemPadreTag  = "<%=fachadaDctoOrdenProgreso.getItemPadreStr()%>">
                    <tr>
                        <td width="5%" align="left" class="letraDetalle"><%=nombreOperacionVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=idControlVar%></td>
                        <td width="5%" align="left" class="letraDetalle"><%=nombreTipoControlVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaSaldoVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=pesoTerminadoVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=pesoTerminadoSaldoVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaInicioVar%></td>
                    </tr>

                </lsu:listaOTExterna>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>

</html>