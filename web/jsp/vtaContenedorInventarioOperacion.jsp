<html>
    <%@ taglib uri="/WEB-INF/tlds/listaBodegaOpcion" prefix="lsu" %>

    <jsp:useBean id="fachadaCategoriaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaCategoriaBean" />

    <head>
        <title>Inventario Operacion</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorInventarioOperacion.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">INVENTARIO OPERACION</td>
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
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">OPERACION</td>
                <td width="33%" align="center" class="letraTitulo">DESTINACION</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="34%" align="center" class="letraDetalle">
                        <select name="xIdBodega">
                            <lsu:listaBodegaOpcion idLocalTag="<%=fachadaCategoriaBean.getIdLocalStr()%>">
                                <option value="<%=idBodegaVar%>">
                                    <%=nombreBodegaVar%>
                                </option>
                            </lsu:listaBodegaOpcion>
                        </select>
                    </td>
                    <td width="33%" align="center" class="letraDetalle">
                     <jsp:include page="./comboDestinacion.jsp"/>
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Consultar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>