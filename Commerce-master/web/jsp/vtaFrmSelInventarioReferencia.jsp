<html>

    <%@ taglib uri="/WEB-INF/tlds/listaInventarioCategoria" prefix="lst" %>

    <jsp:useBean id="fachadaCategoriaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaCategoriaBean" />

    <head>
        <title>Seleccione Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelInventarioReferencia.jsp">

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
                        <td width="30%" align="left" class="letraDetalle">REFERENCIA</td>
                        <td width="8%" align="right" class="letraDetalle">VR.L#1</td>
                        <td width="8%" align="right" class="letraDetalle">VR.L#2</td>
                        <td width="8%" align="right" class="letraDetalle">VR.L#3</td>
                        <td width="8%" align="right" class="letraDetalle">VR.COSTO</td>
                        <td width="8%" align="right" class="letraDetalle">CODIGO</td>
                        <td width="14%" align="left" class="letraDetalle">MARCA</td>
                        <td width="8%" align="right" class="letraDetalle">%IVA</td>
                        <td width="8%" align="right" class="letraDetalle">EXISTENCIA</td>
                    </tr>


                <lst:listaInventarioCategoria idLineaTag="<%=fachadaCategoriaBean.getIdLineaStr()%>"
                                       idCategoriaTag="<%=fachadaCategoriaBean.getIdCategoriaStr()%>">

                    <tr>
                        <td width="30%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelInventarioReferencia.jsp&accionContenedor=Traer&xIdPlu=<%=idPluVar%>"><%=nombreCategoriaVar%> <%=nombrePluVar%></a>
                            </td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrGeneralDf0Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrMayoristaDf0Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrSucursalDf0Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrCostoDf2Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        <td width="14%" align="left" class="letraDetalle"><%=nombreMarcaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=porcentajeIvaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=existenciaDf2Var%></td>
                    </tr>
                </lst:listaInventarioCategoria>
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