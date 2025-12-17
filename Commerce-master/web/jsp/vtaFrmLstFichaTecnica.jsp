<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaUnPedido" prefix="luo" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOrdenItem" prefix="lsv" %>

    <%
    String xIdTipoTercero = "1";
    String xIdOperacionPedidoStr = "1";
    String xIdOperacionExtrusionStr = "2";
    String xIdOperacionImpresionStr = "3";
    String xIdOperacionRefiladoStr = "4";
    String xIdOperacionSelladoStr = "5";
    String xIdOperacionManualStr = "6";
    %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />

    <jsp:useBean id="fachadaPluBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluBean" />

    <head>
        <title>Lista Ficha Tecnica</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest"
                   value="/jsp/vtaFrmLstFichaTecnica.jsp">
            <input type="hidden" name="xIdFicha"
                   value="<%=fachadaDctoOrdenBean.getIdFicha()%>">
            <input type="hidden" name="xNumeroOrden"
                   value="<%=fachadaPluFicha.getNumeroOrdenStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">LISTA FICHA TECNICA</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraResaltadaTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        &nbsp;
                    </td>
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
                <tr>
                    <td width="33%" align="center" class="letraResaltadaTitulo">#ITEM</td>
                    <td width="34%" align="center" class="letraResaltadaTitulo">CANTIDAD</td>
                    <td width="33%" align="center" class="letraResaltadaTitulo">FEC.ENTREGA</td>
                </tr>
                <lsv:listaOrdenItem idLocalTag = "<%=fachadaPluFicha.getIdLocalStr()%>"
                                    idTipoOrdenTag = "<%=fachadaPluFicha.getIdTipoOrdenStr()%>"
                                    numeroOrdenTag = "<%=fachadaPluFicha.getNumeroOrdenStr()%>">
                <tr>
                    <td width="33%" align="center" class="letraDetalle">
                        <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstFichaTecnica.jsp&accionContenedor=trae&xIdOperacion=<%=fachadaPluFicha.getIdOperacionStr()%>&xIdFicha=<%=fachadaDctoOrdenBean.getIdFicha()%>&xItem=<%=itemVar%>&xNumeroOrden=<%=fachadaPluFicha.getNumeroOrdenStr()%>&xAccionBoton=<%=fachadaPluFicha.getAccionBoton()%>"><%=itemVar%></a>
                    </td>
                    <td width="34%" align="center" class="letraDetalle"><%=cantidadVar%></td>
                    <td width="33%" align="center" class="letraDetalle"><%=fechaEntregaVar%></td>
                </tr>
                </lsv:listaOrdenItem>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
