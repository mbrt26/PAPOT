<html>

    <%@ taglib uri="/WEB-INF/tlds/listaMarcaNombre" prefix="lst" %>

    <jsp:useBean id="fachadaMarcaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaMarcaBean" />

    <head>
        <title>Seleccione Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelCatalogoMarca.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">SELECCIONE MARCA</td>
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
                    <td width="33%" align="left" class="letraDetalle">NOMBRE MARCA</td>
                    <td width="34%" align="left" class="letraDetalle">#MARCA</td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>

                <lst:listaMarcaNombre nombreMarcaTag="<%=fachadaMarcaBean.getNombreMarca()%>">
                    <tr>
                        <td width="33%" align="left" class="letraDetalle">
                         <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelCatalogoMarca.jsp&accionContenedor=Seleccionar&xIdMarca=<%=idMarcaVar%>"><%=nombreMarcaVar%></a>
                        </td>
                        <td width="34%" align="left" class="letraDetalle"><%=idMarcaVar%></td>
                        <td width="33%" class="letraDetalle">&nbsp;</td>
                    </tr>
                </lst:listaMarcaNombre>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>