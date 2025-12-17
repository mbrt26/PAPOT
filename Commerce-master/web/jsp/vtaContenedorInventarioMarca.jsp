<html>
    <%@ taglib uri="/WEB-INF/tlds/listaUnAllMarca" prefix="lst" %>
    <head>
        <title>Inventario Marca</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
                          <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorInventarioMarca.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">INVENTARIO MARCA</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">
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

                    <td width="33%" align="center" class="letraDetalle">
                        <jsp:include page="./comboDestinacion.jsp"/>
                    </td>
                    <td width="34%" align="right" class="letraDetalle">MARCA</td>
                    <td width="33%" align="left" class="letraDetalle">
                           <select name='xIdMarca' onchange=location.href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorInventarioMarca.jsp&accionContenedor=listaMarca&xIdDestinacion="+idDestinacion.value+"&xIdMarca="+this.value>
                                <lst:listaUnAllMarca>
                                    <option value="<%=idMarcaVar%>">
                                    <%=nombreMarcaVar%>
                                </option>
                            </lst:listaUnAllMarca>
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