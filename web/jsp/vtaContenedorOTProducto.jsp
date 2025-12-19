<html>
<%@ taglib uri="/WEB-INF/tlds/listaOperacionOpcion.tld" prefix="lsu" %>

    <head>
        <title>Produccion</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

<% String xIdOperacionCadena = "2,3,4,5,6,666,888,999"; %>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorOTProducto.jsp">
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">PRODUCCION</td>
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
                    <td width="33%" align="center" class="letraTitulo">OPERACION</td>
                    <td width="34%" align="center" class="letraTitulo">PEDIDO</td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraDetalle">
                        <select name='xIdOperacion'>
                            <lsu:listaOperacionOpcion idOperacionCadenaTag="<%=xIdOperacionCadena%>">
                                <option value="<%=idOperacionVar%>">
                                    <%=nombreOperacionVar%>
                                </option>
                            </lsu:listaOperacionOpcion>
                        </select>
                    </td>
                    <td width="34%" align="center" class="letraDetalle">
                        <input type="text" name="xNumeroOrden" id="xNumeroOrden" value="" size="6" maxlength="6">
                    </td>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>
            <table border="0" width="90%" id="letraDetalle">
                <tr>
                    <td width="33%" align="center" class="letraDetalle">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%"  align="center" class="letraDetalle">

                        <input type="submit" value="Registro Produccion" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraDetalle">
                        <input type="submit" value="Registro Tiempo Perdido" name="accionContenedor">
                    </td>
                </tr>
            </table>

            <script type="text/javascript">
                document.getElementById('xNumeroOrden').focus();
            </script>

        </form>
    </body>
</html>