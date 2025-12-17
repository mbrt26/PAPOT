<html>

    <jsp:useBean id="fachadaJobOperacion"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaJobOperacion" />

    <head>
        <title>Ingresa Operario</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngCatalogoPotOperario.jsp">
            <input type="hidden" name="xIdOperacion" value=<%=fachadaJobOperacion.getIdOperacionStr()%>>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">INGRESA OPERARIO</td>
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
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">OPERACION
                        <br><%=fachadaJobOperacion.getNombreOperacion()%>
                    </td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

                    <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraDetalle">OPERARIO</td>
                <td width="34%" align="left" class="letraDetalle">
                         <jsp:include page="./comboUsuarioNivelOpcionAll.jsp"/>
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