<html>
    <%@ taglib uri="/WEB-INF/tlds/listaOTProgresoCliente.tld" prefix="lst" %>

    <jsp:useBean id="jhDate" scope="page" class="com.solucionesweb.losbeans.utilidades.JhDate">
        <jsp:setProperty name="jhDate" property="*" />
    </jsp:useBean>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />

    <% String xIdOperacionCadena = "8,9,999"; %>

    <head>
        <title>Elabora Factura</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngFacturaFacturaRemision.jsp">
            <input type="hidden" name="xIdCliente" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">ELABORA FACTURA</td>
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
                    <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraResaltadaTituloGrande">
                        <%=fachadaTerceroBean.getNombreTercero()%>
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
            <table border="0" width="90%" id="tablaDetalle">
                <tr>
                    <td width="1%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="5%" align="right" class="letraTitulo">OT</td>
                    <td width="5%" align="right" class="letraTitulo">ITEM</td>
                    <td width="5%" align="right" class="letraTitulo">OC</td>
                    <td width="5%" align="right" class="letraTitulo">FICHA</td>
                    <td width="30%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
                    <td width="5%" align="center" class="letraTitulo">FEC.ENT</td>
                    <td width="5%" align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.FRA</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.TER</td>
                    <td width="5%" align="right" class="letraTitulo">CAN.PEN</td>
                    <td width="1%" align="right" class="letraTitulo">RET</td>
                </tr>
                <lst:listaOTProgresoCliente idLocalTag = "<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                idTipoOrdenTag = "<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                estadoTag = "<%=fachadaDctoOrdenBean.getEstadoStr()%>"
                idTipoTerceroTag = "<%=fachadaDctoOrdenBean.getIdTipoTerceroStr()%>"
                idOperacionTag = "<%=fachadaDctoOrdenBean.getIdOperacionStr()%>"
                idClienteTag = "<%=fachadaDctoOrdenBean.getIdCliente()%>">

                    <tr>
                        <td width="1%" align="center" class="letraDetalle">
                            <input type="checkbox" name="xLocalTipoOrdenOrdenItem" 
                           value="<%=idLocalVar%>~<%=idTipoOrdenVar%>~<%=idOrdenVar%>~<%=itemVar%>~<%=cantidadTerminadaStrVar%>~<%=idOperacionVar%>~<%=tipoAlcance%>"/>
                        </td>
                        <td width="5%" align="right" class="letraDetalle"><%=numeroOrdenVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=itemVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=ordenCompraVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=idFichaVar%></td>
                        <td width="30%" align="left" class="letraDetalle"><%=referenciaClienteVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=fechaEntregaVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=referenciaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadFacturadaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaVar%></td>
                        <td width="5%" align="right" class="letraDetalle"><%=cantidadPendienteFacturaVar%></td>
                        <td width="5%" align="center" class="letraDetalle"><%=tipoAlcance%></td>
                       
                    </tr>
                </lst:listaOTProgresoCliente>
            </table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Trae Factura" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
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