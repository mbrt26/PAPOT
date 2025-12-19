<html>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaAllClienteOT.tld" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacion.tld" prefix="lsu" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />
    <head>
        <title>Selecciona Orden Trabajo</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorOrdenTrabajoActivo.jsp">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">SELECCIONA ORDEN TRABAJO</td>
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
                    <td width="33%" align="center" class="letraTitulo">OPERACION</td>
                    <td width="33%" align="center" class="letraTitulo">NOMBRE CLIENTE (+)</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraDetalle">
                        <select name='xIdOperacion'>
                            <lsu:listaOperacion>
                                <option value="<%=idOperacionVar%>">
                                    <%=nombreOperacionVar%>
                                </option>
                            </lsu:listaOperacion>
                        </select>
                    </td>
                    <td width="34%" align="center" class="letraDetalle">
                        <select name='xIdCliente'>
                            <lst:listaAllClienteOT idLocalTag = "<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                            idTipoOrdenTag = "<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                            estadoTag = "<%=fachadaDctoOrdenBean.getEstadoStr()%>"
                            idTipoTerceroTag = "<%=fachadaDctoOrdenBean.getIdTipoTerceroStr()%>">
                                <option value="<%=idClienteVar%>">
                                    <%=nombreTerceroVar%>
                                </option>
                            </lst:listaAllClienteOT>
                        </select>
                    </td>
                    <td width="33%" class="letraDetalle">&nbsp;</td>
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
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" class="letraTitulo">
                        <input type="submit" value="Consultar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

</html>