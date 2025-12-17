<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<html>
    <head>
        <title>Resurtido Traslado</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">


        <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>



    </head>
    <%@ taglib uri="/WEB-INF/tlds/listaCentroOtro" prefix="lst" %>
    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorResurtidoCompra.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">RESURTIDO COMPRA</td>
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
                    <td width="20%" align="center" class="letraTitulo">#DIAS HISTORIA</td>
                    <td width="35%" align="center" class="letraTitulo">FECHA CORTE</td>
                    <td width="20%" align="center" class="letraTitulo">#DIAS INVENTARIO</td>
                    <td width="25%" align="center" class="letraTitulo">PROVEEDOR</td>
                </tr>

                <tr>
                    <td width="20%" align="center" class="letraDetalle">
                        <input type="text" name="xDiasHistoria" size="4" value="<%=fachadaDctoOrdenBean.getDiasHistoriaStr()%>" maxlength="4">
                    </td>
                    <td width="35%" align="center" class="letraDetalle">
                    <input type="text" name="xFechaCorte" id="xFechaCorte" readonly="readonly" style="width:30%"
                            value="<%=fachadaDctoOrdenBean.getFechaOrdenCorta()%>"/>
                    <img src="./img/img.gif" id="selectorinicial" />
                    </td>

                    <td width="20%" align="center" class="letraDetalle">
                        <input type="text" name="xDiasInventario" size="4" value="<%=fachadaDctoOrdenBean.getDiasInventario()%>"  maxlength="4">
                    </td>
                    <td width="25%" align="center" class="letraDetalle">
                        <jsp:include page="./comboTercero.jsp"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Crear" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Traer" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Listar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
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
    inputField: "xFechaCorte",
    ifFormat:   "%Y/%m/%d",
    button:     "selectorinicial",
    date: new Date()
 }
);

</script>
