<html>

    <%@ taglib uri="/WEB-INF/tlds/listaInventarioOTExterno" prefix="lst" %>

    <jsp:useBean id="fachadaLocalBodega"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaLocalBodega" />
    <jsp:useBean id="fachadaDctoOrdenDetalleBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean" />

    <head>
        <title>Inventario O.T. Externo</title>
    </head>

    <body>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">INVENTARIO O.T. EXTERNO</td>
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
                <td width="5%" align="right" class="letraTitulo">O.T.</td>
                <td width="30%" align="left" class="letraTitulo">REFERENCIA CLIENTE</td>
                 <td width="30%" align="left" class="letraTitulo">OPERARIO</td>
                <td width="5%" align="right" class="letraTitulo">INVENTARIO
                                                             <br>CANTIDAD (UN)</td>
                <td width="5%" align="right" class="letraTitulo">INVENTARIO
                                                             <br>PESO (KG)</td>

            </tr>
            <lst:listaInventarioOTExterno idLocalTag="<%=fachadaDctoOrdenDetalleBean.getIdLocalStr()%>"
                                   idTipoOrdenTag="<%=fachadaDctoOrdenDetalleBean.getIdTipoOrdenStr()%>"
                                   idOperacionTag="<%=fachadaDctoOrdenDetalleBean.getIdOperacionStr()%>">
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=numeroOrdenVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=referenciaClientePluVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreOperarioVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=existenciaCantidadVar%></td>
                    <td width="5%" align="right" class="letraDetalle"><%=existenciaPesoVar%></td>
                </tr>
            </lst:listaInventarioOTExterno>
                <tr>
                    <td width="5%" align="right" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="30%" align="left" class="letraResaltadaGrande">TOTALES</td>
                    <td width="30%" align="left" class="letraResaltadaGrande">&nbsp;</td>
                    <td width="5%" align="right" class="letraResaltadaGrande"><%=existenciaCantidadTotalVar%></td>
                    <td width="30%" align="right" class="letraResaltadaGrande"><%=existenciaPesoTotalVar%></td>
                </tr>                
        </table>

    </body>
</html>