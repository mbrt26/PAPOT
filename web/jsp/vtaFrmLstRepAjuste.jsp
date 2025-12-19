<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaInventarioRepAjustePositivo" prefix="lst" %>

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
                <td width="34%" align="center" class="letraTitulo">AJUSTE</td>
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
                <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">DEL <%=fachadaDctoBean.getFechaInicial()%>
                AL <%=fachadaDctoBean.getFechaFinal()%></td>
                <td width="33%" align="center" class="letraTitulo">&nbsp;</td>
            </tr>
        </table>

        <table border="2" width="90%" id="tablaTitulo">
            <tr bgcolor="green">
                <td width="5%" align="center" class="letraTitulo">#FR</td>
                <td width="30%" align="left" class="letraTitulo">USUARIO</td>
                <td width="30%" align="left" class="letraTitulo">F.FRA</td>
                <td width="5%" align="right" class="letraTitulo">V.BASE</td>
                <td width="5%" align="right" class="letraTitulo">V.IVA</td>
                <td width="5%" align="right" class="letraTitulo">V.COSTO</td>
            </tr>
           <lst:listaInventarioRepAjustePositivo
                           idLocalTag="<%=fachadaDctoBean.getIdLocalStr()%>"
                            fechaInicialTag="<%=fachadaDctoBean.getFechaInicial()%>"
		            fechaFinalTag="<%=fachadaDctoBean.getFechaFinal()%>"
                            indicadorInicialTag="<%=fachadaDctoBean.getIndicadorInicialStr()%>"
                            indicadorFinalTag="<%=fachadaDctoBean.getIndicadorFinalStr()%>"
                            idTipoOrdenTag="<%=fachadaDctoBean.getIdTipoOrdenStr()%>">
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=idDctoVar%></td>
                    <td width="30%" align="right" class="letraDetalle"><%=nombreUsuarioVar%></td>
                    <td width="30%" align="left" class="letraTitulo"><%=fechaDctoVar%></td>
                    <td width="5%" align="left" class="letraDetalle"><%=vrBaseVar%></td>
                    <td width="5%" align="center" class="letraDetalle"><%=vrIvaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=vrCostoMvVar%></td>
                    
                 </tr>

            </lst:listaInventarioRepAjustePositivo>
        </table>
    </body>
</html>
