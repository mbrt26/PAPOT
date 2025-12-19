<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaResurtidoPeriodo" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean" />

    <head>
        <title>Lista Despacho</title>
    </head>
    <body>

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">LISTA DESPACHO</td>
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
            <table border="0" width="90%" id="tablaTitulo">

                <tr>
                    <td width="100%" align="center" class="letraTitulo">BODEGA ORIGEN</td>
                </tr>

                <tr>
                    <td width="10025%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>

            </table>
        </table>

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="5%" align="right" class="letraTitulo">#DCTO</td>
                <td width="10%" align="center" class="letraTitulo">NIT/CC</td>
                <td width="10%" align="center" class="letraTitulo">F.ORDEN</td>
                <td width="10%" align="left" class="letraTitulo">C.O.</td>
                <td width="10%" align="right" class="letraTitulo">V.COSTO</td>
                <td width="10%" align="right" class="letraTitulo">#ARTIC</td>
                <td width="15%" align="left" class="letraTitulo">USUARIO</td>
            </tr>

            <lst:listaResurtidoPeriodo idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                             idTipoOrdenCadenaTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenCadena()%>"
                              indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                                indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
                                    idVendedorTag="<%=fachadaColaboraReporteDctoBean.getIdVendedorStr()%>"
                                  fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicial()%>"
                                    fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinal()%>">

                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=idDctoVar%></td>
                    <td width="10%" align="center" class="letraDetalle"><%=idTerceroVar%></td>
                    <td width="10%" align="center" class="letraDetalle"><%=fechaOrdenVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="10%" align="right" class="letraDetalle"><%=vrCostoMVVar%></td>
                    <td width="10%" align="right" class="letraDetalle"><%=cantidadArticulosVar%></td>
                    <td width="15%" align="left" class="letraDetalle"><%=nombreUsuarioVar%></td>
                </tr>
            </lst:listaResurtidoPeriodo>
        </table>
    </body>
</html>
</html>