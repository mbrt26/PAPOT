<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>
    <%@ taglib uri="/WEB-INF/tlds/listaUnPedido" prefix="luo" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacionFicha" prefix="lsb" %>
    <%@ taglib uri="/WEB-INF/tlds/listaEscala" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaPluCategoriaOpcion" prefix="lsa" %>
    <%@ taglib uri="/WEB-INF/tlds/listaFichaOperacion" prefix="lso" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacionSiguiente" prefix="lsx" %>
    <%@ taglib uri="/WEB-INF/tlds/listaUnaOperacion" prefix="lsv" %>

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />
    <jsp:useBean id="fachadaPluBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluBean" />

    <%
        String xIdTipoTercero = "1";
        String xIdLocal = "1";
        String xIdBodega = "1";
        String xIdLineaMP = "2";
        String xIdCategoriaPigmento = "2";
        String xIdCategoriaOtraRef = "12";
    %>

    <head>
        <title>Crear Referencia</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">

            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModFichaTecnica.jsp">
            <input type="hidden" name="xIdCliente" value="<%=fachadaPluFicha.getIdCliente()%>">
            <input type="hidden" name="xIdFicha" value="<%=fachadaPluFicha.getIdFichaStr()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaPluFicha.getIdOperacionStr()%>">
            <input type="hidden" name="xCantidadPedido" value="<%=fachadaPluFicha.getCantidadStr()%>">
            <input type="hidden" name="xNumeroOrden" value="<%=fachadaPluFicha.getNumeroOrdenStr()%>">
            <input type="hidden" name="xItem" value="<%=fachadaPluFicha.getItemStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICA FICHA TECNICA</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraResaltadaTituloGrande">
                        <jsp:include page="./comboUnaOperacion.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
                <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaPluFicha.getIdCliente()%>"
                idTipoTerceroTag="<%=xIdTipoTercero%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                        <td width="34%" align="center" class="letraDetalle">REF.CLIENTE<br>
                            #PEDIDO<br>
                            #FICHA<br>
                        </td>
                        <td width="33%" align="center" class="letraDetalle">MATERIAL<br>
                            REFERENCIA</td>
                    </tr>

                    <tr>
                        <td width="33%" align="center" class="letraResaltadaGrande">
                            <%=idClienteVar%>     <br>
                            <%=nombreTerceroVar%> <br>
                            <%=ciudadTerceroVar%>
                        </td>
                        <td width="34%" align="center" class="letraResaltadaGrande">
                            <%=fachadaPluFicha.getReferenciaCliente()%><br>
                            PEDIDO#&nbsp;<%=fachadaPluFicha.getNumeroOrdenStr()%><br>
                            FICHA#&nbsp;<%=fachadaPluFicha.getIdFichaStr()%></td>
                        <td width="33%" align="center" class="letraResaltadaGrande">
                            <%=fachadaPluBean.getNombrePlu()%><br>
                            <%=fachadaPluFicha.getReferencia()%></td>
                    </tr>
                </lst:listaClienteControlAgendaNit>
            </table>

            <table border="0" cellpadding="0" cellspacing="0" width="800" height="600">
                <tr>
                    <td valign="top" width="400">
                        <table border="0" width="90%" id="tablaTitulo">
                            <lso:listaFichaOperacion idFichaTag="<%=fachadaPluFicha.getIdFichaStr()%>">
                                <tr>
                                    <td width="10%" align="left" class="letraResaltadaTituloGrande"><%=nombreOperacionVar%></td>
                                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                                    <td width="10%" align="left" class="letraTitulo">
                                        <% if (idOperacionVar.compareTo("1") != 0) {%>
                                        <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmModFichaTecnica.jsp&accionContenedor=cambia&xIdCliente=<%=fachadaPluFicha.getIdCliente()%>&xIdOperacion=<%=idOperacionVar%>&xIdFicha=<%=fachadaPluFicha.getIdFichaStr()%>&xItem=<%=fachadaPluFicha.getItemStr()%>&xNumeroOrden=<%=fachadaPluFicha.getNumeroOrdenStr()%>">MODIFICAR</a>
                                        <% } else {%>
                                        &nbsp;
                                        <% }%>
                                    </td>
                                    <td width="20%" align="right" class="letraTitulo">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td width="10%" align="left" class="letraTitulo">PARAMETRO</td>
                                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                                    <td width="10%" align="left" class="letraTitulo">VALOR</td>
                                    <td width="20%" align="right" class="letraTitulo">&nbsp;</td>
                                </tr>
                                <lsb:listaOperacionFicha idClienteTag="<%=fachadaPluFicha.getIdCliente()%>"
                                idFichaTag="<%=fachadaPluFicha.getIdFichaStr()%>"
                                idOperacionTag="<%=idOperacionVar%>">

                                    <tr>
                                        <td width="10%" align="left" class="letraDetalle"><%=nombreEscalaVar%></td>
                                        <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
                                        <td width="10%" align="left" class="letraDetalle">
                                            <% if (idTipoEscalaVar.compareTo("2") == 0) {%>
                                            <select disabled name=<%=idEscalaIndexVar%>>
                                                <lsu:listaEscala idEscalaTag="<%=idEscalaVar%>"
                                                itemTag="<%=vrEscalaVar%>">
                                                    <option value="<%=itemVar%>">
                                                        <%=nombreItemVar%>
                                                    </option>
                                                </lsu:listaEscala>
                                            </select>
                                            <% }%>

                                            <% if (idTipoEscalaVar.compareTo("5") == 0) {%>
                                            <select disabled name=<%=idEscalaIndexVar%>>
                                                <lsu:listaEscala idEscalaTag="<%=idEscalaVar%>"
                                                itemTag="<%=vrEscalaVar%>">
                                                    <option value="<%=itemVar%>">
                                                        <%=nombreItemVar%>
                                                    </option>
                                                </lsu:listaEscala>
                                            </select>
                                            PANTONE <input name="<%=idTextoEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=textoEscalaVar%>">
                                            <% }%>

                                            <% if (idTipoEscalaVar.compareTo("6") == 0) {%>
                                            <select disabled name=<%=idEscalaIndexVar%>>
                                                <lsa:listaPluCategoriaOpcion idLineaTag="<%=xIdLineaMP%>"
                                                idCategoriaTag="<%=xIdCategoriaPigmento%>"
                                                idPluTag="<%=vrEscalaVar%>">
                                                    <option value="<%=idPluVar%>">
                                                        <%=nombrePluVar%>
                                                    </option>
                                                </lsa:listaPluCategoriaOpcion>
                                            </select>
                                            KG <input name="<%=idTextoEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=textoEscalaVar%>">
                                            <% }%>

                                            <% if (idTipoEscalaVar.compareTo("7") == 0) {%>
                                            <select disabled name=<%=idEscalaIndexVar%>>
                                                <lsa:listaPluCategoriaOpcion idLineaTag="<%=xIdLineaMP%>"
                                                idCategoriaTag="<%=xIdCategoriaOtraRef%>"
                                                idPluTag="<%=vrEscalaVar%>">
                                                    <option value="<%=idPluVar%>">
                                                        <%=nombrePluVar%>
                                                    </option>
                                                </lsa:listaPluCategoriaOpcion>
                                            </select>
                                            KG <input name="<%=idTextoEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=textoEscalaVar%>">
                                            <% }%>
                                            
                                            <% if (idTipoEscalaVar.compareTo("8") == 0) {%>
                                            <select disabled name=<%=idEscalaIndexVar%>>
                                                <lsa:listaPluCategoriaOpcion idLineaTag="<%=xIdLineaMP%>"
                                                idCategoriaTag="<%=xIdCategoriaOtraRef%>"
                                                idPluTag="<%=vrEscalaVar%>">
                                                    <option value="<%=idPluVar%>">
                                                        <%=nombrePluVar%>
                                                    </option>
                                                </lsa:listaPluCategoriaOpcion>
                                            </select>
                                            <input hidden name="<%=idTextoEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=0%>">
                                            <% }%>

                                            <% if (idTipoEscalaVar.compareTo("3") == 0) {%>
                                            <textarea name="<%=idEscalaIndexVar%>" readonly rows="4" cols="50"><%=textoEscalaVar%></textarea>
                                            <% }%>

                                            <% if (idTipoEscalaVar.compareTo("1") == 0) {%>
                                            <input name="<%=idEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=vrEscalaVar%>">
                                            <% }%>

                                            <% if (idTipoEscalaVar.compareTo("4") == 0) {%>
                                            <input name="<%=idEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=vrEscalaVar%>">
                                            REF <input name="<%=idTextoEscalaIndexVar%>" readonly size="14" maxlength="14" value="<%=textoEscalaVar%>">
                                            <% }%>
                                        </td>
                                        <td width="20%" align="right" class="letraDetalle">&nbsp;</td>
                                    </tr>
                                </lsb:listaOperacionFicha>
                            </lso:listaFichaOperacion>
                        </table>
                    </td>
                    <td valign="top" height="800">
                        <table border="0" width="90%" id="tablaTitulo">
                            <tr>
                                <td width="25%" align="left" class="letraTitulo">OPERACION</td>
                                <td width="25%" align="left" class="letraTitulo">OP.SIGUIENTE</td>
                                <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                                <td width="25%" align="center" class="letraTitulo"></td>
                            </tr>
                            <lsx:listaOperacionSiguiente idFichaTag="<%=fachadaPluFicha.getIdFichaStr()%>">
                                <tr>
                                    <td width="25%" align="left" class="letraDetalle"><%=nombreOperacionVar%></td>
                                    <td width="25%" align="left" class="letraDetalle">
                                        <lsv:listaUnaOperacion idOperacionTag="<%=idOperacionSiguienteVar%>">
                                            <%=nombreOperacionVar%>
                                        </lsv:listaUnaOperacion>
                                    </td>
                                    <td width="25%" align="center" class="letraDetalle">&nbsp;</td>
                                    <td width="25%" align="center" class="letraDetalle">&nbsp;</td>
                                </tr>
                            </lsx:listaOperacionSiguiente>
                            <tr>
                                <td width="25%" align="left" class="letraTitulo">PARAMETRO</td>
                                <td width="25%" align="right" class="letraTitulo">VALOR</td>
                                <td width="25%" align="left" class="letraTitulo">&nbsp;</td>
                                <td width="25%" align="right" class="letraTitulo">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="25%" align="left" class="letraDetalle">DENSIDAD</td>
                                <td width="25%" align="right" class="letraDetalle">
                                    <%=fachadaPluFicha.getFactorDensidadDf5()%>
                                </td>
                                <td width="25%" align="left" class="letraDetalle">&nbsp;</td>
                                <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="25%" align="left" class="letraDetalle">PESO PEDIDO(KG)</td>
                                <td width="25%" align="right" class="letraDetalle">
                                    <%=fachadaPluFicha.getPesoPedidoDf0()%>
                                </td>
                                <td width="25%" align="left" class="letraDetalle">&nbsp;</td>
                                <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="25%" align="left" class="letraDetalle">CANTIDAD PEDIDO(UN)</td>
                                <td width="25%" align="right" class="letraDetalle">
                                    <%=fachadaPluFicha.getCantidadDf0()%>
                                </td>
                                <td width="25%" align="left" class="letraDetalle">&nbsp;</td>
                                <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="25%" align="left" class="letraDetalle">PESO MILLAR(GR)</td>
                                <td width="25%" align="right" class="letraDetalle">
                                    <%=fachadaPluFicha.getPesoMillarDf2()%>
                                </td>
                                <td width="25%" align="left" class="letraDetalle">&nbsp;</td>
                                <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="25%" align="left" class="letraDetalle">PESO METRO(KG)</td>
                                <td width="25%" align="right" class="letraDetalle">
                                    <%=fachadaPluFicha.getPesoComplementoDf2()%>
                                </td>
                                <td width="25%" align="left" class="letraDetalle">&nbsp;</td>
                                <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="25%" align="left" class="letraDetalle">METRO PEDIDO</td>
                                <td width="25%" align="right" class="letraDetalle">
                                    <%=fachadaPluFicha.getMetroPedidoDf0()%>
                                </td>
                                <td width="25%" align="left" class="letraDetalle">&nbsp;</td>
                                <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="25%" align="left" class="letraDetalle">METRO ROLLO</td>
                                <td width="25%" align="right" class="letraDetalle">
                                    <%=fachadaPluFicha.getMetroRolloDf0()%>
                                </td>
                                <td width="25%" align="left" class="letraDetalle">&nbsp;</td>
                                <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="25%" align="left" class="letraDetalle">PESO ROLLO(KG)</td>
                                <td width="25%" align="right" class="letraDetalle">
                                    <%=fachadaPluFicha.getPesoRolloDf2()%>
                                </td>
                                <td width="25%" align="left" class="letraDetalle">&nbsp;</td>
                                <td width="25%" align="right" class="letraDetalle">&nbsp;</td>
                            </tr>
                        </table>
                        <table border="0" width="90%" id="tablaTitulo">
                            <tr>
                                <td width="50%" align="right" class="letraTitulo">
                                    <input type="submit" value="Imprimir Ficha" name="accionContenedor">
                                </td>
                                <td width="50%" align="left" class="letraTitulo">
                                    <input type="submit" value="Imprimir Pedido" name="accionContenedor">
                                </td>
                                <td width="50%" align="left" class="letraTitulo">
                                    <input type="submit" value="Imp. Arte" name="accionContenedor">
                                </td>
                            </tr>
                        </table>
                    </td>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="right" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="50%" align="left" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
