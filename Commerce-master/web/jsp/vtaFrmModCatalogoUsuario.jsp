<html>
    <%@ taglib uri="/WEB-INF/tlds/listaUnUsuarioNivel" prefix="lst" %>
    <jsp:useBean id="fachadaUsuarioBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaUsuarioBean" />
    <head>
        <title>Modifica Usuario</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModCatalogoUsuario.jsp">
            <input type="hidden" name="xIdUsuario" value="<%=fachadaUsuarioBean.getIdUsuarioSf0()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICA USUARIO</td>
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
                        <input name="xNombreUsuario" type="text" value="<%=fachadaUsuarioBean.getNombreUsuario()%>" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">CEDULA</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <%=fachadaUsuarioBean.getIdUsuarioSf0()%>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">ESTADO ( 1-activo / 2-inactivo )</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xEstado" type="text" value="<%=fachadaUsuarioBean.getEstadoStr()%>" size="1" maxlength="1">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">NIVEL SEGURIDAD</td>
                    <td width="34%" align="left" class="letraDetalle">

                        <select name="xIdNivel">
                            <lst:listaUnUsuarioNivel idNivelTag  = "<%=fachadaUsuarioBean.getIdNivelStr()%>">
                                <option value="<%=idNivelVar%>">
                                    <%=nombreNivelVar%>
                                </option>
                            </lst:listaUnUsuarioNivel>
                        </select>
                        
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">DIRECCION</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xDireccion" value="<%=fachadaUsuarioBean.getDireccion()%>" type="text" id="xDireccion" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">TELEFONO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xTelefono" value="<%=fachadaUsuarioBean.getTelefono()%>" type="text" id="xTelefono" size="20" maxlength="20">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">EMAIL</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xEmail" value="<%=fachadaUsuarioBean.getEmail()%>" type="text" id="xEmail" size="50" maxlength="50">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">CENTRO OPERATIVO</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xIdLocal" value="<%=fachadaUsuarioBean.getIdLocalUsuarioStr()%>" type="text" id="xIdLocal" size="2" maxlength="2">
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraDetalle">ALIAS</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input name="xAliasUsuario" value="<%=fachadaUsuarioBean.getAliasUsuario()%>" type="text" id="xAliasUsuario" size="5" maxlength="5">
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
