<html>

    <%@ taglib uri="/WEB-INF/tlds/listaInventarioPlu" prefix="lst" %>

    <jsp:useBean id="fachadaPluBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluBean" />

    <head>
        <title>Seleccione Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConInventarioReferencia.jsp">

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
                    <td width="34%" align="center" class="letraTitulo"><%=fachadaPluBean.getNombreCategoria()%></td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">

                    <tr>
                        <td width="46%" align="left" class="letraDetalle">REFERENCIA</td>
                        <td width="8%" align="right" class="letraDetalle">VR.LISTA#1</td>
                        <td width="8%" align="right" class="letraDetalle">VR.COSTO</td>
                        <td width="8%" align="right" class="letraDetalle">VR.COSTOIND</td>
                        <td width="8%" align="right" class="letraDetalle">CODIGO</td>
                        <td width="14%" align="left" class="letraDetalle">MARCA</td>
                        <td width="8%" align="right" class="letraDetalle">%IVA</td>
                        <td width="8%" align="right" class="letraDetalle">EXISTENCIA</td>
                    </tr>


                <lst:listaInventarioPlu idLocalTag="<%=fachadaPluBean.getIdLocalStr()%>"
                                          idPluTag="<%=fachadaPluBean.getIdPluStr()%>"
                                          idBodegaTag="<%=fachadaPluBean.getIdBodegaStr()%>">

                    <tr>
                        <td width="46%" align="left" class="letraDetalle">
                            <%=nombreCategoriaVar%>
                            <%=nombrePluVar%>
                            </td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrGeneralDf0Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrCostoDf2Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrCostoINDDf2Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        <td width="14%" align="left" class="letraDetalle"><%=nombreMarcaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=porcentajeIvaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=existenciaDf2Var%></td>
                    </tr>
                </lst:listaInventarioPlu>
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