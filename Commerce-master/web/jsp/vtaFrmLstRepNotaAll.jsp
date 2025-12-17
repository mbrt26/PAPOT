<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaNotaAll" prefix="lst" %>

    <jsp:useBean id="fachadaDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoBean" />

    <head>
        <title>Ventas x Detalle</title>
    </head>
    <body>

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">VENTAS DETALLE</td>
                <td width="33%" class="letraTitulo">&nbsp;</td>
            </tr>
            <tr>
                <td width="45%" class="letraTitulo">
                    <jsp:include page="./comboLocal.jsp"/>
                </td>
                <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
                <td width="45%" class="letraTitulo">
                    <jsp:include page="./comboFechaHoy.jsp"/>
                </td>
            </tr>

            <tr>
                <td width="10%" align="center" class="letraTitulo">&nbsp;</td>
                <td width="45%" align="center" class="letraTitulo">DEL <%=fachadaDctoBean.getFechaInicialStr()%>
                AL <%=fachadaDctoBean.getFechaFinalStr()%></td>
                <td width="45%" align="center" class="letraTitulo">&nbsp;</td>
            </tr>
            <td width="45%" align="center" class="letraTitulo">&nbsp;</td>
        </table>

        <table border="2" width="90%" id="tablaTitulo">
            <tr bgcolor="green">
            <b><td width="5%" align="left" class="letraTitulo">NRO.DOC</td>
                <td width="5%" align="left" class="letraTitulo">NIT</td>
                <td width="35%" align="leftt" class="letraTitulo">CLIENTE</td>
                <td width="10%" align="right" class="letraTitulo">F.FRA</td>
                <td width="5%" align="right" class="letraTitulo">FR.ORIG</td>
                <td width="5%" align="right" class="letraTitulo">v.COSTO</td>
                <td width="15%" align="right" class="letraTitulo">CAUSA</td>
                <td width="10%" align="right" class="letraTitulo">VEN</td>
                </b>
            </tr>

            <lst:listaNotaAll
                            idLocalTag="<%=fachadaDctoBean.getIdLocalStr()%>"
                            fechaInicialTag="<%=fachadaDctoBean.getFechaInicialStr()%>"
		            fechaFinalTag="<%=fachadaDctoBean.getFechaFinalStr()%>"
                            indicadorInicialTag="<%=fachadaDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaDctoBean.getIndicadorFinalStr()%>"
                            idTipoOrdenInicialTag="<%=fachadaDctoBean.getIdTipoOrdenINIStr()%>"
                            idTipoOrdenFinalTag="<%=fachadaDctoBean.getIdTipoOrdenFINStr()%>"
                            idVendedorTag="<%=fachadaDctoBean. getIdVendedorStr()%>">

                <tr>
                    <td width="5%" align="left" class="letraDetalle"><%=idDctoVar%></td>
                    <td width="5%" align="left" class="letraDetalle"><%=idClienteVar%></td>
                    <td width="35%" align="right" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="10%" align="right" class="letraDetalle"><%=fechaDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=idDctoCruceVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoMVVar%></td>
                    <td width="15%" align="right" class="letraDetalle"><%=nombreCausaVar%></td>
                    <td width="10%" align="right" class="letraDetalle"><%=aliasUsuarioVar%></td>
               </tr>

            </lst:listaNotaAll>
        </table>
    </body>
</html>
