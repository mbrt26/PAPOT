<html>

<head>
</head

    <%@ taglib uri="/WEB-INF/tlds/listaInventarioOperacion" prefix="lst" %>

    <jsp:useBean id="fachadaLocalBodega"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaLocalBodega" />
    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />


    <body>
        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">INVENTARIO OPERACION</td>
                <td width="33%" class="letraTitulo">&nbsp;</td>
            </tr>
            <tr>
                <td width="33%" align="center" class="letraResaltadaTitulo">
                    <jsp:include page="./comboLocal.jsp"/>
                </td>
                <td width="34%" class="letraTitulo">&nbsp;</td>
                <td width="33%" class="letraTitulo">
                    <jsp:include page="./comboFechaHoy.jsp"/>
                </td>
            </tr>
        </table>

        <table border="0" width="90%" id="tablaTitulo">

            <tr>
                <td width="5%" align="left" class="letraTitulo">OPERACION</td>
                <td width="5%" align="right" class="letraTitulo">O.T.</td>
                <td width="30%" align="left" class="letraTitulo">NOMBRE CLIENTE</td>
                <td width="5%" align="left" class="letraTitulo">REF.CLIENTE</td>
                <td width="5%" align="left" class="letraTitulo">MATERIAL</td>
                <td width="5%" align="right" class="letraTitulo">UNIDADES</td>
                <td width="5%" align="right" class="letraTitulo">KG</td>
            </tr>
            <lst:listaInventarioOperacion idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                                   idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                                   idOperacionTag="<%=fachadaLocalBodega.getIdBodegaStr()%>">
                <tr>
                    <td width="5%" align="left" class="letraDetalle"><%=nombreOperacionVar%></td>
                    <td width="5%" align="right" class="letraDetalle">&nbsp;<%=numeroOrdenVar%>-<%=itemVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreTerceroVar%></td>
                    <td width="5%" align="left" class="letraDetalle"><%=referenciaClienteVar%></td>
                    <td width="5%" align="left" class="letraDetalle"><%=nombreReferenciaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=cantidadTerminadaVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=pesoTerminadoVar%></td>
                </tr>
            </lst:listaInventarioOperacion>
        </table>
    </body>
</html>