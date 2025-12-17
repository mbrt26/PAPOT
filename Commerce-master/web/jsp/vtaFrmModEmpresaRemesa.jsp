<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>

    <jsp:useBean id="fachadaDctoOrdenDetalleBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />

    <jsp:useBean id="fachadaAgendaLogVisitaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />


    <head>
        <title>Modificar Cantidad</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmModEmpresaRemesa.jsp">
            <input type="hidden" name="xItem" value="<%=fachadaDctoOrdenDetalleBean.getItemStr()%>">
            <input type="hidden" name="xIdLog" value="<%=fachadaDctoOrdenDetalleBean.getIdLogStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MODIFICAR REFERENCIA</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>

                <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
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
                </lst:listaClienteControlAgendaNit>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="center" class="letraTitulo">REFERENCIA</td>
                    <td width="50%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                </tr>

                <tr>
                    <td width="50%" align="center" class="letraDetalle">
                        <%=fachadaDctoOrdenDetalleBean.getIdPluStr()%>
                    </td>
                    <td width="50%" align="left" class="letraDetalle">
                        <%=fachadaDctoOrdenDetalleBean.getNombrePlu()%>
            </table>


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">NUEVA CANTIDAD</td>
                    <td width="34%" align="center" class="letraTitulo">NUEVO VR.UNITARIO</td>
                    <td width="33%" align="center" class="letraTitulo">%DESCUENTO</td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraDetalle">
                        <input name="cantidad" size="20"  value="<%=fachadaDctoOrdenDetalleBean.getCantidadStr()%>">
                    </td>
                    <td width="34%" align="center" class="letraDetalle">
                        <input name="vrVentaUnitario" size="20" value="<%=fachadaDctoOrdenDetalleBean.getVrVentaUnitarioSf0()%>">
                    </td>
                    <td width="33%" align="center" class="letraDetalle">
                        <input name="xPorcentajeDescuento" size="20" value="<%=fachadaDctoOrdenDetalleBean.getPorcentajeDsctoStr()%>"></td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="right" class="letraTitulo">
                        <input type="submit" value="Confirmar Cantidad" name="accionContenedor">
                    </td>
                    <td width="50%" align="left" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
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