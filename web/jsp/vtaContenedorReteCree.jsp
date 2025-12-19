<html>
    <%@taglib uri="/WEB-INF/tlds/listaRetencion" prefix="lst"%>

    <head>
        <title>Retencion Cree</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorReteCree.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">RETENCION CREE</td>
                    <td colspan="3" width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td colspan="3" width="33%" class="letraTitulo" colspan="2">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <th width="10%" align="center" class="letraTitulo">CODIGO</th>
                    <th width="30%" align="center" class="letraTitulo">ACTIVIDAD<br>ECONOMICA</th>
                    <th width="10%" align="center" class="letraTitulo">PORCENTAJE</th>
                    <th width="10%" align="left" class="letraTitulo">VALOR<br>BASE</br></th>
                </tr>
                
                <tr>
                    <th width="10%" align="center" class="letraDetalle">
                        <input type="text" value="" name="xIdRteCree" size="8" maxlength="8" >
                    </th>
                    <th width="30%" align="center" class="letraDetalle">&nbsp;</th>
                    <th width="10%" align="center" class="letraDetalle">&nbsp;</th>
                    <th width="10%" align="left" class="letraDetalle">&nbsp;</th>
                </tr>
                

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td colspan="3" width="33%" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Traer" name="accionContenedor">
                    </td>
                    <td colspan="9" width="34%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>
    </body>
</html>