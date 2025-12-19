<html>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacion" prefix="lsv" %>
    <head>
        <title>Utilidad Referencia</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorInventarioReferencia.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">INVENTARIO REFERENCIA</td>
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
                    <td width="33%"  align="center" class="letraDetalle">BODEGA</td>
                    <td width="34%"  align="left" class="letraDetalle">REFERENCIA</td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraDetalle">
                       <select name='xIdOperacion'>
                            <lsv:listaOperacion>
                                <option value="<%=idOperacionVar%>">
                                    <%=nombreOperacionVar%>
                                </option>
                            </lsv:listaOperacion>
                        </select>
                    </td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xIdPlu" value="" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="left" class="letraTitulo">
                        <input type="submit" value="Traer" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">
                        &nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>