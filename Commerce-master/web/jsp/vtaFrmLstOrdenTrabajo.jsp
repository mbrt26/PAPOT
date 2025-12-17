<html>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaSuspendido.tld" prefix="lst" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <head>
        <title>Selecciona Pedido Suspendido</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstOrdenTrabajo.jsp">


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">SELECCIONA PEDIDO SUSPENDIDO</td>
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
                    <td width="5%" align="right" class="letraTitulo">OT</td>
                    <td width="5%" align="center" class="letraTitulo">ITEM</td>
                    <td width="5%" align="right" class="letraTitulo">OC</td>
                    <td width="5%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraTitulo">FICHA</td>
                    <td width="5%" align="right" class="letraTitulo">&nbsp;</td>
                    <td width="30%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
                    <td width="5%" align="center" class="letraTitulo">FEC.PED</td>
                    <td width="1%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="center" class="letraTitulo">FEC.ENT</td>
                    <td width="20%" align="left" class="letraTitulo">REF.CLIENTE</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.PED</td>
                </tr>
                <lst:listaSuspendido idLocalTag = "<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                idTipoOrdenTag = "<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                estadoTag = "<%=fachadaDctoOrdenBean.getEstadoStr()%>"
                idTipoTerceroTag = "<%=fachadaDctoOrdenBean.getIdTipoTerceroStr()%>">
                    <tr>
                        <td width="5%" align="right" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstOrdenTrabajo.jsp&accionContenedor=activaPedido&xIdLocal=<%=fachadaDctoOrdenBean.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>&xIdLog=<%=idLogVar%>"><%=numeroOrdenVar%></a>
                        </td>
                        <td width="5%" align="center" class="letraDetalle"><%=itemVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=ordenCompraVar%></td>
                        <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                        <td width="5%" align="right" class="letraDetalle"><%=idFichaVar%></td>
                        <td width="5%" align="right" class="letraDetalle">&nbsp;</td>
                        <td width="30%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaOrdenVar%></td>
                        <td width="1%" align="center" bgcolor="<%=xColorPedidoVar%>">&nbsp;</td>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaEntregaVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=referenciaClienteVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadDf0Var%></td>
                    </tr>
                </lst:listaSuspendido>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" class="letraTitulo">&nbsp;</td>
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