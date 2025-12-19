<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoLocal" prefix="lst" %>
    <head>
        <title>Resurtido Orden Compra</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">


        <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>
    </head>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorOrdenCompra.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">RESURTIDO ORDEN COMPRA</td>
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
                    <td width="33%" align="left" class="letraDetalle">#DIAS HISTORIA</td>
                    <td width="34" align="left" class="letraDetalle">
                        <input type="text" name="xDiasHistoria" size="4" value="<%=fachadaDctoOrdenBean.getDiasHistoriaStr()%>" maxlength="4">
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" align="left" class="letraDetalle">FECHA CORTE</td>
                    <td width="34%" align="left" class="letraDetalle">
                        <input type="text" name="xFechaCorte" id="xFechaCorte" readonly="readonly" style="width:30%"
                            value="<%=fachadaDctoOrdenBean.getFechaOrdenCorta()%>"/>
                    <img src="./img/img.gif" id="selectorinicial" />
                    </td>
                    <td width="33%" align="left" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" value="Consolidar" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Listar" name="accionContenedor">
                    </td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="10%" align="center" class="letraTitulo">#ORDEN</td>
                    <td width="10" align="center" class="letraTitulo">FECHA</td>
                    <td width="10" align="center" class="letraTitulo">IDLOCAL</td>
                    <td width="30" align="left" class="letraTitulo">NOMBRE LOCAL</td>
                    <td width="10" align="center" class="letraTitulo">#PLUS</td>
                </tr>
                <lst:listaResurtidoLocal>
                    <tr>
                        <td width="10%" align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorOrdenCompra.jsp&accionContenedor=trae&xIdLocal=<%=idLocalVar%>&xIdLog=<%=idLogVar%>&xDiasHistoria=<%=fachadaDctoOrdenBean.getDiasHistoriaStr()%>"><%=idLogVar%></a>
                        </td>
                        <td width="10%" align="center" class="letraDetalle"><%=fechaOrdenVar%></td>
                        <td width="10%" align="center" class="letraDetalle"><%=idLocalVar%></td>
                        <td width="30%" align="left" class="letraDetalle"><%=nombreLocalVar%></td>
                        <td width="10%" align="center" class="letraDetalle"><%=cantidadArticulosVar%></td>
                    </tr>
                </lst:listaResurtidoLocal>             
            </table>
        </form>
    </body>
</html>

<script type="text/javascript">
  Calendar.setup(
  {
    inputField: "xFechaCorte",
    ifFormat:   "%Y/%m/%d",
    button:     "selectorinicial",
    date: new Date()
 }
);

</script>
