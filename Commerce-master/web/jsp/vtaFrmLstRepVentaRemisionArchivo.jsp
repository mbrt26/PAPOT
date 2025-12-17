<html>

    <%@ taglib uri="/WEB-INF/tlds/listaRemisionAll" prefix="lst" %>

    <jsp:useBean id="fachadaDctoBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoBean" />

    <body>

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">DETALLE REMISION</td>
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

        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="5%" align="right" class="letraTitulo">NRO.DOC</td>
                <td width="5%" align="right" class="letraTitulo">O.T.</td>
                <td width="35%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
                <td width="10%" align="center" class="letraTitulo">FEC.REM</td>
                <td width="5%" align="right" class="letraTitulo">CANTIDAD</td>
                <td width="5%" align="left" class="letraTitulo">REFERENCIA CLIENTE</td>
            </tr>
            <lst:listaRemisionAll
            idLocalTag="<%=fachadaDctoBean.getIdLocalStr()%>"
            idTipoOrdenTag="<%=fachadaDctoBean.getIdTipoOrdenStr()%>"
            fechaInicialTag="<%=fachadaDctoBean.getFechaInicial()%>"
            fechaFinalTag="<%=fachadaDctoBean.getFechaFinal()%>"
            idTerceroTag="<%=fachadaDctoBean.getIdCliente()%>">
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=idDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=numeroOrdenVar%></td>
                    <td width="35%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="10%" align="center" class="letraDetalle"><%=fechaDctoVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=cantidadVar%></td>
                    <td width="5%" align="left" class="letraDetalle"><%=referenciaClienteVar%></td>
                </tr>
            </lst:listaRemisionAll>
        </table>
    </body>
</html>
