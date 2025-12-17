<html>
    <%@ taglib uri="/WEB-INF/tlds/listaCategoria" prefix="lst" %>
    <head>
        <title>Utilidad Modifica Contraseña</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorUtilClave.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">UTILIDAD MODIFICA CONTRASEÑA</td>
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
                    <td width="50%" align="center" class="letraDetalle">USUARIO</td>
                    <td width="50%" align="center" class="letraDetalle">CONTRASEÑA ACTUAL</td>
                </tr>

                <tr>
                    <td width="50%" align="center" class="letraDetalle">
                        <input type="password" value="" name="xIdUsuarioActual" size="12" maxlength="12">
                        </td>
                    <td width="50%" align="center" class="letraDetalle">
                        <input type="password" value="" name="xClave" size="8" maxlength="8">
                        </td>
                </tr>

                <tr>
                    <td width="50%" align="center" class="letraDetalle">NUEVA CONSTRASEÑA</td>
                    <td width="50%" align="center" class="letraDetalle">REPITA NUEVA CONTRASEÑA</td>
                </tr>

                <tr>
                <tr>
                    <td width="50%" align="center" class="letraDetalle">
                        <input type="password" value="" name="xClaveUno" size="8" maxlength="8">
                        </td>
                    <td width="50%" align="center" class="letraDetalle">
                        <input type="password" value="" name="xClaveDos" size="8" maxlength="8">
                        </td>
                </tr>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Modificar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">
                        &nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>