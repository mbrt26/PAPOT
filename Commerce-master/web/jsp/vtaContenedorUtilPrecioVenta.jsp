<html>
    <%@ taglib uri="/WEB-INF/tlds/listaCategoria" prefix="lst" %>
    <head>
        <title>Utilidad Referencia</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorUtilPrecioVenta.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">UTILIDAD REFERENCIA</td>
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
                    <td width="33%" class="letraDetalle">CATEGORIA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <select name='xIdLineaCategoria' onchange=location.href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorUtilPrecioVenta.jsp&accionContenedor=listaCategoria&xIdLineaCategoria="+this.value>
                                <lst:listaCategoria>
                                    <option value="<%=idLineaCategoriaVar%>">
                                    <%=nombreCategoriaVar%>
                                </option>
                            </lst:listaCategoria>
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
               <!--tr>
                    <td width="33%" class="letraDetalle">REFERENCIA</td>
                    <td width="34%" class="letraDetalle">
                        <input type="text" name="xIdPlu" value="" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr-->
                <tr>
                    <td width="33%" class="letraDetalle">NOMBRE/PLU</td>
                    <td width="34%" aling="center" class="letraDetalle">
                        <input type="text" name="idLinea" value="" size="40">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Consultar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">
                        &nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>