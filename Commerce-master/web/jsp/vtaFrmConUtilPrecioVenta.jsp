<html>
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
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmConUtilPrecioVenta.jsp">
            <input type="hidden" name="xIdPlu" value=<%=fachadaPluBean.getIdPluStr()%>>
            <input type="hidden" name="xVrSucursal" value="<%=fachadaPluBean.getVrSucursalStr()%>" size="10">
            <input type="hidden" name="xVrMayorista" value="<%=fachadaPluBean.getVrMayoristaStr()%>" size="10">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CONSULTA REFERENCIA</td>
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
                <td width="34%" align="left" class="letraDetalle">
                    <%=fachadaPluBean.getIdPluStr()%>
                </td>
                <td width="33%" class="letraDetalle">&nbsp;</td>
            </tr>

            <tr>
                <td width="33%" class="letraDetalle">DESCRIPCION</td>
                <td width="34%" align="left" class="letraDetalle">
                    <%=fachadaPluBean.getNombreCategoria()%>
                    <%=fachadaPluBean.getNombrePlu()%>
                </td>
                <td width="33%" class="letraDetalle">&nbsp;</td>
            </tr>

            <tr>
                <td width="33%" class="letraDetalle">%IVA</td>
                <td width="34%" align="left" class="letraDetalle">
                    <%=fachadaPluBean.getPorcentajeIvaStr()%>
                </td>
                <td width="33%" class="letraDetalle">&nbsp;</td>
            </tr>

            <tr>
                <td width="33%" class="letraDetalle">TIPO</td>
                <td width="34%" align="left" class="letraDetalle">
                    <%=fachadaPluBean.getIdTipoStr()%>
                </td>
                <td width="33%" class="letraDetalle">&nbsp;</td>
            </tr>

            <tr>
                <td width="33%" class="letraDetalle">MARCA</td>
                <td width="34%" align="left" class="letraDetalle">
                    <%=fachadaPluBean.getNombreMarca()%>
                </td>
                <td width="33%" class="letraDetalle">&nbsp;</td>
            </tr>


            <tr>
                <td width="33%" class="letraDetalle">LISTA PRECIO#1</td>
                <td width="34%" align="left" class="letraDetalle">
                    <%=fachadaPluBean.getVrGeneralDf0()%>
                </td>
                <td width="33%" class="letraDetalle">&nbsp;</td>
            </tr>

            <tr>
                <td width="33%" class="letraDetalle">LISTA PRECIO#2</td>
                <td width="34%" align="left" class="letraDetalle">
                    <%=fachadaPluBean.getVrMayoristaDf0()%>
                </td>
                <td width="33%" class="letraDetalle">&nbsp;</td>
            </tr>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
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