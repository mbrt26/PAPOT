<html>

    <%@ taglib uri="/WEB-INF/tlds/seleccionaTerceroxIdTipoTercero" prefix="lista" %>
    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <head>
        <title>Catalogo Cliente</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelCatalogoProveedor.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">LISTA PROVEEDOR</td>
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
                <td width="40%" align="left" class="letraDetalle">NOMBRE</td>
                <td width="10%" align="right" class="letraDetalle">NIT/CC</td>
                <td width="20%" align="left" class="letraDetalle">DIRECCION</td>
                <td width="10%" align="left" class="letraDetalle">TEL.FIJO</td>
                <td width="10%" align="left" class="letraDetalle">TEL.CELULAR</td>
                <td width="10%" align="left" class="letraDetalle">TEL.FAX</td>
            </tr>
            <lista:seleccionaTerceroxIdTipoTercero nombreTerceroTag="<%=fachadaTerceroBean.getNombreTercero()%>"
                                                   idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">

                <tr>
                    <td width="40%" align="left" class="letraDetalle">
                        <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelCatalogoProveedor.jsp&accionContenedor=Seleccionar&xIdTipoTercero=<%=fachadaTerceroBean.getIdTipoTerceroStr()%>&xIdCliente=<%=idClienteVar%>"><%=nombreTerceroVar%></a>
                    </td>
                    <td width="10%" align="right" class="letraDetalle"><%=idTerceroFormatVar%></td>
                    <td width="20%" align="left" class="letraDetalle"><%=direccionTerceroVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=telefonoFijoVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=telefonoCelularVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=telefonoFaxVar%></td>
                </tr>

            </lista:seleccionaTerceroxIdTipoTercero>

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