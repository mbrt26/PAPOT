<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaComisionRecaudo" prefix="lst" %>

    <jsp:useBean id="fachadaParametroComisionBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaParametroComisionBean" />

    <head>
        <title>Reporte Comision Recaudo</title>
    </head>
    <body>
        
        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <!--td width="33%" class="letraTitulo" >&nbsp;</td-->
                <td width="34%" align="center" class="letraTitulo" colspan="12" ><strong>COMISION RECAUDO</strong></td>
                <!--td width="33%" class="letraTitulo">&nbsp;</td-->
            </tr>
            <tr>
                <td width="33%" class="letraTitulo" align="center" colspan="12" ><strong>
                    <jsp:include page="./comboLocal.jsp"/>
                </strong></td>
                <!--td width="34%" align="center" class="letraTitulo">&nbsp;</td-->
                
            </tr>
            <tr>
                <td width="33%" class="letraTitulo" colspan="12"><strong>
                    <jsp:include page="./comboFechaHoy.jsp"/>
                </strong></td>
            </tr>

            <tr>
                
                <td width="34%" align="center" class="letraTitulo" colspan="12"><strong>DEL <%=fachadaParametroComisionBean.getFechaInicial()%>
                AL <%=fachadaParametroComisionBean.getFechaFinal()%></strong></td>
                <!--td width="33%" align="center" class="letraTitulo">&nbsp;</td-->
            </tr>
            <tr>
                <td width="33%" colspan="12" align="center" class="letraTitulo">&nbsp;</td>
            </tr>
        </table>

        <table border="0" width="90%" id="tablaTitulo">
            <tr bgcolor="#87CEFA">
                <td width="5%" align="right" class="letraTitulo"><strong>#DOC</strong></td>
                <td width="5%" align="right" class="letraTitulo"><b>NIT</b></td>
                <td width="30%" align="left" class="letraTitulo"><b>NOMBRE CLIENTE</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>#FRA</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>FEC.FRA</b></td>
                <td width="5%" align="right" class="letraTitulo"><b>#REC</b></td>
                <td width="5%" align="center" class="letraTitulo"><b>FEC.PAG</b></td>
                <td width="5%" align="right" class="letraTitulo"><b>VR.PAG</b></td>                
                <td width="5%" align="right" class="letraTitulo"><b>#DIA</b></td>
                <td width="5%" align="right" class="letraTitulo"><b>%</b></td>
                <td width="10%" align="right" class="letraTitulo"><b>VR.COM</b></td>
                <td width="5%" align="left" class="letraTitulo"><b>USR</b></td>

            </tr>

            <lst:listaComisionRecaudo                           
                            idVendedorTag="<%=fachadaParametroComisionBean.getIdVendedorStr()%>"
                            fechaInicialTag="<%=fachadaParametroComisionBean.getFechaInicialSqlServer()%>"
                            fechaFinalTag="<%=fachadaParametroComisionBean.getFechaFinalSqlServer()%>">
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=idDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idClienteVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=idFraVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=fechaDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idReciboVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=fechaPagoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrPagadoVar%></td>                    
                    <td width="5%" align="right" class="letraDetalle"><%=idDiasVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=porcentajeVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrComisionVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=aliasUsuarioVar%></td>

                </tr>

            </lst:listaComisionRecaudo>
        </table>
    </body>
</html>
</html>