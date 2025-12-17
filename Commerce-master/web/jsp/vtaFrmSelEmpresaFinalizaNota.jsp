<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ page import="com.solucionesweb.losbeans.utilidades.Day" %>
    <%@ page import="com.solucionesweb.losbeans.negocio.UsuarioBean" %>
    <%@ page import="com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean" %>
    <%@ page import="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" %>

    <%@ taglib uri="/WEB-INF/tlds/listaClienteCotizacionArticulos" prefix="lss" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOrdenLog" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <jsp:useBean id="fachadaDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoBean" />

    <%
        String strIdSucursal = "--";
        double idCliente = -1;
        int estadoActivo = 9;
        Day day = new Day();
        String strFechaVisita = day.getFechaFormateada();
    %>

    <%
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");
        String idUsuario = usuarioBean.getIdUsuarioStr();

        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
        agendaLogVisitaBean.setEstado(estadoActivo);
        agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(idUsuario);

        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.seleccionaVisitaEstadoFecha();
        String xIdBodega = "1";
    %>

    <head>
        <title>Nota Credito</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest"
                   value="/jsp/vtaFrmSelEmpresaFinalizaNota.jsp">
            <input type="hidden" name="xIdLog" value="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>">
            <input type="hidden" name="xIdDcto" value="<%=fachadaDctoBean.getIdDctoSf0()%>">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoBean.getIdLocalStr()%>">
            <input type="hidden" name="xIndicador" value="<%=fachadaDctoBean.getIndicadorStr()%>">
            <input type="hidden" name="xIdOrden" value="<%=fachadaDctoBean.getIdOrdenStr()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">NOTA CREDITO</td>
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

                <tr>
                    <td width="33%" align="right" class="letraTitulo">#ARTICULOS</td>
                    <td width="34%" align="right" class="letraTitulo">$VR.BASE</td>
                    <td width="33%" align="right" class="letraTitulo">$VR.TOTAL</td>
                </tr>
                <lst:listaOrdenLog idLocalTag = "<%=fachadaDctoBean.getIdLocalStr()%>"
                                   idLogTag = "<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                   idTipoOrdenTag = "<%=fachadaTerceroBean.getIdTipoOrdenStr()%>">

                    <tr>
                        <td width="33%" align="right" class="letraDetalle"><%=cantidadArticulosVar%></td>
                        <td width="34%" align="right" class="letraDetalle"><%=vrVentaSinIvaVar%></td>
                        <td width="33%" align="right" class="letraDetalle"><%=vrVentaConIvaVar%></td>
                    </tr>

                </lst:listaOrdenLog>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="10%" class="letraTitulo">&nbsp;</td>
                    <td width="10%" nowrap align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="20%" nowrap align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    <td width="10%" nowrap align="left" class="letraTitulo">MARCA</td>
                    <td width="20%" nowrap align="right" class="letraTitulo">VR.VENTA</td>
                    <td width="20%" nowrap align="right" class="letraTitulo">CANTIDAD</td>
                    <td width="15%" nowrap align="right" class="letraTitulo">SUBTOTAL</td>
                </tr>
                <lss:listaClienteCotizacionArticulos idLocalTag="<%=fachadaDctoBean.getIdLocalStr()%>"
                                                     idTipoOrdenTag="<%=fachadaDctoBean.getIdTipoOrdenStr()%>"
                                                     idLogTag="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                                     idBodegaTag = "<%=xIdBodega%>">

                    <tr>
                        <td width="10%"  nowrap align="center" class="letraDetalle">&nbsp;</td>
                        <td width="10%" nowrap align="left" class="letraDetalle"><%=strIdReferenciaVar%></td>
                        <td width="20%" nowrap align="left" class="letraDetalle"><%=nombrePluVar%></td>
                        <td width="10%" nowrap align="left" class="letraDetalle"><%=nombreMarcaVar%></td>
                        <td width="20%" nowrap align="right" class="letraDetalle"><%=vrVentaUnitarioVar%></td>
                        <td width="20%" nowrap align="right" class="letraDetalle"><%=cantidadVar%></td>
                        <td width="15%" nowrap align="right" class="letraDetalle"><%=vrVentaSinIvaVar%></td>
                    </tr>

                </lss:listaClienteCotizacionArticulos>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Finalizar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="25%" align="right" class="letraTitulo">CAUSA</td>
                    <td width="25%" align="left" class="letraTitulo">
                    <jsp:include page="./comboCausaNota.jsp"/></td>
                </tr>
            </table>
        </form>

    </body>

</html>