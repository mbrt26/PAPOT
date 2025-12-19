<html>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaOperacion" prefix="lsv" %>

    <head>
        <title>Ficha Tecnica Cliente</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorFichaTecnica.jsp">
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">FICHA TECNICA CLIENTE</td>
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
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">OPERACION</td>
                    <td width="33%" align="left" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;
                       <!--select name='xIdOperacion'>
                            <lsv:listaOperacion>
                                <option value="<%=idOperacionVar%>">
                                    <%=nombreOperacionVar%>
                                </option>
                            </lsv:listaOperacion>
                        </select-->
                    </td>
                    <td width="33%" align="left" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%"  align="center" class="letraDetalle">#PEDIDO
                        <input type="text" name="xNumeroOrden" id="xNumeroOrden" value="" size="6" maxlength="6">
                        <input type="submit" value="Consultar Pedido" name="accionContenedor">
                    </td>
                    <!--td width="34%"  align="center" class="letraDetalle">#FICHA
                        <input type="text" name="xIdFicha" id="xIdFicha" value="" size="6" maxlength="6">
                        <input type="submit" value="Consultar Ficha" name="accionContenedor">
                    </td-->
                    <td width="33%" align="center" class="letraDetalle">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>
            </table>

            <script type="text/javascript">
                document.getElementById('xNumeroOrden').focus();
            </script>

        </form>
    </body>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

</html>