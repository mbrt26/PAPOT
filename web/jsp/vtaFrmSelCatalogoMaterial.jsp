<html>

    <%@ taglib uri="/WEB-INF/tlds/listaPluCategoria" prefix="lst" %>

    <jsp:useBean id="fachadaCategoriaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaCategoriaBean" />

    <head>
        <title>Seleccione Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <%
                String xIdBodega = "1";
    %>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelCatalogoMaterial.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">SELECCIONE MATERIAL</td>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td colspan="3" width="33%" class="letraTitulo" colspan="2">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" width="33%" align="left" class="letraTitulo">&nbsp;</td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo"><%=fachadaCategoriaBean.getNombreCategoria()%></td>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>

                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <th width="5%" align="right" class="letraTitulo">PLU</th>
                    <th width="30%" align="left" class="letraTitulo">DESCRIPCION REFERENCIA</th>
                    <th width="5%" align="center" class="letraTitulo">UNIDAD DE <br>DESPACHO</th>
                    <th width="5%" align="center" class="letraTitulo">TIPO</th>
                    <th width="5%" align="left" class="letraTitulo">REFERENCIA</th>
                    <th width="50%" align="left" class="letraTitulo">&nbsp;</th>
                </tr>
                <lst:listaPluCategoria idLineaTag="<%=fachadaCategoriaBean.getIdLineaStr()%>"
                idCategoriaTag="<%=fachadaCategoriaBean.getIdCategoriaStr()%>"
                idLocalTag="<%=fachadaCategoriaBean.getIdLocalStr()%>"
                idBodegaTag="<%=xIdBodega%>" >
                    <tr>
                        <td width="5%" align="right" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelCatalogoMaterial.jsp&accionContenedor=Traer&xIdPlu=<%=idPluVar%>"><%=idPluVar%></a>
                        </td>
                        <td width="30%" align="left" class="<%=letraEstiloVar%>">
                            <%=nombreCategoriaVar%>
                            <%=nombrePluVar%>
                        </td>
                        <td width="5%" align="center" class="letraDetalle"><%=factorDespachoVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=idTipoVar%></td>
                        <th width="5%" align="left" class="letraDetalle"><%=referenciaVar%></th>
                        <th width="50%" align="left" class="letraDetalle">&nbsp;</th>
                    </tr>

                </lst:listaPluCategoria>


                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>