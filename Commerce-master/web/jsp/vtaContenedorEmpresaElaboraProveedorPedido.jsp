
<html>

    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <%@ taglib uri="/WEB-INF/tlds/listaLinea" prefix="comboLinea" %>

    <%@ page import="com.solucionesweb.losbeans.utilidades.Day" %>
    <%@ page import="com.solucionesweb.losbeans.negocio.UsuarioBean" %>
    <%@ page import="com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean" %>
    <%@ page import="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" %>

    <%@ taglib uri="/WEB-INF/tlds/listaClienteCotizacionArticulos" prefix="listaArticulos" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteCotizacion" prefix="listaCotizacion" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lista" %>

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

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
    %>

    <head>
        <title>Elaborar Pedido</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">ELABORAR COMPRA</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>

                <lista:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                                                    idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle">ESTADO PROVEEDOR</td>
                        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
                        <td width="33%" align="center" class="letraDetalle">NOMBRE PROVEEDOR</td>
                    </tr>

                    <tr>
                        <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
                        <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
                        <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
                    </tr>
                </lista:listaClienteControlAgendaNit>

                <tr>
                    <td width="33%" align="center" class="letraTitulo">#ARTICULOS</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" align="right" class="letraTitulo">$VR.TOTAL</td>
                </tr>

                <listaCotizacion:listaClienteCotizacion idLogTag = "<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                                        idClienteTag = "<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                                        idSucursalTag = "<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>"
                                                        idUsuarioTag = "<%=fachadaAgendaLogVisitaBean.getIdUsuarioStr()%>">

                    <tr>
                        <td width="33%" align="center" class="letraDetalle"><%=cantidadArticulosVar%></td>
                        <td width="34%" align="center" class="letraDetalle">&nbsp;</td>
                        <td width="33%" align="right" class="letraDetalle"><%=vrCostoConIvaVar%></td>
                    </tr>

                </listaCotizacion:listaClienteCotizacion>


            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="right" class="letraTitulo">
                        <input type="text" name="idLinea" id="idLinea" size="30" tabindex="1" maxlength="30">
                    </td>
                    <td width="50%" align="left"  class="letraTitulo">
                        <input type="submit" value="+Productos" name="accionContenedor">
                    </td>
                </tr>
            </table>

            <script type="text/javascript">
                document.getElementById('idLinea').focus();
            </script>
            
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="10%" class="letraTitulo">&nbsp;</td>
                    <td width="10%" nowrap align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="30%" align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    <td width="10%" align="right" class="letraTitulo">COSTO NUEVO</td>
                    <td width="10%" align="right" class="letraTitulo">COSTO ACTUAL</td>
                    <td width="10%" nowrap align="right" class="letraTitulo">%INC</td>
                    <td width="10%" nowrap align="right" class="letraTitulo">CANT</td>
                    <td width="10%" nowrap align="right" class="letraTitulo">SUBTOTAL</td>
                </tr>

                <listaArticulos:listaClienteCotizacionArticulos idLogTag="<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>"
                                                                idClienteTag="<%=fachadaAgendaLogVisitaBean.getIdCliente()%>"
                                                                idSucursalTag="<%=fachadaAgendaLogVisitaBean.getStrIdSucursal()%>"
                                                                idUsuarioTag="<%=fachadaAgendaLogVisitaBean.getIdUsuarioStr()%>"
                                                                idTipoOrdenTag="<%=fachadaTerceroBean.getIdTipoOrdenStr()%>">

                    <tr>
                        <td width="10%"  nowrap align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp&accionContenedor=Retirar&item=<%=itemVar%>&idLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">R</a>
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp&accionContenedor=ModificarItem&item=<%=itemVar%>&idLog=<%=fachadaAgendaLogVisitaBean.getIdLogStr()%>">M</a>
                        </td>
                        <td width="10%" nowrap align="left" class="letraDetalle"><%=strIdReferenciaVar%></td>
                        <td width="30%" align="left" class="letraDetalle"><%=nombrePluVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=vrCostoVar%></td>
                        <td width="10%" align="right" class="letraDetalle"><%=vrCostoActualVar%></td>
                        <td width="10%" nowrap align="right" class="letraDetalle"><%=incrementoCostoVar%></td>
                        <td width="10%" nowrap align="right" class="letraDetalle"><%=cantidadVar%></td>
                        <td width="10%" nowrap align="right" class="letraDetalle"><%=vrCostoConIvaVar%></td>

                    </tr>

                </listaArticulos:listaClienteCotizacionArticulos>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="right" class="letraTitulo">
                        <input type="submit" value="Finalizar" name="accionContenedor">
                    </td>
                    <td width="50%" align="left" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
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