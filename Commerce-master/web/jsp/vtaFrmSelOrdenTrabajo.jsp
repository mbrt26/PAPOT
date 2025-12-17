<html>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">

    <%@ taglib uri="/WEB-INF/tlds/listaAllFicha" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteControlAgendaNit" prefix="lst" %>

    <%
                HttpSession sesion = request.getSession(true);
                String xIdOperacionStr = "1";
    %>

    <jsp:useBean id="fachadaAgendaLogVisitaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean" />

    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />


    <head>
        <title>ADICIONAR PRODUCTOS</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelOrdenTrabajo.jsp">

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">ADICIONAR PRODUCTOS</td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="33%" class="letraTitulo">
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                </tr>

                <lst:listaClienteControlAgendaNit idClienteTag="<%=fachadaTerceroBean.getIdCliente()%>"
                idTipoTerceroTag="<%=fachadaTerceroBean.getIdTipoTerceroStr()%>">
                    <tr>
                        <td width="33%" align="center" class="letraDetalle">ESTADO CLIENTE</td>
                        <td width="34%" align="center" class="letraDetalle">CODIGO</td>
                        <td width="33%" align="center" class="letraDetalle">NOMBRE CLIENTE</td>
                    </tr>

                    <tr>
                        <td width="33%" align="center" class="letraDetalle"><%=estadoVar%></td>
                        <td width="34%" align="center" class="letraDetalle"><%=idClienteVar%></td>
                        <td width="33%" align="center" class="letraDetalle"><%=nombreTerceroVar%></td>
                    </tr>
                </lst:listaClienteControlAgendaNit>
            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" align="right" class="letraTitulo">IDFICHA</td>
                    <td width="5%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="10%" align="left" class="letraTitulo">REFERENCIA</td>
                    <td width="30%" align="left" class="letraTitulo">REF.CLIENTE</td>
                    <td width="10%" align="left" class="letraTitulo">CODIGO</td>
                    <td width="20%" align="left" class="letraTitulo">MATERIAL</td>
                    <td width="5%" align="left" class="letraTitulo">&nbsp;</td>
                </tr>

                <lsu:listaAllFicha idLocalTag = "<%=fachadaTerceroBean.getIdLocalStr()%>"
                idClienteTag = "<%=fachadaTerceroBean.getIdCliente()%>"
                idOperacionTag = "<%=xIdOperacionStr%>">
                    <tr>
                        <td width="5%" align="right" class="letraDetalle"><%=idFichaVar%></td>
                        <td width="5%" align="center" class="letraDetalle">&nbsp;</td>
                        <td width="10%" align="left" class="letraDetalle"><%=referenciaVar%></td>
                        <td width="30%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelOrdenTrabajo.jsp&accionContenedor=Confirmar&xIdPlu=<%=idPluVar%>&xIdFicha=<%=idFichaVar%>"><%=referenciaClienteVar%></a>
                        </td>
                        <td width="10%" align="left" class="letraDetalle"><%=nombreReferenciaVar%></td>
                        <td width="20%" align="left" class="letraDetalle"><%=nombrePluVar%></td>
                        <td width="5%" align="left" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmSelOrdenTrabajo.jsp&accionContenedor=modificaretira&xIdPlu=<%=idPluVar%>&xIdFicha=<%=idFichaVar%>">Modificar/Retirar</a>
                        </td>

                    </tr>
                </lsu:listaAllFicha>

            </table>
            <script type="text/javascript">
                document.getElementById('chkCantidad').focus();
            </script>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" align="center" class="letraTitulo">
                        <a href="javascript:window.history.back()"><img src="./img/ARW06LT.GIF" width="32" height="32"></a>
                    </td>
                    <td width="34%" align="left" class="letraTitulo">&nbsp;</td>
                    <td width="33%" align="right" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>

        </form>
    </body>
</html>