
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
</head>

<%@ taglib uri="/WEB-INF/tlds/listaCentroOtro" prefix="lst" %>
<%@ taglib uri="/WEB-INF/tlds/listaTipoOrdenAlcance" prefix="lsu" %>

<jsp:useBean id="fachadaDctoOrdenBean"
             scope="request"
             type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

<html>
    <head>
        <title>Contable Comprobante</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">

        <style type="text/css">@import url("./styles/calendario/calendar-system.css");</style>
        <script type="text/javascript" src="./js/calendario/calendar.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-es.js" ></script>
        <script type="text/javascript" src="./js/calendario/calendar-setup.js" ></script>

    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorContableComprobante.jsp">
            <input type="hidden" name="xIdAlcance" value="<%=fachadaDctoOrdenBean.getIdAlcanceStr()%>">



            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo"><%=fachadaDctoOrdenBean.getTitulo()%></td>
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
                    <td width="20%" align="center" class="letraTitulo">CONCEPTO</td>
                    <td width="20%" align="center" class="letraTitulo">DCTO.REF</td>
                    <td width="20%" align="center" class="letraTitulo">FECHA CUADRE</td>
                    <td width="20%" align="center" class="letraTitulo">TERCERO</td>
                    <td width="20%" align="center" class="letraTitulo">DESCRIPCION</td>
                </tr>
                <tr>
                    <td width="20%" align="center" class="letraDetalle">
                        <select name="xIdTipoOrden">
                            <lsu:listaTipoOrdenAlcance idAlcanceTag="<%=fachadaDctoOrdenBean.getIdAlcanceStr()%>">
                                <option value="<%=idTipoOrdenVar%>">
                                    <%=nombreTipoOrdenVar%>
                                </option>
                            </lsu:listaTipoOrdenAlcance>
                        </select>
                    </td>
                    <td width="20%" align="center" class="letraDetalle">
                        <input type="text" name="xIdDctoNitCC" size="10" value=""  maxlength="10">
                    </td>
                    <td width="20%" align="center" class="letraDetalle">
                        <input type="text" name="xFechaCorte" id="xFechaCorte" readonly="readonly"
                               value="<%=fachadaDctoOrdenBean.getFechaOrdenCorta()%>"/>
                        <img src="./img/img.gif" id="selectorinicial" />
                    </td>
                    <td width="20%" align="center" class="letraDetalle">
                        <jsp:include page="./comboAllTercero.jsp"/>
                    </td>
                    <td width="20%" align="center" class="letraDetalle">
                        <input type="text" name="xDescripcion" size="30" value=""  maxlength="50">
                    </td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                        
                    </td>
                    <td width="34%" align="center" class="letraTitulo">
                        <input type="submit" size="50" value="Crear" name="accionContenedor">
                    </td>
                    <td width="33%" align="center" class="letraTitulo">
                        <input type="submit" value="Listar" name="accionContenedor">
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
