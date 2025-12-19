<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
    <head>
        <title>Recibo Compra</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <%@ taglib uri="/WEB-INF/tlds/listaCentroOtro" prefix="lst" %>
    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorReciboCompra.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">RECIBO COMPRA</td>
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
                    <td width="25%" align="center" class="letraTitulo">PROVEEDOR</td>
                    <td width="20%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="35%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="20%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="25%" align="center" class="letraDetalle">
                        <jsp:include page="./comboTercero.jsp"/>
                    </td>
                    <td width="20%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="35%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="20%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Traer" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
