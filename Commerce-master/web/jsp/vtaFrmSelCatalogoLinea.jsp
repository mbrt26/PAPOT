<html>

    <%@ taglib uri="/WEB-INF/tlds/listaLineaNombre" prefix="lista" %>

    <jsp:useBean id="fachadaLineaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaLineaBean" />

    <head>
        <title>Seleccione Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelCatalogoLinea.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">SELECCIONE LINEA</td>
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
                    <td width="33%" align="left" class="letraDetalle">NOMBRE LINEA</td>
                    <td width="34%" align="left" class="letraDetalle">#LINEA</td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
                </tr>
                
                <lista:listaLineaNombre nombreLineaTag="<%=fachadaLineaBean.getNombreLinea()%>">
                    <tr>
                        <td width="33%" align="left" class="letraDetalle">
                         <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelCatalogoLinea.jsp&accionContenedor=Seleccionar&xIdLinea=<%=idLineaVar%>"><%=nombreLineaVar%></a>
                        </td>
                        <td width="34%" align="left" class="letraDetalle"><%=idLineaVar%></td>
                        <td width="33%" class="letraDetalle">&nbsp;</td>
                    </tr>
                </lista:listaLineaNombre>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
            
        </form>
    </body>
</html>