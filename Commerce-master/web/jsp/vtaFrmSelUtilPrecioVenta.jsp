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
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelUtilPrecioVenta.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">SELECCIONE REFERENCIA</td>
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
                    <td width="33%" align="left" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo"><%=fachadaCategoriaBean.getNombreCategoria()%></td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">

                    <tr>
                        <td width="8%" align="right" class="letraDetalle">PLU</td>
                        <td width="46%" align="left" class="letraDetalle">REFERENCIA</td>
                        <td width="8%" align="right" class="letraDetalle">VR.LISTA#1</td>
                        <td width="8%" align="right" class="letraDetalle">VR.COSTO</td>
                        <td width="14%" align="left" class="letraDetalle">MARCA</td>
                        <td width="8%" align="right" class="letraDetalle">%IVA</td>
                        <td width="8%" align="right" class="letraDetalle">TIPO</td>
                    </tr>

                <lst:listaPluCategoria idLineaTag="<%=fachadaCategoriaBean.getIdLineaStr()%>"
                                       idCategoriaTag="<%=fachadaCategoriaBean.getIdCategoriaStr()%>"
                                       idLocalTag="<%=fachadaCategoriaBean.getIdLocalStr()%>"
                                       idBodegaTag="<%=xIdBodega%>" >

                    <tr>
                        <td width="8%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        <td width="46%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelUtilPrecioVenta.jsp&accionContenedor=Traer&xIdPlu=<%=idPluVar%>"><%=nombreCategoriaVar%> <%=nombrePluVar%></a>
                            </td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrGeneralDf0Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrCostoDf2Var%></td>
                        <td width="14%" align="left" class="letraDetalle"><%=nombreMarcaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=porcentajeIvaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=idTipoVar%></td>
                    </tr>
                </lst:listaPluCategoria>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>