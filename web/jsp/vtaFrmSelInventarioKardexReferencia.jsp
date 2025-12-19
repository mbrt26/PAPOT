<html>

    <%@ taglib uri="/WEB-INF/tlds/listaPluTipoCategoria" prefix="lst" %>

    <jsp:useBean id="fachadaCategoriaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaCategoriaBean" />

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

    <head>
        <title>Seleccione Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelInventarioKardexReferencia.jsp">

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
                    <td width="8%" align="right" class="letraDetalle">CODIGO</td>
                    <td width="30%" align="left" class="letraDetalle">REFERENCIA</td>
                    <td width="8%" align="right" class="letraDetalle">VR.L#1</td>
                    <td width="8%" align="right" class="letraDetalle">VR.L#2</td>
                    <td width="8%" align="right" class="letraDetalle">VR.L#3</td>
                    <td width="8%" align="right" class="letraDetalle">VR.COSTO</td>
                    <td width="14%" align="left" class="letraDetalle">MARCA</td>
                    <td width="8%" align="right" class="letraDetalle">%IVA</td>
                    <td width="8%" align="right" class="letraDetalle">EXISTENCIA</td>
                </tr>

                <lst:listaPluTipoCategoria idLineaTag="<%=fachadaCategoriaBean.getIdLineaStr()%>"
                                       idCategoriaTag="<%=fachadaCategoriaBean.getIdCategoriaStr()%>"
                                            idTipoTag="<%=fachadaColaboraReporteDctoBean.getIdTipoStr()%>"
                                          idBodegaTag="<%=fachadaColaboraReporteDctoBean.getIdBodegaStr()%>"
                                           idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>">
                                               
                    <tr>
                        <td width="8%" align="right" class="letraDetalle"><%=idPluVar%></td>
                        <td width="30%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelInventarioKardexReferencia.jsp&accionContenedor=traeReferencia&xIdLocal=<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>&xIdPlu=<%=idPluVar%>&xFechaInicial=<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>&xFechaFinal=<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>&xIdTipo=<%=fachadaColaboraReporteDctoBean.getIdTipoStr()%>&xIdBodega=<%=fachadaColaboraReporteDctoBean.getIdBodegaStr()%>"><%=nombreCategoriaVar%> <%=nombrePluVar%></a>
                        </td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrGeneralDf0Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrMayoristaDf0Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrSucursalDf0Var%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=vrCostoDf2Var%></td>
                        <td width="14%" align="left" class="letraDetalle"><%=nombreMarcaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=porcentajeIvaVar%></td>
                        <td width="8%" align="right" class="letraDetalle"><%=existenciaDf2Var%></td>
                    </tr>

                </lst:listaPluTipoCategoria>

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