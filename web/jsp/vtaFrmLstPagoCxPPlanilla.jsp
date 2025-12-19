<html>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

<%@ taglib uri="/WEB-INF/tlds/listaPlanillaVendedor" prefix="lst" %>

    <jsp:useBean id="fachadaUsuarioBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaUsuarioBean" />

    <jsp:useBean id="fachadaPagoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPagoBean" />

    <head>
        <title>Planillas de Pagos</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmLstPagoCxPPlanilla.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">PLANILLAS DE PAGOS</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboLocal.jsp"/>
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraDetalle">VENDEDOR</td>
                    <td width="34%" align="center" class="letraDetalle">ALIAS VENDEDOR</td>
                    <td width="33%" align="center" class="letraDetalle">FECHA PAGO</td>
                </tr>

                <tr>
                    <td width="33%" align="center" class="letraDetalle">
                        <%=fachadaUsuarioBean.getNombreUsuario()%>
                    </td>
                    <td width="34%" align="center" class="letraDetalle">
                        <%=fachadaUsuarioBean.getAliasUsuario()%>
                    </td>
                    <td width="33%" align="center" class="letraDetalle"><%=fachadaPagoBean.getFechaPagoCorta()%></td>
                </tr>
            </table>

<table border="0" width="90%" id="tablaTitulo">
    <tr>
        <td width="10%" align="center" class="letraTitulo">#PLANILLA</td>
        <td width="10%" align="center" class="letraTitulo">F.PAGO</td>
        <td width="10%" align="right" class="letraTitulo">#DCTOS</td>
        <td width="10%" align="right" class="letraTitulo">V.RECB</td>
        <td width="10%" align="right" class="letraTitulo">V.DSCTO</td>
        <td width="10%" align="right" class="letraTitulo">V.RFTE</td>
        <td width="10%" align="right" class="letraTitulo">V.RIVA</td>
        <td width="10%" align="right" class="letraTitulo">V.RICA</td>
        <td width="8%" align="left" class="letraTitulo">VENDEDOR</td>
        <td width="2%" align="right" class="letraTitulo">&nbsp;</td>
    </tr>
    <lst:listaPlanillaVendedor idLocalTag="<%=fachadaPagoBean.getIdLocalStr()%>"
                               idTipoOrdenTag="<%=fachadaPagoBean.getIdTipoOrdenStr()%>"
                               idVendedorTag="<%=fachadaPagoBean.getIdVendedorStr()%>"
                               fechaPagoTag= "<%=fachadaPagoBean.getFechaPago()%>">

        <tr>
            <td width="10%" align="center" class="letraDetalle">
                <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmLstPagoCxPPlanilla.jsp&accionContenedor=listaPlanilla&xIdLocal=<%=fachadaPagoBean.getIdLocalStr()%>&xIdTipoOrden=<%=fachadaPagoBean.getIdTipoOrdenStr()%>&xIdPlanilla=<%=idPlanillaVar%>"><%=idPlanillaVar%></a>
                </td>
            <td width="10%" align="center" class="letraDetalle"><%=fechaPagoVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=idDctoVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrPagoVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrDescuentoVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrRteFuenteVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrRteIvaVar%></td>
            <td width="10%" align="right" class="letraDetalle"><%=vrRteIcaVar%></td>
            <td width="8%" align="left" class="letraDetalle"><%=aliasUsuarioVar%></td>
            <td width="2%" align="center" class="letraDetalle">&nbsp;</td>
        </tr>
    </lst:listaPlanillaVendedor>

</table>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">
                        <input type="submit" value="Regresar" name="accionContenedor">
                    </td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>