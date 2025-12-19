<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
    <head>
        <title>Historico Pedidos</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>

    <jsp:useBean id="fachadaAgendaLogVisitaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorEmpresaHRemesa.jsp">
            <input type="hidden" name="xIdCliente" value="<%=fachadaTerceroBean.getIdCliente()%>">
            <input type="hidden" name="xIdTipoTercero" value="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdLocal" value="<%=fachadaTerceroBean.getIdLocalStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">HISTORICO PEDIDOS</td>
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

                <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                                    idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">

                    <tr>
                        <td width="33%" align="center" class="letraDetalle">ESTADO CLIENTE</td>
                        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
                        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                    </tr>
                    <tr>
                        <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
                        <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
                        <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
                    </tr>
                </lista:listaClienteControlAgendaNit>
            </table>

            <table border="0" width="90%" id="tablaTitulo">

                <tr>
                    <td width="33%" align="center" class="letraTitulo">FECHA INICIAL</td>
                    <td width="34%" align="center" class="letraTitulo">FECHA FINAL</td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="text" name="xFechaInicial" size="10" value="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="text" name="xFechaFinal" size="10" value="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Consultar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Listar" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>