<html>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacion" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacionCosto" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaCosto" prefix="lsu" %>

    <jsp:useBean id="fachadaJobOperacionOperario"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaJobOperacionOperario" />

    <head>
        <title>Catalogo Costo MOD/CIF</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorCatalogoPotCosto.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CATALOGO COSTO MOD/CIF</td>
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
                    <td width="20%" align="center" class="letraDetalle">
                        TIPO COSTO<br>
                        <select name='xIdCosto'>
                            <lsu:listaCosto>
                                <option value="<%=idCostoVar%>">
                                    <%=nombreCostoVar%>
                                </option>
                            </lsu:listaCosto>
                        </select>
                    </td>
                    <td width="20%" align="center" class="letraDetalle">
                        OPERACION<br>
                        <select name='xIdOperacion'>
                            <lsv:listaOperacion>
                                <option value="<%=idOperacionVar%>">
                                    <%=nombreOperacionVar%>
                                </option>
                            </lsv:listaOperacion>
                        </select>
                    </td>
                    <td width="20%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="20%" class="letraDetalle">&nbsp;</td>
                    <td width="20%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" align="left" class="letraTitulo">TIPO COSTO</td>
                    <td width="20%" align="left" class="letraTitulo">OPERACION</td>
                    <td width="20%" align="right" class="letraTitulo">VR.COSTO UNITARIO</td>
                    <td width="20%" align="right" class="letraTitulo">CANTIDAD BASE</td>
                    <td width="20%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>
                <lst:listaOperacionCosto idLocalTag="<%=fachadaJobOperacionOperario.getIdLocalStr()%>">
                <tr>
                    <td width="20%" align="left" class="letraDetalle"><%=nombreCostoVar%></td>
                    <td width="20%" align="left" class="letraDetalle"><%=nombreOperacionVar%></td>
                    <td width="20%" align="right" class="letraDetalle"><%=vrCostoBaseVar%></td>
                    <td width="20%" align="right" class="letraDetalle"><%=cantidadBaseVar%></td>
                    <td width="20%" align="right" class="letraDetalle">
                    <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorCatalogoPotCosto.jsp&accionContenedor=retira&xIdLocal=<%=idLocalVar%>&xIdOperacion=<%=idOperacionVar%>&xIdCosto=<%=idCostoVar%>">Retirar</a>
                    </td>
                </tr>
                </lst:listaOperacionCosto>
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