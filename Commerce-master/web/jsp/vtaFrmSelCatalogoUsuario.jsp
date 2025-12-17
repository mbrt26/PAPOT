<html>

    <%@ taglib uri="/WEB-INF/tlds/listaUsuario" prefix="lst" %>
    <jsp:useBean id="fachadaUsuarioBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaUsuarioBean" />

    <head>
        <title>Catalogo Usuario</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelCatalogoUsuario.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">LISTA USUARIO</td>
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
                <td width="40%" align="left" class="letraDetalle">NOMBRE</td>
                <td width="10%" align="right" class="letraDetalle">CEDULA</td>
                <td width="20%" align="left" class="letraDetalle">DIRECCION</td>
                <td width="10%" align="left" class="letraDetalle">TELEFONO</td>
                <td width="10%" align="left" class="letraDetalle">ALIAS</td>
                <td width="10%" align="left" class="letraDetalle">IDLOCAL</td>
            </tr>

            <lst:listaUsuario nombreUsuarioTag="<%=fachadaUsuarioBean.getNombreUsuario()%>">

                <tr>
                    <td width="40%" align="left" class="letraDetalle">
                        <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelCatalogoUsuario.jsp&accionContenedor=Seleccionar&xIdUsuario=<%=idUsuarioVar%>"><%=nombreUsuarioVar%></a>
                        </td>
                    <td width="10%" align="right" class="letraDetalle"><%=idUsuarioVar%></td>
                    <td width="20%" align="left" class="letraDetalle"><%=direccionVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=telefonoVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=aliasUsuarioVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=idLocalVar%></td>
                </tr>

            </lst:listaUsuario>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Eliminar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>