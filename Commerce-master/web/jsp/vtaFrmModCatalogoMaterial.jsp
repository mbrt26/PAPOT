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
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModCatalogoMaterial.jsp">
            <input type="hidden" name="xIdPlu" value=<%=fachadaPluBean.getIdPluStr()%>>
            <input type="hidden" name="xIdMarca" value="<%=fachadaPluBean.getIdMarcaStr()%>">
            <input type="hidden" name="xFactorDensidad" value="<%=fachadaPluBean.getFactorDensidadStr()%>">
            <input type="hidden" name="xFactorDespacho" value="<%=fachadaPluBean.getFactorDespachoStr()%>">
            <input type="hidden" name="xVrImpoconsumo" value="<%=fachadaPluBean.getVrImpoconsumoStr()%>">
            <input type="hidden" name="xVrCostoIND" value="<%=fachadaPluBean.getVrCostoINDSf2()%>">
            <input type="hidden" name="xVrCosto" value="<%=fachadaPluBean.getVrCostoSf2()%>">
            <input type="hidden" name="xIdTipo" value="<%=fachadaPluBean.getIdTipoStr()%>">
            <input type="hidden" name="xPorcentajeIva" value="<%=fachadaPluBean.getPorcentajeIvaStr()%>">
            <input type="hidden" name="xVrSucursal" value="<%=fachadaPluBean.getVrSucursalSf0()%>">
            <input type="hidden" name="xVrGeneral" value="<%=fachadaPluBean.getVrGeneralSf0()%>">
            <input type="hidden" name="xVrMayorista" value="<%=fachadaPluBean.getVrMayoristaSf0()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICA MATERIAL</td>
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
                    <td width="33%" class="letraDetalle">CODIGO</td>
                    <td width="34%" align="left" class="letraDetalle"><%=fachadaPluBean.getIdPluStr()%></td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

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
                    <td width="33%" class="letraDetalle">DESCRIPCION REFERENCIA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xNombrePlu" value="<%=fachadaPluBean.getNombrePlu()%>" maxlength="50" size="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">REFERENCIA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xReferencia" value="<%=fachadaPluBean.getReferencia()%>" maxlength="50" size="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Modificar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>