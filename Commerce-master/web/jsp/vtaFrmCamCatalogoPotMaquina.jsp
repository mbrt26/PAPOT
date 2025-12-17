<html>
    <%@ taglib uri="/WEB-INF/tlds/listaUnUsuarioNivel" prefix="lst" %>
    <jsp:useBean id="fachadaJobEscalaDetalle"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaJobEscalaDetalle" />
    <head>
        <title>Cambia Maquina</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmCamCatalogoPotMaquina.jsp">
            <input type="hidden" name="xIdEscala" value="<%=fachadaJobEscalaDetalle.getIdEscalaStr()%>">
            <input type="hidden" name="xItem" value="<%=fachadaJobEscalaDetalle.getItemStr()%>">            

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">CAMBIA MAQUINA</td>
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
                    <td width="33%" class="letraDetalle">NOMBRE MAQUINA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xNombreMaquina" type="text" value="<%=fachadaJobEscalaDetalle.getNombreItem()%>" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">ESTADO<br> ( 1-activo / 2-inactivo ) </td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xEstado" type="text" value="<%=fachadaJobEscalaDetalle.getEstadoStr()%>" size="1" maxlength="1">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
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
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
