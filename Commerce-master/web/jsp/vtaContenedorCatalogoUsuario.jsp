<html>

    <head>
        <title>Catalogo Usuario</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorCatalogoUsuario.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CATALOGO USUARIO</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraResaltadaTitulo">
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
                    <td width="33%" class="letraDetalle">NOMBRE</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xNombreUsuario" type="text" id="xNombreUsuario" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">CEDULA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xIdUsuario" type="text" id="xIdUsuario" size="16" maxlength="16">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">NIVEL SEGURIDAD</td>
                    <td width="34%" align="left" class="letraDetalle">
                         <jsp:include page="./comboUsuarioNivel.jsp"/>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">DIRECCION</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xDireccion" value="" type="text" id="xDireccion" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xTelefono" value="" type="text" id="xTelefono" size="20" maxlength="20">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">EMAIL</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xEmail" type="text" id="xEmail" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">CENTRO OPERATIVO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xIdLocal" type="text" size="2" maxlength="2">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" class="letraDetalle">ALIAS</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xAliasUsuario" value="" type="text" size="5" maxlength="5">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Listar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Ingresar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
