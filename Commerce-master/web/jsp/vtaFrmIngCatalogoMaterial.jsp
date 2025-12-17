<html>
    <%@ taglib uri="/WEB-INF/tlds/listaCategoriaSeleccion" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaMarcaSeleccion" prefix="lsu" %>

    <jsp:useBean id="fachadaPluBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluBean" />

    <head>
        <title>Catalogo Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngCatalogoMaterial.jsp">
            <input type="hidden" name="xIdPlu" value=<%=fachadaPluBean.getIdPluStr()%>>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">INGRESA MATERIAL</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
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

                <select name='xIdLineaCategoria'>
                    <lst:listaCategoriaSeleccion idLineaTag="<%=fachadaPluBean.getIdLineaStr()%>"
                                                 idCategoriaTag="<%=fachadaPluBean.getIdCategoriaStr()%>">
                        <option value="<%=idLineaCategoriaVar%>">
                            <%=nombreCategoriaVar%>
                        </option>
                    </lst:listaCategoriaSeleccion>
                </select>

                <td width="33%" class="letraDetalle">&nbsp;</td>
            </tr>

            <tr>
                <td width="33%" class="letraDetalle">DESCRIPCION</td>
                <td width="34%" align="left" class="letraDetalle">
                    <input type="text" name="xNombrePlu" value="<%=fachadaPluBean.getNombrePlu()%>" maxlength="50" size="50">
                </td>
                <td width="33%" class="letraDetalle">&nbsp;</td>
            </tr>

                <tr>
                    <td width="33%" class="letraDetalle">DESCRIPCION REFERENCIA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xReferencia" value="<%=fachadaPluBean.getReferencia()%>" maxlength="50" size="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Guardar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>

</html>