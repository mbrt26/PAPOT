<html>
    <%@ taglib uri="/WEB-INF/tlds/listaUnAllCategoria" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaLocalBodega" prefix="lsu" %>

    <jsp:useBean id="fachadaCategoriaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaCategoriaBean" />

    <head>
        <title>Utilidad Referencia</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorInventarioCategoria.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">INVENTARIO CATEGORIA</td>
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

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="34%" align="center" class="letraDetalle">BODEGA</td>
                    <td width="33%" align="center" class="letraDetalle">CATEGORIA</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraDetalle">
                        <jsp:include page="./comboDestinacion.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraDetalle">
                        <select name="xIdBodega">
                            <lsu:listaLocalBodega idLocalTag="<%=fachadaCategoriaBean.getIdLocalStr()%>">
                                <option value="<%=idBodegaVar%>">
                                    <%=nombreBodegaVar%>
                                </option>
                            </lsu:listaLocalBodega>
                        </select>
                    </td>
                    <td width="33%" align="center" class="letraDetalle">
                        <select name='xIdLineaCategoria' onchange=location.href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorInventarioCategoria.jsp&accionContenedor=listaCategoria&xIdDestinacion="+idDestinacion.value+"&xIdBodega="+xIdBodega.value+"&xIdLineaCategoria="+xIdLineaCategoria.value>
                                <lst:listaUnAllCategoria>
                                    <option value="<%=idLineaCategoriaVar%>">
                                    <%=nombreCategoriaVar%>
                                </option>
                            </lst:listaUnAllCategoria>
                        </select>
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        &nbsp;
                    </td>
                    <td width="33%" class="letraTitulo">
                        &nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>