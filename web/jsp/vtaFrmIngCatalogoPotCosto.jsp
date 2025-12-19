<html>

    <jsp:useBean id="fachadaJobOperacion"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaJobOperacion" />

    <jsp:useBean id="fachadaJobCosto"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaJobCosto" />

    <head>
        <title>Ingresa Costo</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngCatalogoPotCosto.jsp">
            <input type="hidden" name="xIdOperacion" value=<%=fachadaJobOperacion.getIdOperacionStr()%>>
            <input type="hidden" name="xIdCosto" value=<%=fachadaJobCosto.getIdCostoStr()%>>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">INGRESA COSTO</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraTitulo">OPERACION
                        <br><%=fachadaJobOperacion.getNombreOperacion()%>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">TIPO COSTO
                        <br><%=fachadaJobCosto.getNombreCosto()%>
                    </td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="right" class="letraDetalle">CANTIDAD BASE</td>
                    <td width="34%" align="right" class="letraDetalle">VR.COSTO UNITARIO</td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="right" class="letraDetalle">
                        <input type="text" value="0" name="xCantidadBase" maxlength="10" size="10">
                    </td>
                    <td width="34%" align="right" class="letraDetalle">
                        <input type="text" value="0" name="xVrCostoBase" maxlength="10" size="10">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Guardar" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>

</html>