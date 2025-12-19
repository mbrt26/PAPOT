<html>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacion" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacionOperario" prefix="lst" %>

    <jsp:useBean id="fachadaJobOperacionOperario"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaJobOperacionOperario" />

    <head>
        <title>Catalogo Operario</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorCatalogoPotOperario.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CATALOGO OPERARIO</td>
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
                    <td width="25%" class="letraDetalle">OPERACION</td>


                    <td width="25%" align="left" class="letraDetalle">
                        <select name='xIdOperacion'>
                            <lsv:listaOperacion>
                                <option value="<%=idOperacionVar%>">
                                    <%=nombreOperacionVar%>
                                </option>
                            </lsv:listaOperacion>
                        </select>
                    </td>
                    <td width="25%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="25%" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">OPERACION</td>
                    <td width="34%" align="left" class="letraTitulo">OPERARIO</td>
                    <td width="33%" align="left" class="letraTitulo">&nbsp;</td>
                </tr>
                <lst:listaOperacionOperario idLocalTag="<%=fachadaJobOperacionOperario.getIdLocalStr()%>">
                    <tr>
                        <td width="33%" align="left" class="letraDetalle"><%=nombreOperacionVar%></td>
                        <td width="34%" align="left" class="letraDetalle"><%=nombreOperarioVar%></td>
                        <td width="33%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorCatalogoPotOperario.jsp&accionContenedor=retira&xIdLocal=<%=idLocalVar%>&xIdOperacion=<%=idOperacionVar%>&xIdVendedor=<%=idOperarioVar%>">Retirar</a>
                        </td>
                    </tr>
                </lst:listaOperacionOperario>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Ingresar" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>