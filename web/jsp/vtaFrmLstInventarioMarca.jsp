<html>

    <%@ taglib uri="/WEB-INF/tlds/listaInventarioCostoMarca" prefix="lst" %>

    <jsp:useBean id="fachadaMarcaBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaMarcaBean" />

    <body>
        <table border="0" width="90%" id="tablaTitulo">
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">INVENTARIO REFERENCIA</td>
                <td width="33%" class="letraTitulo">&nbsp;</td>
            </tr>
            <tr>
                <td width="33%" class="letraTitulo">&nbsp;</td>
                <td width="34%" align="center" class="letraTitulo">
                    <jsp:include page="./comboLocal.jsp"/>
                </td>
                <td width="33%" class="letraTitulo">
                    <jsp:include page="./comboFechaHoy.jsp"/>
                </td>
            </tr>
        </table>

        <table border="0" width="90%" id="tablaTitulo">

            <tr>
                <td width="5%" align="right" class="letraTitulo">PLU</td>
                <td width="30%" align="left" class="letraTitulo">NOMBRE PLU</td>
                <td width="5%" align="right" class="letraTitulo">EXIST</td>
                <td width="5%" align="left" class="letraTitulo">V.COSTO</td>
                <td width="5%" align="right" class="letraTitulo">V.CTOIND</td>
                <td width="5%" align="right" class="letraTitulo">V.IMP.</td>
                <td width="5%" align="right" class="letraTitulo">IVA</td>
                <td width="15%" align="right" class="letraTitulo">MARCA</td>
            </tr>
            <lst:listaInventarioCostoMarca idMarcaTag="<%=fachadaMarcaBean.getIdMarcaStr()%>"
                                           idLocalTag="<%=fachadaMarcaBean.getIdLocalStr()%>"
                                           idBodegaTag="<%=fachadaMarcaBean.getIdBodegaStr()%>">
                <tr>
                    <td width="5%" align="right" class="letraDetalle"><%=idPluVar%></td>
                    <td width="30%" align="left" class="letraDetalle"><%=nombreCategoriaVar%>
                        <%=nombrePluVar%></td>
                    <td width="5%" align="right" ><%=existenciaSf2Var%></td>
                    <td width="5%" align="right" ><%=vrCostoInventarioVar%></td>
                    <td width="5%" align="right" ><%=vrTotalCostoINDVar%></td>
                    <td width="5%" align="right" ><%=vrTotalImpoconsumoVar%></td>
                    <td width="5%" align="right" ><%=vetVrCostoIvaVar%></td>
                    <td width="15%" align="left" ><%=nombreMarcaVar%></td>
                </tr>

            </lst:listaInventarioCostoMarca>
        </table>
    </body>
</html>