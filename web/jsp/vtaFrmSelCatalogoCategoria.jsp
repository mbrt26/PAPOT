<html>
    <%@ taglib uri="/WEB-INF/tlds/listaUnCategoria" prefix="lst" %>

    <jsp:useBean id="fachadaLineaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaLineaBean" />

    <head>
        <title>Catalogo Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelCatalogoCategoria.jsp">
            <input type="hidden" name="xIdLinea" value="<%=fachadaLineaBean.getIdLineaStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">LINEA <%=fachadaLineaBean.getNombreLinea()%></td>
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
                    <td width="33%" class="letraDetalle">NOMBRE CATEGORIA</td>
                    <td width="34%" class="letraDetalle">#CATEGORIA</td>
                    <td width="%" class="letraDetalle">#LINEA</td>
                </tr>
                
                <lst:listaUnCategoria idLineaTag="<%=fachadaLineaBean.getIdLineaStr()%>">


                    <tr>
                        <td width="33%" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelCatalogoCategoria.jsp&accionContenedor=Seleccionar&xIdLinea=<%=idLineaVar%>&xIdCategoria=<%=idCategoriaVar%>"><%=nombreCategoriaVar%></a>

                            </td>
                        <td width="34%" class="letraDetalle"><%=idCategoriaVar%></td>
                        <td width="%" class="letraDetalle"><%=idLineaVar%></td>
                    </tr>

                </lst:listaUnCategoria>

            </table>


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">NOMBRE CATEGORIA</td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="text" name="xNombreCategoria" value="" size="50">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Ingresar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>
