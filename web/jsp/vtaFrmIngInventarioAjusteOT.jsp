<html>


    <% String xIdBodega = "1";%>

    <%@ taglib uri="/WEB-INF/tlds/listaUnLocalBodega" prefix="lsx" %>
    <%@ taglib uri="/WEB-INF/tlds/listaPrecio" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/liquidaOrden" prefix="lsu" %>
    <%@ taglib uri="/WEB-INF/tlds/listaOrden" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaClienteCotizacionArticulos" prefix="listaArticulos" %>

    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />

    <head>
        <title>Movimiento Interno Ajuste O.T.</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngInventarioAjusteOT.jsp">
            <input type="hidden" name="xIdLocal" value="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">                        
            <input type="hidden" name="xIdTipoOrden" value="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">            
            <input type="hidden" name="xIdTercero" value="<%=fachadaDctoOrdenBean.getIdCliente()%>">
            <input type="hidden" name="xFechaCorte" value="<%=fachadaDctoOrdenBean.getFechaOrdenCorta()%>">
            <input type="hidden" name="xIdOperacion" value="<%=fachadaDctoOrdenBean.getIdOperacionStr()%>">
            <input type="hidden" name="xNumeroOrdenOrigen" value="<%=fachadaDctoOrdenBean.getNumeroOrden()%>">
            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="33%" class="letraTitulo">&nbsp;</td>
                    <td width="34%" align="center" class="letraTitulo">MOVIMIENTO INTERNO AJUSTE O.T.</td>
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

                <tr>
                    <td width="33%" align="center" class="letraDetalle">BODEGA ORIGEN</td>
                    <td width="34%" align="center" class="letraDetalle">&nbsp;</td>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>
                <tr>
                    <td width="33%" align="center" class="letraResaltada">
                        <lsx:listaUnLocalBodega idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                                                idBodegaTag = "<%=fachadaDctoOrdenBean.getIdOperacionStr()%>">
                            <%=nombreBodegaVar%>
                        </lsx:listaUnLocalBodega>

                    </td>
                    <td width="33%" align="center" class="letraResaltada">&nbsp;</td>
                    <td width="33%" align="center" class="letraDetalle">&nbsp;</td>
                </tr>

                <tr>
                    <td width="33%" align="right" class="letraTitulo">#ARTICULOS</td>
                    <td width="34%" align="right" class="letraTitulo">$VR.BASE</td>
                    <td width="33%" align="right" class="letraTitulo">$VR.TOTAL</td>
                </tr>

                <lsu:liquidaOrden idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                                  idLogTag = "<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                                  idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>">     
                    <tr>
                        <td width="33%" align="right" class="letraDetalle"><%=cantidadArticulosVar%></td>
                        <td width="34%" align="right" class="letraDetalle"><%=vrVentaSinDsctoDf0Var%></td>
                        <td width="33%" align="right" class="letraDetalle"><%=vrVentaConIvaVar%></td>
                    </tr>

                </lsu:liquidaOrden>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="50%" align="right" class="letraTitulo">
                        <input type="text" name="xNumeroOrden" id="xNumeroOrden" size="6" tabindex="1" maxlength="6">
                    </td>
                    <td width="50%" align="left"  class="letraTitulo">
                        <input type="submit" value="O.T." name="accionContenedor">
                    </td>
                </tr>
            </table>

            <script type="text/javascript">
                document.getElementById('idLinea').focus();
            </script>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="5%" class="letraTitulo">&nbsp;</td>
                    <td width="5%"  align="left" class="letraTitulo">REF</td>
                    <td width="30%" nowrap align="left" class="letraTitulo">NOMBRE REFERENCIA</td>
                    <td width="10%" nowrap align="left" class="letraTitulo">MARCA</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">&nbsp;</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">&nbsp;</td>
                    <td width="15%" nowrap align="right" class="letraTitulo">&nbsp;</td>                    
                    <td width="5%" nowrap align="right" class="letraTitulo">EXIST</td>
                    <td width="5%" nowrap align="right" class="letraTitulo">CANT</td>                    
                </tr>
                <lsv:listaOrden idLocalTag="<%=fachadaDctoOrdenBean.getIdLocalStr()%>"
                                idTipoOrdenTag="<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>"
                                idLogTag="<%=fachadaDctoOrdenBean.getIdLogStr()%>"
                                idBodegaTag="<%=xIdBodega%>">

                    <tr>
                        <td width="5%"  nowrap align="center" class="letraDetalle">
                            <a href="GralControladorServlet?nombrePaginaRequest=/jsp/vtaFrmIngInventarioAjusteOT.jsp&accionContenedor=retira&xItem=<%=itemVar%>&xIdLog=<%=fachadaDctoOrdenBean.getIdLogStr()%>&xIdTercero=<%=fachadaDctoOrdenBean.getIdCliente()%>&xFechaCorte=<%=fachadaDctoOrdenBean.getFechaOrdenCorta()%>&xIdOperacion=<%=fachadaDctoOrdenBean.getIdOperacionStr()%>&xIdTipoOrden=<%=fachadaDctoOrdenBean.getIdTipoOrdenStr()%>">R</a>
                        </td>
                        <td width="5%" nowrap align="left" class="letraDetalle"><%=strIdReferenciaVar%></td>
                        <td width="30%" nowrap align="left" class="letraDetalle"><%=nombrePluVar%></td>
                        <td width="10%" nowrap align="left" class="letraDetalle"><%=nombreMarcaVar%></td>
                        <td width="5%" nowrap align="right" class="letraDetalle">&nbsp;</td>
                        <td width="5%" nowrap align="right" class="letraDetalle">&nbsp;</td>
                        <td width="15%" nowrap align="right" class="letraDetalle">&nbsp;</td>
                        <td width="5%" nowrap align="right" class="letraDetalle"><%=existenciaVar%></td>
                        <td width="5%" nowrap align="right" class="letraDetalle"><%=cantidadVar%></td>                        
                    </tr>

                </lsv:listaOrden>

            </table>

            <table border="0" width="90%" id="tablaTitulo">
                <tr>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Legalizar" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">
                        <input type="submit" value="Salir" name="accionContenedor">
                    </td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                    <td width="25%" align="center" class="letraTitulo">&nbsp;</td>
                </tr>
            </table>
        </form>

    </body>

</html>