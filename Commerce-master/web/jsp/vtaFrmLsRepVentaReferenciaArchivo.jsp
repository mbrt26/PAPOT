<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaVentaReferencia" prefix="lst" %>

    <jsp:useBean id="fachadaColaboraReporteDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoModBean" />

    <head>
        <title>Ventas x Referencias</title>
    </head>
    <body>
        
        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="34%" align="center"  colspan="10" class="letraTitulo">VENTAS X REFERENCIAS</td>
            </tr>
            <tr>
                <td width="33%" colspan="10" align="center" class="letraTitulo">
                    <jsp:include page="./comboLocal.jsp"/>
                </td>
                <!--td width="34%" align="center" class="letraTitulo">&nbsp;</td-->
                <!--td width="33%" class="letraTitulo">
                    <!--jsp:include page="./comboFechaHoy.jsp"/>
                </td-->
            </tr>

            <tr>
                <!--td width="33%" align="center" class="letraTitulo">&nbsp;</td-->
                <td width="34%" align="center"  colspan="10" class="letraTitulo">DEL <%=fachadaColaboraReporteDctoBean.getFechaInicial()%>
                AL <%=fachadaColaboraReporteDctoBean.getFechaFinal()%></td>
                <!--td width="33%" align="center" class="letraTitulo">&nbsp;</td-->
            </tr>
            <tr>
                <td width="33%" colspan="10" class="letraTitulo">
                    <jsp:include page="./comboFechaHoy.jsp"/>
                </td>
                
            </tr>
            <tr>
                <td width="33%" colspan="10" align="center" class="letraTitulo">&nbsp;</td>
            </tr>
        </table>

        <table border="0" width="90%" id="tablaTitulo">
            <tr bgcolor="#87CEFA">
                <td width="5%" align="center" class="letraTitulo"><b>CODIGO</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>REFERENCIA</b></td>
                <td width="30%" align="center" class="letraTitulo"><b>NOMBRE CLIENTE</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>F.FRA</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>DCTO</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>VR.BASE</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>PESO.FACT</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>CANT.FACT</b></td>
                <td width="10%" align="left" class="letraTitulo"><b>NEG</b></td>
                <td width="5%" align="left" class="letraTitulo"><b>USR</b></td>

            </tr>

            <lst:listaVentaReferencia
                            idLocalTag="<%=fachadaColaboraReporteDctoBean.getIdLocalStr()%>"
                            idTipoOrdenCadenaTag="<%=fachadaColaboraReporteDctoBean.getIdTipoOrdenCadena()%>"
                            indicadorInicialTag="<%=fachadaColaboraReporteDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaColaboraReporteDctoBean.getIndicadorFinalStr()%>"
                            idVendedorTag="<%=fachadaColaboraReporteDctoBean.getIdVendedorStr()%>"
                            fechaInicialTag="<%=fachadaColaboraReporteDctoBean.getFechaInicialSqlServer()%>"
                            fechaFinalTag="<%=fachadaColaboraReporteDctoBean.getFechaFinalSqlServer()%>">
                <tr>
                    <td width="5%" align="left" class="letraDetalle"><%=codigoVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreReferenciaVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=fechaDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrBaseVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=pesoFacturadoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=cantidadFacturadoVar%></td> 
                    <td width="10%" align="left" class="letraDetalle"><%=nombreTipoNegocioVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=aliasUsuarioVar%></td>
                </tr>

            </lst:listaVentaReferencia>
        </table>
    </body>
</html>
</html>