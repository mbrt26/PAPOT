<html>
    <%@ taglib uri="/WEB-INF/tlds/listaUnAllCategoria" prefix="lst" %>

    <head>
        <title>Catalogo Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorCatalogoMaterial.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CATALOGO MATERIAL</td>
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
                    <td width="25%" class="letraDetalle">CATEGORIA</td>


                    <td width="25%" align="left" class="letraDetalle">
                    <jsp:include page="./comboAllCategoria.jsp"/>
                    </td>
                     <td width="25%" align="center" class="letraDetalle">DESTINACION</td>
                    <td width="25%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="25%" class="letraDetalle">MARCA</td>
                    <td width="25%" align="left" class="letraDetalle">&nbsp;</td>
                    <td width="25%" align="center" class="letraDetalle">
                    <jsp:include page="./comboDestinacion.jsp"/>
                    </td>
                    <td width="25%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="25%" class="letraDetalle">REFERENCIA</td>
                    <td width="25%" align="left" class="letraDetalle">
                        <input type="text" name="xIdPlu" value="" size="10">
                    </td>
                    <td width="25%" class="letraDetalle">&nbsp;</td>
                    <td width="25%" class="letraDetalle">&nbsp;</td>
                </tr>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Traer" name="accionContenedor" >
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Ingresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Consultar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>