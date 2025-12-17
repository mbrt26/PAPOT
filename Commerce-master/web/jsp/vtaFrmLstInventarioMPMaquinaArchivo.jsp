<html>

    <%@ taglib uri="/WEB-INF/tlds/listaInventarioMPMaquina" prefix="lst" %>

    <jsp:useBean id="fachadaLocalBodega"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaLocalBodega" />
    <jsp:useBean id="fachadaDctoOrdenDetalleBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />

    <head>
        <title>Inventario M.P. Maquina</title>
    </head>

    <body>
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">INVENTARIO M.P. MAQUINA</td>
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
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraResaltadaTitulo">
                        <br>BODEGA
                        <%=fachadaLocalBodega.getNombreBodega()%>
                        <jsp:include page="./comboFechaHoy.jsp"/>
                    </td>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        <table border="0" width="90%" id="tablaTitulo">

            <tr>
                <td width="10%" align="right" class="letraTitulo">IDPLU</td>
                <td width="35%" align="left" class="letraTitulo">REFERENCIA CLIENTE /
                    <br>M.P.</td>
                <td width="10%" align="left" class="letraTitulo">MAQUINA</td>
                 <td width="5%" align="right" class="letraTitulo">O.T.</td>
                <td width="5%" align="right" class="letraTitulo">INVENTARIO
                                                             <br>PESO (KG)</td>
                <td width="30%" align="right" class="letraTitulo">&nbsp;</td>
            </tr>
            <lst:listaInventarioMPMaquina idLocalTag="<%=fachadaDctoOrdenDetalleBean.getIdLocalStr()%>"
                                   idTipoOrdenTag="<%=fachadaDctoOrdenDetalleBean.getIdTipoOrdenStr()%>"
                                   idOperacionTag="<%=fachadaDctoOrdenDetalleBean.getIdOperacionStr()%>">
                <tr>
                    <td width="10%" align="right" class="letraDetalle"><%=idPluVar%></td>
                    <td width="35%" align="left" class="letraDetalle"><%=nombrePluVar%></td>
                    <td width="10%" align="left" class="letraDetalle"><%=nombreItemVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=numeroOrdenVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=existenciaCantidadVar%></td>
                    <td width="30%" align="right" class="letraDetalle">&nbsp;</td>
                </tr>
            </lst:listaInventarioMPMaquina>
                <tr>
                    <td width="10%" align="right" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="35%" align="left" class="letraResaltadaGrande">TOTALES</td>
                    <td width="10%" align="left" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="5%" align="right" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="5%" align="right" class="letraResaltadaGrande"><%=existenciaCantidadTotalVar%></td>
                    <td width="30%" align="right" class="letraResaltadaGrande">&nbsp;</td>
                </tr>
        </table>
    </body>
</html>