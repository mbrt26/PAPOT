<html>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <%@ taglib uri="/WEB-INF/tlds/listaEmpresaNombre.tld" prefix="lista" %>

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <jsp:useBean id="fachadaPagoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPagoBean" />

    <head>
        <title>Selecciona Proveedor</title>
        <link href="/Commerce/styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="/Commerce/styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelPagoCxPPlanilla.jsp">
            <input type="hidden" name="xIdVendedor" value="<%=fachadaPagoBean.getIdVendedorDi0()%>">
            <input type="hidden" name="xFechaPago" value="<%=fachadaPagoBean.getFechaPagoCorta()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">SELECCIONA PROVEEDOR</td>
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
                    <td width="33%" align="center" class="letraTitulo">
                        <a href="javascript:window.history.back()"><img src="./img/ARW06LT.GIF" width="32" height="32"></a>
                    </td>
                    <td width="34%" align="left" class="letraTitulo">&nbsp;</td>
                    <td width="33%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="10%" align="left" class="letraDetalle">CODIGO</td>
                    <td width="5%" align="center" class="letraDetalle">LOCAL</td>
                    <td width="25%" align="left" class="letraDetalle">NOMBRE PROVEEDOR</td>
                    <td width="25%" align="left" class="letraDetalle">NOMBRE EMPRESA</td>
                    <td width="20%" align="left" class="letraDetalle">CIUDAD</td>
                    <td width="10%" align="left" class="letraDetalle">TELEFONO</td>
                </tr>

                <lista:listaEmpresaNombre idTipoTerceroTag = "<%=fachadaTerceroBean.getIdTipoTerceroStr()%>"
                                          idUsuarioTag = "<%=fachadaPagoBean.getIdUsuarioStr()%>"
                                          nombreTerceroTag = "<%=fachadaTerceroBean.getNombreTercero()%>">
                    <tr>
                        <td width="10%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelPagoCxPPlanilla.jsp&accionContenedor=Confirmar&xIdCliente=<%=idClienteSinFormatoVar%>&xIdVendedor=<%=fachadaPagoBean.getIdVendedorDi0()%>&xFechaPago=<%=fachadaPagoBean.getFechaPagoCorta()%>&xIdLocalTercero=<%=idLocalTerceroVar%>"><%=idClienteVar%></a>
                        </td>
                        <td width="5%" align="center" class="letraDetalle"><%=idLocalTerceroVar%>
                        <td width="25%" align="left" class="letraDetalle"><%=nombreTerceroVar%>
                        <td width="25%" align="left" class="letraDetalle"><%=nombreEmpresaVar%>
                        <td width="20%" align="left" class="letraDetalle"><%=ciudadTerceroVar%>
                        <td width="10%" align="left" class="letraDetalle"><%=telefonoFijoVar%>

                        </td>
                    </tr>
                </lista:listaEmpresaNombre>
            </table>


            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
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