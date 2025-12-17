<html>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaCotizacion.tld" prefix="lst" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <head>
        <title>Selecciona Cotizacion</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorClienteCotiza.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">SELECCIONA COTIZACION</td>
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


            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="10%" align="left" class="letraDetalle">CODIGO</td>
                    <td width="25%" align="left" class="letraDetalle">NOMBRE CLIENTE</td>
                    <td width="10%" align="left" class="letraDetalle">FEC.PEDIDO</td>
                    <td width="25%" align="left" class="letraDetalle">NOMBRE EMPRESA</td>
                    <td width="25%" align="left" class="letraDetalle">VENDEDOR</td>
                </tr>

                <lst:listaCotizacion idLocalTag = "<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                                     idTipoOrdenTag = "<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                                     fechaVisitaTag = "<%=fachadaDctoOrdenBean.getFechaOrden()%>"
                                     estadoTag = "<%=fachadaDctoOrdenBean.getEstadoStr()%>">
                    <tr>
                        <td width="10%" align="left" class="letraDetalle">
                            <input type="radio" name="xIdLog" value="<%=idLogVar%>"><%=idOrdenVar%>
                        </td>
                        <td width="25%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                        <td width="10%" align="left" class="letraDetalle"><%=fechaVisitaCortaVar%></td>
                        <td width="25%" align="left" class="letraDetalle"><%=nombreEmpresaVar%></td>
                        <td width="25%" align="left" class="letraDetalle"><%=nombreUsuarioVar%></td>
                    </tr>
                </lst:listaCotizacion>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" class="letraTitulo">
                        <input type="submit" value="Listar" name="accionContenedor">
                    </td>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Pedido" name="accionContenedor">
                    </td>
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