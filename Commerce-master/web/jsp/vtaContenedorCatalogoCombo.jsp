<html>
    <%@ taglib uri="/WEB-INF/tlds/listaPluTipo" prefix="lst" %>

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
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorCatalogoCombo.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CATALOGO COMBOS</td>
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
                    <td width="8%" align="right" class="letraTitulo">PLU</td>
                    <td width="30%" align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="8%" align="right" class="letraTitulo">V.L#1</td>
                    <td width="8%" align="right" class="letraTitulo">V.L#2</td>
                    <td width="8%" align="right" class="letraTitulo">V.L#3</td>
                    <td width="5%" align="right" class="letraTitulo">COSTO</td>
                    <td width="5%" align="right" class="letraTitulo">CTO.IND</td>
                    <td width="14%" align="left" class="letraTitulo">MARCA</td>
                    <td width="8%" align="right" class="letraTitulo">%IVA</td>
                </tr>


                <lst:listaPluTipo idTipoTag="<%=fachadaPluBean.getIdTipoStr()%>">

                    <tr>
                        <td width="8%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        <td width="30%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorCatalogoCombo.jsp&accionContenedor=Traer&xIdPlu=<%=idPluVar%>"><%=nombreCategoriaVar%> <%=nombrePluVar%></a>
                        </td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrGeneralDf0Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrMayoristaDf0Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrSucursalDf0Var%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=vrCostoDf2Var%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=vrCostoINDDf2Var%></td>
                        <td width="14%" align="left" class="letraDetalle"><%=nombreMarcaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=porcentajeIvaVar%></td>
                    </tr>

                </lst:listaPluTipo>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        &nbsp;
                    </td>
                    <td width="33%" class="letraTitulo">
                        &nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>