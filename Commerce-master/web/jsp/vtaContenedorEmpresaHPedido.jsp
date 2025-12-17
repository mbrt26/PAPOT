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

        <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>


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
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorEmpresaHPedido.jsp">
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
                        <input type="text" name="xFechaInicial" id="fechainicial" readonly="readonly"
                               value="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"/>
                        <img src="./img/img.gif" id="selectorinicial" />
                    </td>

                    

                    <td width="34%" align="center" class="letraTitulo">
                        <input type="text" name="xFechaFinal" id="fechafinal" readonly="readonly"
                               value="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>"/>
                        <img src="./img/img.gif" id="selectorfinal" />
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


<script type="text/javascript">
    Calendar.setup(
    {
        inputField: "fechainicial",
        ifFormat:   "%Y/%m/%d",
        button:     "selectorinicial",
        date: new Date()
    }
);
    Calendar.setup(
    {
        inputField: "fechafinal",
        ifFormat:   "%Y/%m/%d",
        button:     "selectorfinal",
        date: new Date()
    }
);
</script>