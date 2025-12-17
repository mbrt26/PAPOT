<html>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaOTEstadoPedido.tld" prefix="lst" %>

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />

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
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConOTEstado.jsp">


            <table border="0" width="90%" id="tablaTitulo">
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
                <tr>
                    <td width="33%" align="center" class="letraResaltadaTitulo"> #PEDIDO
                        <br><%=fachadaPluFicha.getNumeroOrden()%>-<%=fachadaPluFicha.getItemPadre()%>
                    </td>
                    <td width="34%" align="left" class="letraResaltadaTitulo">
                        REFERENCIA CLIENTE
                        <br><%=fachadaPluFicha.getReferenciaCliente()%>
                        <br>REFERENCIA
                        <br><%=fachadaPluFicha.getReferencia()%>
                        <br>FICHA : <%=fachadaPluFicha.getIdFicha()%>
                    </td>
                    <td width="33%" align="right" class="letraTitulo">
                        <table border="0" width="90%" id="letraResaltadaTitulo">
                            <tr>
                            <td width="50%" align="left" class="letraResaltadaTitulo">
                                CANTIDAD PEDIDA (UN)
                            </td>
                            <td width="50%" align="right" class="letraResaltadaTitulo">
                                <%=fachadaPluFicha.getCantidadDf0()%>
                            </td>
                            </tr>
                            <tr>
                            <td width="50%" align="left" class="letraResaltadaTitulo">
                                PESO(KG) PEDIDO
                            </td>
                            <td width="50%" align="right" class="letraResaltadaTitulo">
                                <%=fachadaPluFicha.getPesoPedidoDf0()%>
                            </td>
                            </tr>
                            <tr>
                            <td width="50%" align="left" class="letraResaltadaTitulo">
                                CANTIDAD TERMINADA (UN)
                            </td>
                            <td width="50%" align="right" class="letraResaltadaTitulo">
                                <%=fachadaPluFicha.getCantidadTerminadaDf0()%>
                            </td>
                            </tr>
                            <tr>
                            <td width="50%" align="left" class="letraResaltadaTitulo">
                                PESO(KG) TERMINADO
                            </td>
                            <td width="50%" align="right" class="letraResaltadaTitulo">
                                <%=fachadaPluFicha.getPesoTerminadoDf1()%>
                            </td>
                            </tr>
                            <tr>
                            <td width="50%" align="left" class="letraResaltadaTitulo">
                                CANTIDAD FACTURADA (UN)
                            </td>
                            <td width="50%" align="right" class="letraResaltadaTitulo">
                                <%=fachadaPluFicha.getCantidadFacturadaDf0()%>
                            </td>
                            </tr>
                            <tr>
                            <td width="50%" align="left" class="letraResaltadaTitulo">
                                CANTIDAD REMISIONADA (UN)
                            </td>
                            <td width="50%" align="right" class="letraResaltadaTitulo">
                                <%=fachadaPluFicha.getCantidadEntregadaDf0()%>
                            </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="left" class="letraTitulo">OPERACION</td>
                    <td width="5%" align="right" class="letraTitulo">PESOS (KG)
                                                                 <br>TERMINADO</td>
                    <td width="5%" align="right" class="letraTitulo">CANTIDAD
                                                                     <br>TERMINADA</td>
                    <td width="5%" align="right" class="letraTitulo">PESO (KG)
                                                                    <br>RETAL</td>
                    <td width="10%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>
                <lst:listaOTEstadoPedido idLocalTag = "<%=fachadaPluFicha.getIdLocalStr()%>"
                idTipoOrdenTag = "<%=fachadaPluFicha.getIdTipoOrdenStr()%>"
                idOrdenTag = "<%=fachadaPluFicha.getIdOrdenStr()%>"
                itemPadreTag = "<%=fachadaPluFicha.getItemPadreStr()%>">
                    <tr>
                        <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
                        <td width="20%" align="left" class="letraDetalle"><%=nombreOperacionVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=pesoTerminadoVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=pesoPerdidoVar%></td>
                        <td width="10%" align="right" class="letraDetalle">&nbsp;</td>
                    </tr>
                </lst:listaOTEstadoPedido>
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

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

</html>